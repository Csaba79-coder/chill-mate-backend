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
public class MusicGenre extends Identifier {

    private String genre;

    @Relationship(type = "LIKES_MUSIC")
    private List<User> users;
}

