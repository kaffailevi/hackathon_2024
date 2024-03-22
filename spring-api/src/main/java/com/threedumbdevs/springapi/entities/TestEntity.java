package com.threedumbdevs.springapi.entities;

import jakarta.persistence.*;

public class TestEntity {

    @Entity
    @Table(name = "test")
    public class YourEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // other fields, getters, and setters
    }



}
