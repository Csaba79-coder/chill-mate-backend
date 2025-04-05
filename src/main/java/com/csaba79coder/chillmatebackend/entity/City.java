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
public class City extends Identifier {

    private String name;

    @Relationship(type = "LIVES_IN")
    private List<User> users;
}
