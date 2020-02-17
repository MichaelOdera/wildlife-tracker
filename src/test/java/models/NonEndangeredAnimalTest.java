package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonEndangeredAnimalTest {


    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectlyAsAnimal_true(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals(true,testNonEndangeredAnimal instanceof NonEndangeredAnimal);
    }

    @Test
    public void getAnimalName_returnsAnimalNameCorrectly_tiger(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("tiger", testNonEndangeredAnimal.getAnimalName());
    }

    @Test
    public void getAnimalAge_returnsAnimalAgeCorrectly_young(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("young", testNonEndangeredAnimal.getAnimalAge());
    }

    @Test
    public void getAnimalHealth_returnsAnimalHealthStatusCorrectly_okay(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("okay", testNonEndangeredAnimal.getAnimalHealthStatus());
    }

    @Test
    public void getRangerId_returnsRangerIdCorrectly_12(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals(12, testNonEndangeredAnimal.getRangerId());
    }

    @Test
    public void equals_returnsTrueIfNameAndAgeAndHealthAndRangerIdAreEqual_true(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertTrue(testNonEndangeredAnimal.equals(secondNonEndangeredAnimal));
    }

    @Test
    public void save_returnsTrueIfSavesInDbCorrectly() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        testNonEndangeredAnimal.save();
        assertTrue(NonEndangeredAnimal.allNonEndangeredAnimals().get(0).equals(testNonEndangeredAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        NonEndangeredAnimal testNonEndangeredAnimal= new NonEndangeredAnimal("tiger", "young", "okay", 12);
        testNonEndangeredAnimal.save();
        NonEndangeredAnimal savedEndangeredAnimal = NonEndangeredAnimal.allNonEndangeredAnimals().get(0);
        assertEquals(savedEndangeredAnimal.getId(),testNonEndangeredAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("lion", "adult", "okay", 12);
        secondNonEndangeredAnimal.save();
        assertEquals(true, NonEndangeredAnimal.allNonEndangeredAnimals().get(0).equals(firstNonEndangeredAnimal));
        assertEquals(true, NonEndangeredAnimal.allNonEndangeredAnimals().get(1).equals(secondNonEndangeredAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("lion", "adult", "okay", 12);
        secondNonEndangeredAnimal.save();
        assertEquals(NonEndangeredAnimal.findAnimalById(secondNonEndangeredAnimal.getId()), secondNonEndangeredAnimal);
    }

    @Test
    public void get_obtainTheProbabilityOfSurvival_3(){
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals(3, firstNonEndangeredAnimal.getProbabilityOfSurvival());
    }


}