package org.it_academy.MK_JD2_90_22.json.dto.student;

public class StudentDto {

    private long id;
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public StudentDto(long id, String name, int age, double score, boolean olympicGamer) {
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

    public static class Builder {
        private long id;

        private String name;
        private int age;
        private double score;
        private boolean olympicGamer;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setScore(double score) {
            this.score = score;
            return this;
        }

        public Builder setOlympicGamer(boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public StudentDto build() {
            return new StudentDto(id, name, age, score, olympicGamer);
        }
    }

}
