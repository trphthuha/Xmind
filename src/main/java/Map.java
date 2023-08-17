import java.util.List;
import java.util.ArrayList;

public class Map {
    public List<Relationship> getRelationshipList() {
        return relationshipList;
    }

    List<Relationship> relationshipList = new ArrayList();

    public void createNewRelationship(Relationship... addRelationship) {
        for (var item : addRelationship) {
            this.relationshipList.add(item);
        }
    }

    public void removeRelationshipById(String removeRelationshipId) {
        List<Relationship> newRelationshipList = new ArrayList();

        for (var relationship : this.relationshipList) {
            if (relationship.getId() != removeRelationshipId)
                newRelationshipList.add(relationship);
        }
        this.relationshipList = newRelationshipList;
    }

    public void removeMultipleRelationshipsById(String... removeRelationshipIds) {
        for (var item : removeRelationshipIds) {
            this.removeRelationshipById(item);
        }
    }

    public void changeRelationshipBeginPoint(String relationshipId, String newBeginPointId) {
        for (var item : relationshipList) {
            if (relationshipId.equals(item.getId())) {
                item.setBeginPointId(newBeginPointId);
            }
        }
    }

    public void changeRelationshipEndPoint(String relationshipId, String newEndPointId) {
        for (var item : relationshipList) {
            if (relationshipId.equals(item.getId())) {
                item.setEndPointId(newEndPointId);
            }
        }
    }


}
