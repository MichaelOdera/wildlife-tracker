package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public abstract class Animal {
    public String type;
    public String animalName;
    public String animalAge;



    public String animalHealth;
    public int id;
    public int rangerId;

    public static final String BEST_HEALTH = "healthy";
    public static final String MID_HEALTH = "okay";
    public static final String BAD_HEALTH = "ill";

    public static final String OLD_AGE = "adult";
    public static final String MIDDLE_AGE = "young";
    public static final String INFANT_AGE = "newborn";







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                rangerId == animal.rangerId &&
                animalName.equals(animal.animalName) &&
                animalAge.equals(animal.animalAge) &&
                animalHealth.equals(animal.animalHealth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalName, animalAge, animalHealth, id, rangerId);
    }



    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (animalname, animalage, animalhealth, rangerid, type) VALUES (:animalName, :animalAge, :animalHealth, :rangerId, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalName", this.animalName)
                    .addParameter("animalAge", this.animalAge)
                    .addParameter("animalHealth", this.animalHealth)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();

        }
    }


    public String getAnimalName() {
        return animalName;
    }



    public String getAnimalAge() {
        if(animalAge.equals("adult")){
            animalAge = OLD_AGE;
        }
        else if(animalAge.equals("young")){
            animalAge = MIDDLE_AGE;
        }else{
            animalAge = INFANT_AGE;
        }
        return animalAge;
    }

    public String getAnimalHealthStatus() {
        if(animalHealth.equals("healthy")){
            animalHealth = BEST_HEALTH;
        }
        else if(animalHealth.equals("okay")){
            animalHealth = MID_HEALTH;
        }
        else{
            animalHealth = BAD_HEALTH;
        }
        return animalHealth;
    }

    public int getRangerId() {
        return rangerId;
    }


    public int getId() {
        return id;
    }

    public String getAnimalHealth() {
        return animalHealth;
    }


}
