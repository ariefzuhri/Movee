package com.ariefzuhri.blu.data;

import java.util.List;

public class CreditEntity {

    private final int id;
    private final List<Cast> cast;
    private final List<Crew> crew;

    public CreditEntity(int id, List<Cast> cast, List<Crew> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public int getId(){
        return id;
    }

    public List<Cast> getCast(){
        return cast;
    }

    public List<Crew> getCrew(){
        return crew;
    }

    public static class Cast {

        private final int id;
        private final String name;
        private final String creditId;
        private final String profile;
        private final String knownForDepartment;
        private final String character;

        public Cast(int id, String name, String creditId, String profile, String knownForDepartment, String character) {
            this.id = id;
            this.name = name;
            this.creditId = creditId;
            this.profile = profile;
            this.knownForDepartment = knownForDepartment;
            this.character = character;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCreditId() {
            return creditId;
        }

        public String getProfile() {
            return profile;
        }

        public String getKnownForDepartment() {
            return knownForDepartment;
        }

        public String getCharacter() {
            return character;
        }
    }

    public static class Crew {

        private final int id;
        private final String name;
        private final String creditId;
        private final Object profile;
        private final String job;

        public Crew(int id, String name, String creditId, Object profile, String job) {
            this.id = id;
            this.name = name;
            this.creditId = creditId;
            this.profile = profile;
            this.job = job;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCreditId() {
            return creditId;
        }

        public Object getProfile() {
            return profile;
        }

        public String getJob() {
            return job;
        }
    }
}
