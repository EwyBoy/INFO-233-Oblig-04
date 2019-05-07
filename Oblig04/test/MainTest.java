import no.uib.info233.tree.LinkedBinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static LinkedBinarySearchTree searchTree = new LinkedBinarySearchTree();

    @Test
    void main() {

        Comparable<String> apple = "apple";
        Comparable<String> orange = "orange";
        Comparable<String> watermelon = "watermelon";
        Comparable<String> grape = "grape";
        Comparable<String> mango = "mango";

        assertEquals(searchTree.isEmpty(), true);

        searchTree.add(apple);
        assertEquals(searchTree.getNumberOfNodes(), 1);

        searchTree.add(orange);
        searchTree.add(watermelon);
        searchTree.add(grape);
        searchTree.add(mango);

        assertEquals(searchTree.getNumberOfNodes(), 5);

        searchTree.add(apple);
        assertEquals(searchTree.getNumberOfNodes(), 5);

        searchTree.remove(orange);
        searchTree.remove(grape);
        assertEquals(searchTree.getNumberOfNodes(), 3);
        assertEquals(searchTree.getEntry(orange), null);
        assertEquals(searchTree.getEntry(mango), "mango");

        searchTree.remove(apple);
        assertNotEquals(searchTree.getEntry(apple), "apple");

        searchTree.clear();
        assertEquals(searchTree.isEmpty(), true);

    }


}