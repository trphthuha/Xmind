import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopicTest {
    @Test
    public void testAppendChild() {
        var mainTopic1 = new Topic("Main Topic 1 ID");
        var subTopic1 = new Topic("Subtopic 1 ID");
        var subTopic2 = new Topic("Subtopic 2 ID");

        mainTopic1.appendChildTopic(subTopic1, subTopic2);

        assertEquals(2, mainTopic1.getChildren().size());
    }

    @Test
    public void testRemoveOneChildTopic() {
        var mainTopic = new Topic("Main Topic 1");
        var subTopic1 = new Topic("Subtopic 1");
        var subTopic2 = new Topic("Subtopic 2");
        var subTopic1_1 = new Topic("Subtopic 1");
        var subTopic1_2 = new Topic("Subtopic 1");

        mainTopic.appendChildTopic(subTopic1, subTopic2);
        subTopic1.appendChildTopic(subTopic1_1, subTopic1_2);

        assertEquals(2, mainTopic.getChildren().size());

        mainTopic.removeOneChildTopicById(subTopic1.getId());

        assertEquals(1, mainTopic.getChildren().size());
    }

    @Test
    public void testRemoveMultipleChildTopic() {
        var mainTopic1 = new Topic("Main Topic 1 ID");
        var mainTopic2 = new Topic("Main Topic 1 ID");
        var subTopic1 = new Topic("Subtopic 1 ID");
        var subTopic2 = new Topic("Subtopic 2 ID");
        var subTopic3 = new Topic("Subtopic 2 ID");

        mainTopic1.appendChildTopic(subTopic1, subTopic2);
        mainTopic2.appendChildTopic(subTopic3);

        assertEquals(2, mainTopic1.getChildren().size());
        assertEquals(1, mainTopic2.getChildren().size());

        mainTopic1.removeMultipleChildTopic(subTopic1.getId(), subTopic2.getId());

        assertEquals(0, mainTopic1.getChildren().size());
        assertEquals(1, mainTopic2.getChildren().size());
    }

    @Test
    public void testRemoveMultipleSelectedTopics() {
        var central = new Central("Central");
        var mainTopic1 = new Topic("Main Topic 1");
        var mainTopic2 = new Topic("Main Topic 1");
        var subTopic1_1 = new Topic("Subtopic 1");
        var subTopic1_2 = new Topic("Subtopic 2");
        var subTopic2_1 = new Topic("Subtopic 1");
        var subTopic2_2 = new Topic("Subtopic 2");
        var subTopic2_1_1 = new Topic("Subtopic 2");

        central.appendChildTopic(mainTopic1, mainTopic2);
        mainTopic1.appendChildTopic(subTopic1_1, subTopic1_2);
        mainTopic2.appendChildTopic(subTopic2_1, subTopic2_2);
        subTopic2_1.appendChildTopic(subTopic2_1_1);

        assertEquals(2, central.getChildren().size());
        assertEquals(2, mainTopic1.getChildren().size());
        assertEquals(2, mainTopic1.getChildren().size());
        assertEquals(2, mainTopic2.getChildren().size());
        assertEquals(1, subTopic2_1.getChildren().size());

        List<String> myList = new ArrayList();
        myList.add(subTopic1_2.getId());
        myList.add(subTopic2_1_1.getId());

        central.removeMultipleSelectedTopicsById(myList);

        assertEquals(2, central.getChildren().size());
        assertEquals(1, mainTopic1.getChildren().size());
        assertEquals(2, mainTopic2.getChildren().size());
        assertEquals(0, subTopic2_1.getChildren().size());

    }

    @Test
    public void testChangeParentTopic() {
        var mainTopic = new Topic("Main Topic 1");
        var subTopic1 = new Topic("Subtopic 1");
        var subTopic2 = new Topic("Subtopic 2");
        var subTopic1_1 = new Topic("Subtopic 1");
        var subTopic1_2 = new Topic("Subtopic 1");

        mainTopic.appendChildTopic(subTopic1, subTopic2);
        subTopic1.appendChildTopic(subTopic1_1, subTopic1_2);

        subTopic1.changeParentTopic(subTopic1_1, subTopic2);

        // verify that the subtopic was successfully removed from subTopic1 and appended to subTopic2.
        assertFalse(subTopic1.getChildren().contains(subTopic1_1));
        assertTrue(subTopic2.getChildren().contains(subTopic1_1));
    }
}

