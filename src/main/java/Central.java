import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Central extends Topic {
    private List<Topic> childrenFloat = new ArrayList();

    Central(String _name) {
        super(_name);
    }

    public List<Topic> getChildrenFloat() {
        return childrenFloat;
    }

    public void addFloatingTopic(Topic... floatingTopic) {
        for (Topic item : floatingTopic) {
            this.childrenFloat.add(item);
            item.setParent(this);
        }
    }

    public void removeFloatingTopic(Topic... removeFloatingTopic) {
        for (var item : removeFloatingTopic) {
            List<Topic> newFloatingChildren = childrenFloat.stream()
                    .filter(element -> !element.equals(item))
                    .collect(Collectors.toList());
            this.childrenFloat = newFloatingChildren;
        }
    }

    public void changeTopicToFloatingTopic(Topic... changeTopic) {
        // remove child topic
        // add floating topic
        for (var item : changeTopic) {
            item.getParent().removeOneChildTopicById(item.getId());
            this.addFloatingTopic(item);
        }
    }

    public void changeFloatingTopicToTopic(List<Topic> floatingTopic, Topic destinationTopic) {
        for (var item : floatingTopic) {
            this.removeFloatingTopic(item);
            destinationTopic.appendChildTopic(item);
        }
    }
}

