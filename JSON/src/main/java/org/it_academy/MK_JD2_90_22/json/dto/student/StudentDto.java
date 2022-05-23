package org.it_academy.MK_JD2_90_22.json.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = StudentDto.Builder.class)
public class StudentDto {

    private long id;
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public StudentDto(String name, int age, double score, boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public static Builder create() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private long id;
        private String name;
        private int age;
        private double score;
        private boolean olympicGamer;

        @JsonProperty(value = "id", defaultValue = "0")
        public Builder setId(long id) {
            this.id = id;
            return this;
        }

//        @JsonProperty(value = "name")
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
//        @JsonProperty(value = "age")
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

//        @JsonProperty(value = "score")
        public Builder setScore(double score) {
            this.score = score;
            return this;
        }

        public Builder setOlympicGamer(boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public StudentDto build() {
            StudentDto studentDto = new StudentDto(name, age, score, olympicGamer);
            studentDto.id = this.id;
            return studentDto;
        }
    }

}
