package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.*;
import com.csaba79coder.chillmatebackend.model.*;
import com.csaba79coder.chillmatebackend.persistence.*;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final CityRepository cityRepository;
    private final HobbyRepository hobbyRepository;
    private final MovieRepository movieRepository;
    private final MusicGenreRepository musicGenreRepository;
    private final SportRepository sportRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapper::mapUserEntityToResponse)
                .collect(Collectors.toList());
    }

    public UserBasicResponse createUserBasic(UserBasicRequest userRequest) {
        if (userRequest.getFirstName() == null || userRequest.getFirstName().trim().isEmpty()) {
            String message = "User first name is null or empty. No action taken.";
            log.info(message);
            return new UserBasicResponse(message, HttpStatus.BAD_REQUEST.value());
        }

        Optional<User> existingUser = userRepository.findUserByFirstNameEqualsIgnoreCaseAndMidNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(
                userRequest.getFirstName(), userRequest.getMidName(), userRequest.getLastName());

        if (existingUser.isPresent()) {
            log.info("User with name '{}' already exists. Returning existing user...", userRequest.getFirstName());
            return mapUserEntityToBasicResponse(existingUser.get());
        }

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .midName(userRequest.getMidName())
                .lastName(userRequest.getLastName())
                .build();

        log.info("Creating new user: {}", user);
        return mapUserEntityToBasicResponse(userRepository.save(user));
    }

    public UserBasicResponse findUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("User with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });

        return mapUserEntityToBasicResponse(user);
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("User with id: %s was not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                });

        userRepository.delete(user);
        log.info("User with id: {} deleted successfully", id);
    }

    public List<UserBasicResponse> findUsersByName(String name) {
        String searchQuery = name.trim();

        if (searchQuery.isEmpty()) {
            return Collections.emptyList();
        }

        return userRepository.findByFirstNameContainingIgnoreCaseOrMidNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                searchQuery, searchQuery, searchQuery)
                .stream()
                .map(Mapper::mapUserEntityToBasicResponse)
                .toList();
    }

    public UserResponse addConnectionsToUser(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional.ofNullable(request.getCity())
                .filter(city -> !city.trim().isEmpty())
                .map(city -> cityRepository.findCityByNameEqualsIgnoreCase(city)
                        .orElseGet(() -> createCity(city)))
                .ifPresent(user::setCity);
        Optional.ofNullable(request.getHobby())
                .filter(hobby -> !hobby.trim().isEmpty())
                .map(hobby -> hobbyRepository.findHobbyByNameEqualsIgnoreCase(hobby)
                        .orElseGet(() -> createHobby(hobby)))
                .ifPresent(h -> {
                    if (!user.getHobbies().contains(h)) {
                        user.getHobbies().add(h);
                    }
                });
        Optional.ofNullable(request.getSport())
                .filter(sport -> !sport.trim().isEmpty())
                .map(sport -> sportRepository.findSportByNameEqualsIgnoreCase(sport)
                        .orElseGet(() -> createSport(sport)))
                .ifPresent(s -> {
                    if (!user.getSports().contains(s)) {
                        user.getSports().add(s);
                    }
                });
        Optional.ofNullable(request.getMusic())
                .filter(musicGenre -> !musicGenre.trim().isEmpty())
                .map(musicGenre -> musicGenreRepository.findMusicGenreByGenreEqualsIgnoreCase(musicGenre)
                        .orElseGet(() -> createMusicGenre(musicGenre)))
                .ifPresent(m -> {
                    if (!user.getMusicGenres().contains(m)) {
                        user.getMusicGenres().add(m);
                    }
                });
        Optional.ofNullable(request.getMovie())
                .filter(movie -> !movie.trim().isEmpty())
                .map(movie -> movieRepository.findMovieByTitleEqualsIgnoreCase(movie)
                        .orElseGet(() -> createMovie(movie)))
                .ifPresent(m -> {
                    if (!user.getMovies().contains(m)) {
                        user.getMovies().add(m);
                    }
                });
       Optional.ofNullable(request.getEvent())
               .filter(activity -> !activity.trim().isEmpty())
                       .map(activity -> activityRepository.findActivityByNameEqualsIgnoreCase(activity)
                       .orElseGet(() -> createActivity(activity)))
                               .ifPresent(a -> {
                                   if (!user.getActivities().contains(a)) {
                                       user.getActivities().add(a);
                                   }
                               });
// TODO a friends!
        /*String firstName = request.getFriend().getFirstName();
        String midName = request.getFriend().getMidName();
        String lastName = request.getFriend().getLastName();

        if (firstName != null && !firstName.trim().isEmpty() &&
                midName != null && !midName.trim().isEmpty() &&
                lastName != null && !lastName.trim().isEmpty()) {
            User friend = new User();
            friend.setFirstName(firstName);
            friend.setMidName(midName);
            friend.setLastName(lastName);

            if (!user.getFriends().contains(friend)) {
                user.getFriends().add(friend);
            }
        }*/
        userRepository.save(user);
        return mapUserEntityToResponse(user);
    }

    private City createCity(String cityName) {
        City city = new City();
        city.setName(cityName);
        return cityRepository.save(city);
    }

    private Hobby createHobby(String hobbyName) {
        Hobby hobby = new Hobby();
        hobby.setName(hobbyName);
        return hobbyRepository.save(hobby);
    }

    private Sport createSport(String sportName) {
        Sport sport = new Sport();
        sport.setName(sportName);
        return sportRepository.save(sport);
    }

    private Movie createMovie(String movieName) {
        Movie movie = new Movie();
        movie.setTitle(movieName);
        return movieRepository.save(movie);
    }

    private Activity createActivity(String activityName) {
        Activity activity = new Activity();
        activity.setName(activityName);
        return activityRepository.save(activity);
    }

    private MusicGenre createMusicGenre(String musicGenreName) {
        MusicGenre musicGenre = new MusicGenre();
        musicGenre.setGenre(musicGenreName);
        return musicGenreRepository.save(musicGenre);
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

}
