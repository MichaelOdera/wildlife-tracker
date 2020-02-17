package models;
import org.sql2o.*;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

        @Override
        protected void before() {
            DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "access");
        }

        @Override
        protected void after() {
            try(Connection con = DB.sql2o.open()) {
                String deleteRangersQuery = "DELETE FROM rangers *;";
                String deleteAnimalsQuery = "DELETE FROM animals *;";
                String deleteSightingsQuery = "DELETE FROM sightings*;";
                con.createQuery(deleteRangersQuery).executeUpdate();
                con.createQuery(deleteAnimalsQuery).executeUpdate();
                con.createQuery(deleteSightingsQuery).executeUpdate();
            }
        }
}
