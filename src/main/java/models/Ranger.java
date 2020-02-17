package models;

import org.sql2o.Connection;

import java.util.*;

public class Ranger {
    private String rangerName;
    private String email;
    private int badgeNumber;
    private int id;


    public String getRangerName() {
        return rangerName;
    }

    public Ranger(String rangerName, int badgeNumber, String email){
        this.rangerName = rangerName;
        this.badgeNumber = badgeNumber;
        this.email = email;
    }

    public static Ranger findRangerById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where id=:id";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranger ranger = (Ranger) o;
        return badgeNumber == ranger.badgeNumber &&
                id == ranger.id &&
                rangerName.equals(ranger.rangerName) &&
                email.equals(ranger.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangerName, email, badgeNumber, id);
    }

    public String getName() {
        return rangerName;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (rangername, badgenumber, email) VALUES (:rangerName, :badgeNumber, :email)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("badgeNumber", this.badgeNumber)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static ArrayList<Ranger> getAllRangers() {
        String sql = "SELECT * FROM rangers";
        try(Connection con = DB.sql2o.open()) {
            return (ArrayList<Ranger>) con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public List<Object> getAnimals() {
        List<Object> allAnimals = new ArrayList<Object>();

        try(Connection con = DB.sql2o.open()) {
            String sqlEndangered = "SELECT * FROM animals WHERE rangerId=:id AND type='endangered';";
            List<EndangeredAnimal> endangeredAnimals = con.createQuery(sqlEndangered)
                    .addParameter("id", this.id)
                    .executeAndFetch(EndangeredAnimal.class);
            allAnimals.addAll(endangeredAnimals);

            String sqlNonEndangered = "SELECT * FROM animals WHERE rangerId=:id AND type='nonendangered';";
            List<NonEndangeredAnimal> nonEndangeredAnimals = con.createQuery(sqlNonEndangered)
                    .addParameter("id", this.id)
                    .executeAndFetch(NonEndangeredAnimal.class);
            allAnimals.addAll(nonEndangeredAnimals);
        }

        return allAnimals;
    }


    public List<Sightings> getSightings() {
        List<Sightings> allSightings = new ArrayList<>();

        try(Connection con=DB.sql2o.open()){
            String sqlSightings = "SELECT * FROM sightings WHERE rangerId=:id";
            List<Sightings> sightings = con.createQuery(sqlSightings)
                    .addParameter("id", this.id)
                    .executeAndFetch(Sightings.class);
            allSightings.addAll(sightings);
        }
        return allSightings;
    }
}
