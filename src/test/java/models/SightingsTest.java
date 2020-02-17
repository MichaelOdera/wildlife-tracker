package models;

import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class SightingsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void instance_instantiatesCorrectly_true(){
        Sightings testSightings = new Sightings("1200","town", 12);
        assertTrue(testSightings instanceof Sightings);
    }

    @Test
    public void getAnimalId_returnsCorrectlyIdOfAnimal_1200(){
        Sightings testSightings = new Sightings("1200","town", 12);
        assertEquals("1200", testSightings.getAnimalId());
    }

    @Test
    public void getLocation_returnsCorrectlyLocationSpotted_town(){
        Sightings testSightings = new Sightings("1200","town", 12);
        assertEquals("town", testSightings.getAnimalLocation());
    }

    @Test
    public void getRangerId_returnsCorrectlyRangerId_12(){
        Sightings testSightings = new Sightings("1200","town", 12);
        assertEquals(12, testSightings.getRangerId());
    }

    @Test
    public void equals_returnsTrueIfTheEntriesAreTheSame_true(){
        Sightings firstSightings = new Sightings("1200","town", 12);
        Sightings secondSightings = new Sightings("1200","town", 12);
        assertTrue(firstSightings.equals(secondSightings));
    }

    @Test
    public void save_correctlySavesInTheDatabase_true(){
        Sightings testSightings = new Sightings("1200","town", 12);
        testSightings.save();
        Sightings savedSightings = Sightings.allSightings().get(0);
        assertEquals(testSightings.getId(), savedSightings.getId());
    }

    @Test
    public void save_returnCorrectIndividualSighting(){
        Sightings firstSightings = new Sightings("1200","town", 12);
        firstSightings.save();
        Sightings secondSightings = new Sightings("1220","rural", 10);
        secondSightings.save();
        assertTrue(Sightings.allSightings().get(0).equals(firstSightings));
        assertTrue(Sightings.allSightings().get(1).equals(secondSightings));
    }

    @Test
    public void save_returnsAllInstancesOfSightings_true(){
        Sightings firstSightings = new Sightings("1200","town", 12);
        firstSightings.save();
        Sightings secondSightings = new Sightings("1220","rural", 10);
        secondSightings.save();
        assertEquals(Sightings.findSightingsById(firstSightings.getId()), firstSightings);
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sightings testSighting = new Sightings("1242", "town", 2);
        testSighting.save();
        Timestamp savedSightingLastSeen = Sightings.findSightingsById(testSighting.getId()).getLastSeen();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedSightingLastSeen.getDay());
    }

}