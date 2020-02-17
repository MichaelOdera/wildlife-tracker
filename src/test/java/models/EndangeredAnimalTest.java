package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectlyAsAnimal_true(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void getAnimalName_returnsAnimalNameCorrectly_tiger(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("tiger", testEndangeredAnimal.getAnimalName());
    }

    @Test
    public void getAnimalAge_returnsAnimalAgeCorrectly_young(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("young", testEndangeredAnimal.getAnimalAge());
    }

    @Test
    public void getAnimalHealth_returnsAnimalHealthStatusCorrectly_okay(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals("okay", testEndangeredAnimal.getAnimalHealthStatus());
    }

    @Test
    public void getRangerId_returnsRangerIdCorrectly_12(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertEquals(12, testEndangeredAnimal.getRangerId());
    }

    @Test
    public void equals_returnsTrueIfNameAndAgeAndHealthAndRangerIdAreEqual_true(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        assertTrue(testEndangeredAnimal.equals(secondEndangeredAnimal));
    }

    @Test
    public void save_returnsTrueIfSavesInDbCorrectly() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.allEndangeredAnimals().get(0).equals(testEndangeredAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        EndangeredAnimal testEndangeredAnimal= new EndangeredAnimal("tiger", "young", "okay", 12);
        testEndangeredAnimal.save();
        EndangeredAnimal savedAnimal = EndangeredAnimal.allEndangeredAnimals().get(0);
        assertEquals(savedAnimal.getId(), testEndangeredAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("lion", "adult", "okay", 12);
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.allEndangeredAnimals().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.allEndangeredAnimals().get(1).equals(secondEndangeredAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("tiger", "young", "okay", 12);
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("lion", "adult", "okay", 12);
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.findAnimalById(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }


}