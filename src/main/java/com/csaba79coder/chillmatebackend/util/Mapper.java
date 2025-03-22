package com.csaba79coder.chillmatebackend.util;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ActivityResponse mapActivityEntityToResponse(Activity activity) {
        return modelMapper.map(activity, ActivityResponse.class);
    }

    private Mapper() {
    }
}
