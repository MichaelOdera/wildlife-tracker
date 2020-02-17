package models;

import org.sql2o.Sql2o;

public class DB {
    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "access");
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-52-202-185-87.compute-1.amazonaws.com:5432/dk8qkbfoh7hbj", "ljjjqbmztautkv", "\n" +
            "a8773b64bb1d1083f9ba73bdfcf93734bb028b6afcdd33e8e93bb8e3fc773cb5");

}
