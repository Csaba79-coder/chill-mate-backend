package com.csaba79coder.chillmatebackend.entity;

import com.csaba79coder.chillmatebackend.entity.base.Identifier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Getter
@Setter
public class User extends Identifier {

    private String fistName;
    private String lastName;
    private String midName;

    @Relationship(type = "IS_FRIEND_WITH")
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
