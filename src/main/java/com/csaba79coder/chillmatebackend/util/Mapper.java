package com.csaba79coder.chillmatebackend.util;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.entity.City;
import com.csaba79coder.chillmatebackend.entity.Hobby;
import com.csaba79coder.chillmatebackend.entity.Movie;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import com.csaba79coder.chillmatebackend.model.HobbyResponse;
import com.csaba79coder.chillmatebackend.model.MovieResponse;
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

    private Mapper() {
    }
}
