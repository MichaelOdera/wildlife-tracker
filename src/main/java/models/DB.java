package models;

import org.sql2o.Sql2o;

public class DB {
    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "access");
    //postgresql-elliptical-75578
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-184-72-235-159.compute-1.amazonaws.com:5432/d8pqeqatuugh4i", "vcuumdduvjkxkw", "\n" +
            "9a3ba1380e82ffb67eea7d65b91092fd8b60652989d7c3837c5440c78ba503d3");

}
