package com.animal.doginfo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "breed_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id")
    private Long id;
    private String breed_name;
    private Integer height_low_inches;
    private Integer height_high_inches;
    private Integer weight_low_lbs;
    private Integer weight_high_lbs;
    private String breed_classification;
    private String breed_obey;
    private Integer reps_lower;
    private Integer reps_upper;

    public Breed(){

    }

    public Long getBreed_id() {
        return id;
    }

    public void setBreed_id(Long id) {
        this.id = id;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }

    public Integer getHeight_low_inches() {
        return height_low_inches;
    }

    public void setHeight_low_inches(Integer height_low_inches) {
        this.height_low_inches = height_low_inches;
    }

    public Integer getHeight_high_inches() {
        return height_high_inches;
    }

    public void setHeight_high_inches(Integer height_high_inches) {
        this.height_high_inches = height_high_inches;
    }

    public Integer getWeight_low_lbs() {
        return weight_low_lbs;
    }

    public void setWeight_low_lbs(Integer weight_low_lbs) {
        this.weight_low_lbs = weight_low_lbs;
    }

    public Integer getWeight_high_lbs() {
        return weight_high_lbs;
    }

    public void setWeight_high_lbs(Integer weight_high_lbs) {
        this.weight_high_lbs = weight_high_lbs;
    }

    public String getBreed_classification() {
        return breed_classification;
    }

    public void setBreed_classification(String breed_classification) {
        this.breed_classification = breed_classification;
    }

    public String getBreed_obey() {
        return breed_obey;
    }

    public void setBreed_obey(String breed_obey) {
        this.breed_obey = breed_obey;
    }

    public Integer getReps_lower() {
        return reps_lower;
    }

    public void setReps_lower(Integer reps_lower) {
        this.reps_lower = reps_lower;
    }

    public Integer getReps_upper() {
        return reps_upper;
    }

    public void setReps_upper(Integer reps_upper) {
        this.reps_upper = reps_upper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return id.equals(breed.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
