package models;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    public void ranger_instantiatesCorrectly_true(){
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test
    public void getName_returnsCorrectName_monty(){
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        assertEquals("monty", testRanger.getName());
    }

    @Test
    public void getBadgeNumber_returnsCorrectBadgeNumber_1234(){
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        assertEquals(12342, testRanger.getBadgeNumber());
    }

    @Test
    public void getEmail_returnsCorrectEmail_jugeathotmail(){
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        assertEquals("juge@hotmail.com", testRanger.getEmail());

    }

    @Test
    public void equals_returnsTrueIfNameAndBadgeNumberAndEmailAreSame_true() {
        Ranger firstRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        Ranger anotherRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        assertTrue(firstRanger.equals(anotherRanger));
    }


    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        testRanger.save();
        Ranger savedRanger = Ranger.getAllRangers().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }


    @Test
    public void allRangers_returnsAllInstancesOfRangers_true() {
        Ranger firstRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        firstRanger.save();
        Ranger secondRanger = new Ranger("python", 5678,"python@hotmail.com");
        secondRanger.save();
        assertEquals(true, Ranger.getAllRangers().get(0).equals(firstRanger));
        assertEquals(true, Ranger.getAllRangers().get(1).equals(secondRanger));
    }

    @Test
    public void findRangerById_returnsCorrectRangerIfFindById_true(){
        Ranger firstRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        firstRanger.save();
        Ranger secondRanger = new Ranger("python", 5678,"python@hotmail.com");
        secondRanger.save();
        assertEquals(Ranger.findRangerById(secondRanger.getId()), secondRanger);
    }


    @Test
    public void getAnimals_retrievesAllAnimalsFromDatabase_animalsList() {
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        testRanger.save();
        NonEndangeredAnimal firstAnimal = new NonEndangeredAnimal("tiger", "young", "okay", testRanger.getId());
        firstAnimal.save();
        EndangeredAnimal secondAnimal= new EndangeredAnimal("tiger", "young", "okay", testRanger.getId());
        secondAnimal.save();
        Object[] animals = new Object[] { firstAnimal, secondAnimal };
        assertTrue(testRanger.getAnimals().containsAll(Arrays.asList(animals)));
    }

    @Test
    public void getSightings_retrievesAllSightingsOccurrences_sightingsList(){
        Ranger testRanger = new Ranger("monty", 12342, "juge@hotmail.com");
        testRanger.save();
        Sightings testSightings = new Sightings("1400", "borehole", testRanger.getId());
        testSightings.save();
        Sightings secondSightings = new Sightings("1700", "forest", testRanger.getId());
        secondSightings.save();
        Object[] sightings = new Object[] {testSightings, secondSightings};
        assertTrue(testRanger.getSightings().containsAll(Arrays.asList(sightings)));

    }



}