package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name="person")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private final UUID id;
    @NotBlank
    private final String name;
    private final String type;


    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("type") String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = "User";
    }

    public User(){
        this.id = UUID.randomUUID();
        this.name = "Unknown";
        this.type = "User";
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
