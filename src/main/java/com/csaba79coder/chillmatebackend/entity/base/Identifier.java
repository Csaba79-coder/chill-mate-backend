package com.csaba79coder.chillmatebackend.entity.base;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.io.Serializable;
import java.util.UUID;

@Node
@Getter
public class Identifier implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;
}