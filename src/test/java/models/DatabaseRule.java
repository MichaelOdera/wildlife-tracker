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
                    "ec2-184-72-235-159.compute-1.amazonaws.com:5432/d6rj62th8a8uph", "zvoccfjxuezrxw", "ca911dc6f855529f806ddaebaf7373ac8a616f3d000c789af5993690150f89e7");
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
