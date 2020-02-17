package models;
import org.sql2o.*;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

        @Override
        protected void before() {
            //DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "access");
            DB.sql2o = new Sql2o("jdbc:postgresql://\n" +
                    "ec2-3-213-192-58.compute-1.amazonaws.com:5432/d69poqm1vfa8j2", "eqektnogfzxvlj", "b6b4254d95f7fa260b547ab616a5004240579f5668fbcdd936e7abce85404ee2");
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
