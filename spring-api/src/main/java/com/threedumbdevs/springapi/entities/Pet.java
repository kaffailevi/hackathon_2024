package com.threedumbdevs.springapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Pet.TABLE_NAME)
public class Pet {

    public static final String TABLE_NAME = "pet";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breedName;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location lastLocation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pet_friends",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Pet> friends = new HashSet<>();

    //favourite locations
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pet_favourite_locations",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> favouriteLocations = new HashSet<>();

    @Column
    private String profilePictureName;





}
