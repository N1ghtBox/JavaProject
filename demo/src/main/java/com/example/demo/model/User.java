package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "person")
public class User {


    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")

    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public User(@JsonProperty("name") String name,
                @JsonProperty("type") String type) {
        this.name = name;
        this.type = type;
    }

    public User(@JsonProperty("name") String name) {
        this.name = name;
        this.type = "User";
    }
    public User(){
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setId(Long newId){
        this.id = newId;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setType(String newType){
        this.type= newType;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
