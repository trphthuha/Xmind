import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CentralTest {
    @Test
    public void testHasTopicChildren() {
        var Central = new Topic("Central idea");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 1");

        Central.appendChildTopic(mainTopic1);
        Central.appendChildTopic(mainTopic2);

        assertEquals(2, Central.getChildren().size());
    }

    @Test
    public void testAddFloatingTopic() {
        Central centralRoot = new Central("Central idea");
        Topic floating1 = new Topic("Floating Topic 1");

        centralRoot.addFloatingTopic(floating1);

        assertEquals(1, centralRoot.getChildrenFloat().size());
    }

    @Test
    public void testRemoveFloatingTopic() {
        Central centralRoot = new Central("Central idea");
        Topic floating1 = new Topic("Floating Topic 1");
        Topic floating2 = new Topic("Floating Topic 1");

        centralRoot.addFloatingTopic(floating1, floating2);

        assertEquals(2, centralRoot.getChildrenFloat().size());

        centralRoot.removeFloatingTopic(floating2);

        assertEquals(1, centralRoot.getChildrenFloat().size());
    }

    @Test
    public void testChangeTopicToFloatingTopic() {
        Central centralRoot = new Central("Central idea");
        Topic mainTopic = new Topic("Main Topic 1");
        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic subTopic1 = new Topic("Subtopic 1");

        centralRoot.appendChildTopic(mainTopic, mainTopic1);
        mainTopic.appendChildTopic(subTopic1);

        assertEquals(2, centralRoot.getChildren().size()); //central root has Main Topic
        assertEquals(1, mainTopic.getChildren().size()); //Main Topic 1 has 1 Subtopic

        centralRoot.changeTopicToFloatingTopic(subTopic1);

        assertEquals(2, centralRoot.getChildren().size());
        assertEquals(0, mainTopic.getChildren().size());
        assertEquals(1, centralRoot.getChildrenFloat().size());
    }

    @Test
    public void testChangeFloatingTopicToTopic() {
        Central centralRoot = new Central("Central idea");
        Topic mainTopic1 = new Topic("Main Topic 1");
        Topic mainTopic2 = new Topic("Main Topic 1");
        Topic floating1 = new Topic("Floating Topic 1");
        Topic floating2 = new Topic("Floating Topic 1");

        centralRoot.appendChildTopic(mainTopic1, mainTopic2);
        centralRoot.addFloatingTopic(floating1, floating2);

        assertEquals(2, centralRoot.getChildren().size());
        assertEquals(2, centralRoot.getChildrenFloat().size());

        List<Topic> myList = new ArrayList();
        myList.add(floating1);
        myList.add(floating2);

        centralRoot.changeFloatingTopicToTopic(myList, mainTopic2);

        assertEquals(2, centralRoot.getChildren().size());
        assertEquals(0, centralRoot.getChildrenFloat().size());
        assertEquals(2, mainTopic2.getChildren().size());

    }
}