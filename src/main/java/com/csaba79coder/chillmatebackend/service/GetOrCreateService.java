package com.csaba79coder.chillmatebackend.service;

// T - Entity; S - Request; U - Response
public interface GetOrCreateService<T, S, U> {

    U getOrCreate(String name);
}
