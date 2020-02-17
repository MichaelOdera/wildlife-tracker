package models;

import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Animal {
    public static final String DATABASE_TYPE = "endangered";

    public EndangeredAnimal(String animalName, String animalAge, String animalHealth, int rangerId){
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalHealth = animalHealth;
        this.rangerId = rangerId;
        type = DATABASE_TYPE;
    }


    public static EndangeredAnimal findAnimalById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id AND type='endangered';";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        }
    }

    public static List<EndangeredAnimal> allEndangeredAnimals() {
        String sql = "SELECT * FROM animals where type='endangered';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static String getDatabaseType() {
        return DATABASE_TYPE;
    }
}
