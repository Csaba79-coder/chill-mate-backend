package com.csaba79coder.chillmatebackend.entity;

import com.csaba79coder.chillmatebackend.entity.base.Identifier;
import lombok.*;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Identifier {

    private String fistName;
    private String lastName;
    private String midName;

    // this allows a both way connection (the direction!)
    @Relationship(type = "IS_FRIEND_WITH", direction = Relationship.Direction.OUTGOING)
    private List<User> friends;

    @Relationship(type = "LIVES_IN")
    private City city;

    @Relationship(type = "LIKES_HOBBY")
    private List<Hobby> hobbies;

    @Relationship(type = "PLAYS_SPORT")
    private List<Sport> sports;

    @Relationship(type = "LIKES_MUSIC")
    private List<MusicGenre> musicGenres;

    @Relationship(type = "LIKES_MOVIE")
    private List<Movie> movies;

    @Relationship(type = "WANTS_TO_ATTEND")
    private List<Activity> activities;
}
