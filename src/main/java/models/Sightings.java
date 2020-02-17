package models;

import org.sql2o.Connection;
import java.sql.Timestamp;

import java.util.List;
import java.util.Objects;

public class Sightings {
    private String animalLocation;
    private int rangerId;
    private String animalId;
    private int id;
    private Timestamp lastSeen;


    public Sightings(String animalId, String animalLocation, int rangerId){
        this.animalId = animalId;
        this.animalLocation = animalLocation;
        this.rangerId = rangerId;

    }

    public static List<Sightings> allSightings() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sightings sightings = (Sightings) o;
        return rangerId == sightings.rangerId &&
                id == sightings.id &&
                animalLocation.equals(sightings.animalLocation) &&
                animalId.equals(sightings.animalId);
    }

    @Override    public int hashCode() {
        return Objects.hash(animalLocation, rangerId, animalId, id);
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getAnimalLocation() {
        return animalLocation;
    }

    public int getRangerId() {
        return rangerId;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }


    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalid, animallocation, rangerid, lastseen) VALUES (:animalId, :animalLocation, :rangerId, now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("animalLocation", this.animalLocation)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static Sightings findSightingsById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sightings sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return sighting;
        }
    }

    public int getId() {
        return id;
    }
}
