package models;

import org.sql2o.Connection;

import java.util.List;

public class NonEndangeredAnimal extends Animal {

    private int probabilityOfNonSurvival;


    public static final int NORM_SURVIVAL_LEVEL = 6;
    public static final String DATABASE_TYPE ="nonendangered";

    public NonEndangeredAnimal(String animalName, String animalAge, String animalHealth, int rangerId){
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalHealth = animalHealth;
        this.rangerId = rangerId;
        this.probabilityOfNonSurvival = NORM_SURVIVAL_LEVEL/2;
        type = DATABASE_TYPE;
    }

    public int getProbabilityOfSurvival() {
        return probabilityOfNonSurvival;
    }

    public static NonEndangeredAnimal findAnimalById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id AND type='nonendangered';";
            NonEndangeredAnimal nonEndangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(NonEndangeredAnimal.class);
            return nonEndangeredAnimal;
        }
    }
    public static List<NonEndangeredAnimal> allNonEndangeredAnimals() {
        String sql = "SELECT * FROM animals WHERE type='nonendangered';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(NonEndangeredAnimal.class);
        }
    }

}
