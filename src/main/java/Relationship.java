import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Relationship {
    String id;
    String name;
    String endPointId;
    String beginPointId;

    //private List<Relationship> relationshipList = new ArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndPointId() {
        return endPointId;
    }

    public void setEndPointId(String endPointId) {
        this.endPointId = endPointId;
    }

    public String getBeginPointId() {
        return beginPointId;
    }

    public void setBeginPointId(String beginPointId) {
        this.beginPointId = beginPointId;
    }

    // constructor
    Relationship(String _beginPointId, String _endPointId) {
        this.id = UUID.randomUUID().toString();
        this.endPointId = _endPointId;
        this.beginPointId = _beginPointId;
    }

}
