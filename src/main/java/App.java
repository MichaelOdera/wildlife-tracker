import models.EndangeredAnimal;
import models.NonEndangeredAnimal;
import models.Ranger;
import models.Sightings;
import spark.ModelAndView;
import spark.Spark;
import spark.route.HttpMethod;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static  spark.Spark.*;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import static spark.route.HttpMethod.post;

public class App{

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers/form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String rangername = request.queryParams("name");
            int bagdenumber = Integer.parseInt(request.queryParams("badgeNumber"));
            String rangeremail = request.queryParams("email");
            Ranger newRanger = new Ranger(rangername, bagdenumber, rangeremail);
            newRanger.save();
            request.session().attribute("name", rangername);
            request.session().attribute("badgenumber", bagdenumber);
            request.session().attribute("email", rangeremail);
            model.put("newRanger", newRanger);
            return new ModelAndView(model, "ranger-success.hbs");
        },new HandlebarsTemplateEngine());


        get("/rangers/list", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Ranger> rangers = Ranger.getAllRangers();
            model.put("rangers", rangers);
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/form", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sightings_form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new", (request, response)->{
            Map<String, Object> model= new HashMap<>();
            String animalId = request.queryParams("animalId");
            String animalLocation = request.queryParams("animalLocation");
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            Sightings newSightings = new Sightings(animalId,animalLocation,rangerId);
            newSightings.save();
            return new ModelAndView(model, "sightings-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/list", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            List<Sightings> allSightings = Sightings.allSightings();
            model.put("allSightings", allSightings);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animalName");
            String animalAge = request.queryParams("animalAge");
            String animalHealth = request.queryParams("animalHealth");
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(animalName, animalAge, animalHealth, rangerId);
            newEndangeredAnimal.save();
            model.put("newEndangeredAnimal", newEndangeredAnimal);
            return new ModelAndView(model, "endangered-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/list", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimal> allEndangeredAnimals = EndangeredAnimal.allEndangeredAnimals();
            model.put("allEndangeredAnimals", allEndangeredAnimals);
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine() );

        get("/non_endangered/form", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "non_endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/non_endangered/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animalName");
            String animalAge = request.queryParams("animalAge");
            String animalHealth = request.queryParams("animalHealth");
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            NonEndangeredAnimal newNonEndangeredAnimal = new NonEndangeredAnimal(animalName, animalAge, animalHealth, rangerId);
            newNonEndangeredAnimal.save();
            model.put("newNonEndangeredAnimal", newNonEndangeredAnimal);
            return new ModelAndView(model, "non_endangered-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/non_endangered/list", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            List<NonEndangeredAnimal> allNonEndangeredAnimals = NonEndangeredAnimal.allNonEndangeredAnimals();
            model.put("allNonEndangeredAnimals", allNonEndangeredAnimals);
            return new ModelAndView(model, "non_endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/sightings/:id", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            int findById = Integer.parseInt(request.params("id"));
            Ranger ranger = Ranger.findRangerById(findById);
            List<Sightings> allSightings = ranger.getSightings();
            model.put("ranger", ranger);
            model.put("allSightings", allSightings);
            return new ModelAndView(model, "ranger_animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/animals/:id", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            int findById = Integer.parseInt(request.params("id"));
            Ranger ranger = Ranger.findRangerById(findById);
            List<Object> allAnimals = ranger.getAnimals();
            model.put("allAnimals", allAnimals);
            return new ModelAndView(model, "ranger_endangered_and_nonendangered_animals.hbs");
        }, new HandlebarsTemplateEngine());


    }

}