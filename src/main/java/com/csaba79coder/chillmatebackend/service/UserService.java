package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.entity.User;
import com.csaba79coder.chillmatebackend.model.ActivityRequest;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import com.csaba79coder.chillmatebackend.persistence.UserRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapActivityResponseToEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ActivityService activityService;
    private final CityService cityService;
    private final HobbyService hobbyService;
    //private final MovieService movieService;
    //private final MusicGenreService musicGenreService;
    //private final SportService sportService;

    // TODO it is creating new user with a list!!!
    // TODO update the existing list deleting to it, or add to it!
    public User createUserWithConnections(String firstName, String lastName, String cityName, List<String> activities) {
        // TODO create a new user with connections, save the node if not exists, etc.!
        List<Activity> currentActivities = new ArrayList<>();
        for (String activityName : activities) {
            ActivityResponse activityResponse = activityService.findByName(activityName);
            if (activityResponse != null) {
                currentActivities.add(mapActivityResponseToEntity(activityResponse));
            } else {
                ActivityResponse newActivity = activityService.createActivity(new ActivityRequest(activityName));
                currentActivities.add(mapActivityResponseToEntity(newActivity));
            }
        }

        // TODO hasolnóan a city-t is!
        CityResponse cityResponse = cityService.findByName(cityName);
        User user = User.builder()
                .fistName(firstName)
                .lastName(lastName)
                .activities(currentActivities)
                .city(Mapper.mapCityResponseToEntity(cityResponse))
                .build();
        return userRepository.save(user);
    }
}
