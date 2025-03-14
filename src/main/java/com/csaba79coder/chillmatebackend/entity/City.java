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
public class City extends Identifier {

    private String city;

    @Relationship(type = "LIVES_IN")
    private List<User> residents;
}
