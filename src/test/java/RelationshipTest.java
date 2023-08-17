import static org.junit.Assert.*;

import org.junit.Test;

public class RelationshipTest {
    @Test
    public void testCreateNewRelationship() {
        var map = new Map();
        var relationship1 = new Relationship("Begin point Id 1", "Begin point Id 2");

        map.createNewRelationship(relationship1);

        assertEquals(1, map.getRelationshipList().size());
    }

    @Test
    public void testRemoveRelationshipById() {
        var map = new Map();
        var relationship1 = new Relationship("Begin point Id 1", "Begin point Id 2");
        var relationship2 = new Relationship("Begin point Id 3", "Begin point Id 4");

        map.createNewRelationship(relationship1, relationship2);

        assertEquals(2, map.getRelationshipList().size());

        map.removeRelationshipById(relationship2.getId());

        assertEquals(1, map.getRelationshipList().size());
    }

    @Test
    public void testRemoveMultipleRelationshipsById() {
        var map = new Map();
        var relationship1 = new Relationship("Begin point Id 1", "Begin point Id 2");
        var relationship2 = new Relationship("Begin point Id 3", "Begin point Id 4");
        var relationship3 = new Relationship("Begin point Id 7", "Begin point Id 8");

        map.createNewRelationship(relationship1, relationship2, relationship3);

        assertEquals(3, map.getRelationshipList().size());

        map.removeMultipleRelationshipsById(relationship1.getId(), relationship2.getId());

        assertEquals(1, map.getRelationshipList().size());
    }

    @Test
    public void testChangeRelationshipPoint() {
        var map = new Map();
        var relationship1 = new Relationship("Begin point Id 1", "End point Id 2");

        map.createNewRelationship(relationship1);

        map.changeRelationshipBeginPoint(relationship1.getId(), "Begin point Id 3");

        assertEquals("Begin point Id 3", relationship1.getBeginPointId());
        assertEquals("End point Id 2", relationship1.getEndPointId());
    }

    @Test
    public void testChangeRelationshipEndPoint() {
        var map = new Map();
        var relationship1 = new Relationship("Begin point Id 1", "End point Id 2");

        map.createNewRelationship(relationship1);

        map.changeRelationshipEndPoint(relationship1.getId(), "End point Id 4");

        assertEquals("Begin point Id 1", relationship1.getBeginPointId());
        assertEquals("End point Id 4", relationship1.getEndPointId());
    }

}