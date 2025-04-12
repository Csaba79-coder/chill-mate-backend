package com.csaba79coder.chillmatebackend.util;

import com.csaba79coder.chillmatebackend.entity.*;
import com.csaba79coder.chillmatebackend.model.*;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ActivityResponse mapActivityEntityToResponse(Activity activity) {
        return modelMapper.map(activity, ActivityResponse.class);
    }

    public static Activity mapActivityResponseToEntity(ActivityResponse activityResponse) {
        return modelMapper.map(activityResponse, Activity.class);
    }

    public static CityResponse mapCityEntityToResponse(City city) {
        return modelMapper.map(city, CityResponse.class);
    }

    public static City mapCityResponseToEntity(CityResponse cityResponse) {
        return modelMapper.map(cityResponse, City.class);
    }

    public static HobbyResponse mapHobbyEntityToResponse(Hobby hobby) {
        return modelMapper.map(hobby, HobbyResponse.class);
    }

    public static Hobby mapHobbyResponseToEntity(HobbyResponse hobbyResponse) {
        return modelMapper.map(hobbyResponse, Hobby.class);
    }

    public static MovieResponse mapMovieEntityToResponse(Movie movie) {
        return modelMapper.map(movie, MovieResponse.class);
    }

    public static Movie mapMovieResponseToEntity(MovieResponse movieResponse) {
        return modelMapper.map(movieResponse, Movie.class);
    }

    public static MusicGenreResponse mapMusicGenreEntityToResponse(MusicGenre musicGenre) {
        return modelMapper.map(musicGenre, MusicGenreResponse.class);
    }

    public static MusicGenre mapMusicGenreResponseToEntity(MusicGenreResponse musicGenreResponse) {
        return modelMapper.map(musicGenreResponse, MusicGenre.class);
    }

    public static SportResponse mapSportEntityToResponse(Sport sport) {
        return modelMapper.map(sport, SportResponse.class);
    }

    public static Sport mapSportResponseToEntity(SportResponse sportResponse) {
        return modelMapper.map(sportResponse, Sport.class);
    }

    public static UserResponse mapUserEntityToResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public static User mapUserResponseToEntity(UserResponse userResponse) {
        return modelMapper.map(userResponse, User.class);
    }

    // TODO this part brakes the existing part, so check and update if it is needed!
    /*static {
        modelMapper.typeMap(User.class, UserResponse.class).addMappings(mapper -> {
            mapper.map(User::getCity, UserResponse::setCity);
            mapper.map(User::getHobbies, UserResponse::setHobbies);
            mapper.map(User::getSports, UserResponse::setSports);
            mapper.map(User::getMusicGenres, UserResponse::setMusicGenres);
            mapper.map(User::getMovies, UserResponse::setMovies);
            mapper.map(User::getActivities, UserResponse::setActivities);

            mapper.using(ctx -> {
                List<User> friends = (List<User>) ctx.getSource();
                if (friends == null) return null;
                return friends.stream()
                        .map(friend -> modelMapper.map(friend, UserBasicResponse.class))
                        .toList();
            }).map(User::getFriends, UserResponse::setFriends);
        });

        modelMapper.typeMap(UserResponse.class, User.class).addMappings(mapper -> {
            mapper.map(UserResponse::getCity, User::setCity);
            mapper.map(UserResponse::getHobbies, User::setHobbies);
            mapper.map(UserResponse::getSports, User::setSports);
            mapper.map(UserResponse::getMusicGenres, User::setMusicGenres);
            mapper.map(UserResponse::getMovies, User::setMovies);
            mapper.map(UserResponse::getActivities, User::setActivities);

            mapper.using(ctx -> {
                List<UserBasicResponse> friendsResponse = (List<UserBasicResponse>) ctx.getSource();
                if (friendsResponse == null) return null;
                return friendsResponse.stream()
                        .map(friendResponse -> modelMapper.map(friendResponse, User.class))
                        .toList();
            }).map(UserResponse::getFriends, User::setFriends);
        });
    }*/

    private Mapper() {
    }
}
