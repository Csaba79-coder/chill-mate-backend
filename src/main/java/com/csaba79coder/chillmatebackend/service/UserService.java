package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.*;
import com.csaba79coder.chillmatebackend.model.*;
import com.csaba79coder.chillmatebackend.persistence.UserRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ActivityService activityService;
    private final CityService cityService;
    private final HobbyService hobbyService;
    private final MovieService movieService;
    private final MusicGenreService musicGenreService;
    private final SportService sportService;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapper::mapUserEntityToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse createUser(
            String firstName,
            String midName,
            String lastName,
            String city,
            String activity,
            String hobby,
            String sport,
            String musicGenre,
            String movie
    ) {
        Activity activityEntity = mapActivityResponseToEntity(activityService.getOrCreate(activity));
        Hobby hobbyEntity = mapHobbyResponseToEntity(hobbyService.getOrCreate(hobby));
        Sport sportEntity = mapSportResponseToEntity(sportService.getOrCreate(sport));
        MusicGenre musicEntity = mapMusicGenreResponseToEntity(musicGenreService.getOrCreate(musicGenre));
        Movie movieEntity = mapMovieResponseToEntity(movieService.getOrCreate(movie));
        City cityEntity = mapCityResponseToEntity(cityService.getOrCreate(city));

        User user = User.builder()
                .firstName(firstName)
                .midName(midName)
                .lastName(lastName)
                .activities(List.of(activityEntity))
                .hobbies(List.of(hobbyEntity))
                .sports(List.of(sportEntity))
                .musicGenres(List.of(musicEntity))
                .movies(List.of(movieEntity))
                .city(cityEntity)
                .build();
        return mapUserEntityToResponse(userRepository.save(user));
    }


    /*public UserResponse createUserWithConnections(String firstName, String midName, String lastName, String cityName, List<String> activities, List<String> hobbies, List<String> sports, List<String> musicGenres, List<String> movies) {
        List<Activity> currentActivities = getOrCreateActivities(activities);
        List<Hobby> currentHobbies = getOrCreateHobbies(hobbies);
        List<Sport> currentSports = getOrCreateSports(sports);
        List<MusicGenre> currentMusicGenres = getOrCreateMusicGenres(musicGenres);
        List<Movie> currentMovies = getOrCreateMovies(movies);
        CityResponse cityResponse = cityService.findByName(cityName);
        City city = Mapper.mapCityResponseToEntity(cityResponse);

        User user = User.builder()
                .firstName(firstName)
                .midName(midName)
                .lastName(lastName)
                .activities(currentActivities)
                .hobbies(currentHobbies)
                .sports(currentSports)
                .musicGenres(currentMusicGenres)
                .movies(currentMovies)
                .city(city)
                .build();
        return mapUserEntityToResponse(userRepository.save(user));
    }*/














    // Update the existing user or create a new one with connections (activities, city)
    public UserResponse updateUserWithConnections(UUID userId, String firstName, String midName, String lastName, String cityName, List<String> activities) {
        // Fetch the existing user
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " not found"));

        // Update user details
        existingUser.setFirstName(firstName);
        existingUser.setMidName(midName);
        existingUser.setLastName(lastName);

        // Handle activities
        List<Activity> currentActivities = getOrCreateActivities(activities);
        existingUser.setActivities(currentActivities);

        // Handle city
        CityResponse cityResponse = cityService.findCityByName(cityName);
        existingUser.setCity(Mapper.mapCityResponseToEntity(cityResponse));

        // Save updated user
        User updatedUser = userRepository.save(existingUser);

        return Mapper.mapUserEntityToResponse(updatedUser);
    }

    // One-way friendship
    public void addFriendOneWay(User from, User to) {
        if (!from.getFriends().contains(to)) {
            from.getFriends().add(to);
            userRepository.save(from);
        }
    }

    // Both ways friendship
    public void addFriendMutual(User user1, User user2) {
        addFriendOneWay(user1, user2);
        addFriendOneWay(user2, user1);
    }

    private List<Activity> getOrCreateActivities(List<String> activityNames) {
        return activityNames.stream()
                .map(name -> {
                    try {
                        return mapActivityResponseToEntity(activityService.findActivityByName(name));
                    } catch (NoSuchElementException e) {
                        ActivityResponse newActivity = activityService.createActivity(new ActivityRequest(name));
                        return mapActivityResponseToEntity(newActivity);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Hobby> getOrCreateHobbies(List<String> hobbyNames) {
        return hobbyNames.stream()
                .map(name -> {
                    try {
                        return mapHobbyResponseToEntity(hobbyService.findHobbyByName(name));
                    } catch (NoSuchElementException e) {
                        HobbyResponse newHobby = hobbyService.createHobby(new HobbyRequest(name));
                        return mapHobbyResponseToEntity(newHobby);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Sport> getOrCreateSports(List<String> sportNames) {
        return sportNames.stream()
                .map(name -> {
                    try {
                        return mapSportResponseToEntity(sportService.findSportByName(name));
                    } catch (NoSuchElementException e) {
                        SportResponse newSport = sportService.createSport(new SportRequest(name));
                        return mapSportResponseToEntity(newSport);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<MusicGenre> getOrCreateMusicGenres(List<String> genreNames) {
        return genreNames.stream()
                .map(name -> {
                    try {
                        return mapMusicGenreResponseToEntity(musicGenreService.findMusicGenreByName(name));
                    } catch (NoSuchElementException e) {
                        MusicGenreResponse newGenre = musicGenreService.createMusicGenre(new MusicGenreRequest(name));
                        return mapMusicGenreResponseToEntity(newGenre);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Movie> getOrCreateMovies(List<String> movieNames) {
        return movieNames.stream()
                .map(name -> {
                    try {
                        return mapMovieResponseToEntity(movieService.findMovieByName(name));
                    } catch (NoSuchElementException e) {
                        MovieResponse newMovie = movieService.createMovie(new MovieRequest(name));
                        return mapMovieResponseToEntity(newMovie);
                    }
                })
                .collect(Collectors.toList());
    }
}
