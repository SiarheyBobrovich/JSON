package org.it_academy.courses.hibernate.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdatedStudent.Builder.class)
public class UpdatedStudent {

    private final Long id;
    private final String name;
    private final Integer age;
    private final Double score;
    private final Boolean olympicGamer;

    public UpdatedStudent(Long id, String name, Integer age, Double score, Boolean olympicGamer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getScore() {
        return score;
    }

    public Boolean getOlympicGamer() {
        return olympicGamer;
    }

    public static NewStudent.Builder create() {
        return new NewStudent.Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Long id;
        private String name;
        private Integer age;
        private Double score;
        private Boolean olympicGamer;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder setScore(Double score) {
            this.score = score;
            return this;
        }

        public Builder setOlympicGamer(Boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public UpdatedStudent build() {
            return new UpdatedStudent(this.id, this.name, this.age, this.score, this.olympicGamer);
        }
    }
}
