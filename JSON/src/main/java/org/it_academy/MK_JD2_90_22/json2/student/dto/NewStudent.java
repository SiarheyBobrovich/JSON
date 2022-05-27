package org.it_academy.MK_JD2_90_22.json2.student.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = NewStudent.Builder.class)
public class NewStudent {
    private final String name;
    private final Integer age;
    private final Double score;
    private final Boolean olympicGamer;

    private NewStudent(String name, Integer age, Double score, Boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
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

    public static Builder create() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String name;
        private Integer age;
        private Double score;
        private Boolean olympicGamer;

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

        public NewStudent build() {
            return new NewStudent(this.name, this.age, this.score, this.olympicGamer);
        }
    }
}
