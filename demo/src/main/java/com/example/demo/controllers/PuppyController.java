package com.example.demo.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PuppyController {
    class Puppy {
        private String name;
        private String breed;
        private String color;
        public Puppy(String name, String breed, String color) {
            this.name = name;
            this.breed = breed;
            this.color = color;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getBreed() {
            return breed;
        }
        public void setBreed(String breed) {
            this.breed = breed;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
    }

    private List<Puppy> puppies = new ArrayList<Puppy>();

    public PuppyController() {
        Puppy p1 = new Puppy("Buddy", "Golden Retriever", "Golden");
        Puppy p2 = new Puppy("Max", "German Shepherd", "Black/Brown");
        Puppy p3 = new Puppy("Charlie", "Bulldog", "White");
        puppies.add(p1);
        puppies.add(p2);
        puppies.add(p3);
    }

    @GetMapping("/puppies")   
    public List<Puppy> getAllPuppies() {
        return puppies;
    }
        
}
