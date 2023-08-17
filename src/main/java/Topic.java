import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Topic {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Topic getParent() {
        return parent;
    }

    public void setParent(Topic parent) {
        this.parent = parent;
    }

    public List<Topic> getChildren() {
        return children;
    }

    public void setChildren(List<Topic> children) {
        this.children = children;
    }

    private String name;
    private String id;
    private String parentId;

    private Topic parent;

    //private List<String> childrenId = new ArrayList();
    private List<Topic> children = new ArrayList();

    public void appendChildTopic(Topic... childTopic) {
        for (Topic item : childTopic) {
            this.children.add(item);
            item.parent = this;
        }
    }

    public void removeOneChildTopicById(String removeTopicId) {
        // Filter and create a new children list without the topic that needs to be removed
        List<Topic> newChildren = new ArrayList();

        for (var item : this.children) {
            if (item.getId() != removeTopicId)
                newChildren.add(item);
        }
        this.children = newChildren;
    }

    public void removeMultipleChildTopic(String... multipleChildrenById) {
        for (var removeItem : multipleChildrenById) {
            this.removeOneChildTopicById(removeItem);
        }
    }

    public void removeMultipleSelectedTopicsById(List<String> selectedTopicIds) {
        List<Topic> remainingChildren = new ArrayList();

        for (Topic childTopic : this.children) {
            if (selectedTopicIds.contains(childTopic.getId())) { //Vì đây là id của con của nó chứ không phải this nên phải get
                selectedTopicIds.remove(childTopic);
                this.removeOneChildTopicById(childTopic.getId());
            } else {
                remainingChildren.add(childTopic);
                childTopic.removeMultipleSelectedTopicsById(selectedTopicIds);
            }
        }

        this.children = remainingChildren;
    }

    public void changeParentTopic(Topic moveTopic, Topic newParent) {
        // remove the topic from its old parent topic
        this.removeOneChildTopicById(moveTopic.getId());

        // append the topic to its new parent
        newParent.appendChildTopic(moveTopic);
    }

    // Constructor
    Topic(String _name) {
        this.name = _name;
        this.id = UUID.randomUUID().toString();
    }
}
