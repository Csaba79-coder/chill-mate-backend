package com.csaba79coder.chillmatebackend.util;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.entity.City;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ActivityResponse mapActivityEntityToResponse(Activity activity) {
        return modelMapper.map(activity, ActivityResponse.class);
    }

    public static CityResponse mapCityEntityToResponse(City city) {
        return modelMapper.map(city, CityResponse.class);
    }

    private Mapper() {
    }
}
