package com.csaba79coder.chillmatebackend.service;

import com.csaba79coder.chillmatebackend.entity.Activity;
import com.csaba79coder.chillmatebackend.entity.User;
import com.csaba79coder.chillmatebackend.model.ActivityRequest;
import com.csaba79coder.chillmatebackend.model.ActivityResponse;
import com.csaba79coder.chillmatebackend.model.CityResponse;
import com.csaba79coder.chillmatebackend.model.UserResponse;
import com.csaba79coder.chillmatebackend.persistence.UserRepository;
import com.csaba79coder.chillmatebackend.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.csaba79coder.chillmatebackend.util.Mapper.mapActivityResponseToEntity;

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


    // TODO created model for searching easy common friends! UserCommonFriendsModel (saving time! and resources)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapper::mapUserEntityToResponse)
                .collect(Collectors.toList());
    }

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

    // TODO it is just example, not implemented yet for both way connections!
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
