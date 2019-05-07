package no.uib.info233.drivers;import java.util.Iterator;import no.uib.info233.tree.LinkedBinaryTree;import no.uib.info233.tree.BinaryTree;/** * A driver that demonstrates the class BinaryTree. *  * @author Frank M. Carrano * @author Timothy M. Henry * @version 4.2 */public class DriverBT {    public static void main(String[] args)    {        LinkedBinaryTree<String> aTree = new LinkedBinaryTree<>();        createTree1(aTree);        showTreeStats(aTree, "A", 3, 7);        testTraversals(aTree, "A B D E C F G", "D B E A F C G", "D E B F G C A", "A B C D E F G");        aTree.clear();        createTree2(aTree);        showTreeStats(aTree, "A", 3, 6);        testTraversals(aTree, "A B E C F G", "B E A F C G", "E B F G C A", "A B C E F G");        aTree.clear();        createTree3(aTree);        showTreeStats(aTree, "A", 4, 7);        testTraversals(aTree, "A B D E C F G", "D B E A F G C", "D E B G F C A", "A B C D E F G");        aTree.clear();        createTree4(aTree);        showTreeStats(aTree, "A", 4, 8);        testTraversals(aTree, "A B D H E C F G", "D H B E A F C G", "H D E B F G C A", "A B C D E F G H");        aTree.clear();        createTree5(aTree);        showTreeStats(aTree, "A", 4, 8);        testTraversals(aTree, "A B D E H C F G", "D B E H A F C G", "D H E B F G C A", "A B C D E F G H");        aTree.clear();        createTree6(aTree);        showTreeStats(aTree, "A", 4, 8);        testTraversals(aTree, "A B D E F G C H", "D B F E G A C H", "D F G E B H C A", "A B C D E H F G");        aTree.clear();        createTree7(aTree);        showTreeStats(aTree, "A", 4, 11);        testTraversals(aTree, "A B D E F G B D E F G", "D B F E G A D B F E G", "D F G E B D F G E B A",                "A B B D E D E F G F G");        testEmptyTree();        TestSeg2321(); // Test the code in Segment 23.21 of Chapter 23        System.out.println("Done.");    }    /** Tests the 4 traversals of a given binary tree. */    public static void testTraversals(LinkedBinaryTree<String> aTree, String preorder, String inorder, String postorder,            String levelOrder) {        testPreorder(aTree, preorder);        testInorder(aTree, inorder);        testPostorder(aTree, postorder);        testLevelOrder(aTree, levelOrder);    }    /** Precondition: tree is empty but not null. */    public static void createTree1(LinkedBinaryTree<String> tree) {        // Leaves        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>("D");        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        // Subtrees:        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", dTree, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", fTree, gTree);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 1:\n");        System.out.println("     A      ");        System.out.println("   /   \\   "); // '\\' is the escape character for backslash        System.out.println("  B     C   ");        System.out.println(" / \\   / \\");        System.out.println("D   E  F  G ");        System.out.println();    }    public static void createTree2(LinkedBinaryTree<String> tree) // B has no left child    {        // Leaves        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        // Subtrees:        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", null, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", fTree, gTree);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 2:\n");        System.out.println("     A      ");        System.out.println("   /   \\   ");        System.out.println("  B     C   ");        System.out.println("   \\   / \\");        System.out.println("    E  F  G ");        System.out.println();    }    public static void createTree3(LinkedBinaryTree<String> tree) {        // Leaves        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>("D");        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        // Subtrees:        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F", null, gTree);        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", dTree, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", fTree, null);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 3:\n");        System.out.println("     A      ");        System.out.println("   /   \\  ");        System.out.println("  B     C  ");        System.out.println(" / \\   /  ");        System.out.println("D   E  F   ");        System.out.println("        \\ ");        System.out.println("         G ");        System.out.println();    }    public static void createTree4(LinkedBinaryTree<String> tree) // D has H as right child    {        // Leaves        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        LinkedBinaryTree<String> hTree = new LinkedBinaryTree<>("H");        // Subtrees:        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>("D", null, hTree);        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", dTree, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", fTree, gTree);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 4:\n");        System.out.println("     A      ");        System.out.println("   /   \\   ");        System.out.println("  B     C   ");        System.out.println(" / \\   / \\");        System.out.println("D   E  F  G ");        System.out.println(" \\         ");        System.out.println("  H         ");        System.out.println();    }    public static void createTree5(LinkedBinaryTree<String> tree) {        // Leaves        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>("D");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        LinkedBinaryTree<String> hTree = new LinkedBinaryTree<>("H");        LinkedBinaryTree<String> emptyTree = new LinkedBinaryTree<>();        // Subtrees:        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E", emptyTree, hTree);        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", dTree, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", fTree, gTree);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 5:\n");        System.out.println("     A      ");        System.out.println("   /   \\   ");        System.out.println("  B     C   ");        System.out.println(" / \\   / \\");        System.out.println("D   E  F  G ");        System.out.println("     \\     ");        System.out.println("      H     ");        System.out.println();    }    public static void createTree6(LinkedBinaryTree<String> tree) {        // Leaves:        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>();        dTree.setTree("D");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        LinkedBinaryTree<String> hTree = new LinkedBinaryTree<>("H");        LinkedBinaryTree<String> emptyTree = new LinkedBinaryTree<>();        // Subtrees:        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>();        eTree.setTree("E", fTree, gTree);        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>();        bTree.setTree("B", dTree, eTree);        LinkedBinaryTree<String> cTree = new LinkedBinaryTree<>("C", emptyTree, hTree);        tree.setTree("A", bTree, cTree);        System.out.println("\nTree 6:\n"); // Fig. 23-13        System.out.println("     A      ");        System.out.println("   /   \\   ");        System.out.println("  B     C   ");        System.out.println(" / \\     \\");        System.out.println("D   E     H ");        System.out.println("   / \\     ");        System.out.println("  F   G     ");        System.out.println();    }    public static void createTree7(LinkedBinaryTree<String> tree) {        // Leaves:        LinkedBinaryTree<String> dTree = new LinkedBinaryTree<>("D");        LinkedBinaryTree<String> fTree = new LinkedBinaryTree<>("F");        LinkedBinaryTree<String> gTree = new LinkedBinaryTree<>("G");        // Subtrees:        LinkedBinaryTree<String> eTree = new LinkedBinaryTree<>("E", fTree, gTree);        LinkedBinaryTree<String> bTree = new LinkedBinaryTree<>("B", dTree, eTree);        tree.setTree("A", bTree, bTree);        System.out.println("\nTree 7:\n");        System.out.println("     A      ");        System.out.println("   /   \\     ");        System.out.println("  B     B     ");        System.out.println(" / \\   / \\  ");        System.out.println("D   E D   E   ");        System.out.println("   / \\   / \\");        System.out.println("  F   G F   G ");        System.out.println();    }    public static void showTreeStats(LinkedBinaryTree<String> tree, String root, int height, int numberOfNodes) {        if (tree.isEmpty()) {            System.out.println("The tree is empty");        } else {            System.out.println("The tree is not empty");        }        System.out.println("Root of tree is " + tree.getRootData() + "; s/b " + root);        System.out.println("Height of tree is " + tree.getHeight() + "; s/b " + height);        System.out.println("# nodes in tree is " + tree.getNumberOfNodes() + "; s/b " + numberOfNodes);    }    public static void testLevelOrder(LinkedBinaryTree<String> tree, String answer) {        System.out.println("\nLevel order:");        System.out.println(answer + "  Expected");        Iterator<String> levelOrder = tree.levelorderIterator();        while (levelOrder.hasNext()) {            System.out.print(levelOrder.next() + " ");        }        System.out.println(" Actual using LevelOrderIterator\n---------------");    }    public static void testPreorder(LinkedBinaryTree<String> tree, String answer) {        System.out.println("\nPreorder:");        System.out.println(answer + "  Expected");        Iterator<String> preorder = tree.preorderIterator();        while (preorder.hasNext()) {            System.out.print(preorder.next() + " ");        }        System.out.println(" Actual using PreorderIterator");        tree.iterativePreorderTraverse();        System.out.println(" Actual using iterativePreorderTraverse");        System.out.println("---------------");    }    public static void testInorder(LinkedBinaryTree<String> tree, String answer) {        System.out.println("\nInorder:");        System.out.println(answer + "  Expected");        Iterator<String> inorder = tree.inorderIterator();        while (inorder.hasNext()) {            System.out.print(inorder.next() + " ");        }        System.out.println(" Actual using InorderIterator");        tree.iterativeInorderTraverse();        System.out.println(" Actual using iterativeInorderTraverse");        System.out.println("---------------");    }    public static void testPostorder(LinkedBinaryTree<String> tree, String answer) {        System.out.println("\nPostOrder:");        System.out.println(answer + "  Expected");        Iterator<String> postorder = tree.postorderIterator();        while (postorder.hasNext()) {            System.out.print(postorder.next() + " ");        }        System.out.println(" Actual using PostorderIterator\n---------------");    }    public static void testEmptyTree() {        System.out.println("\nTest empty tree:");        LinkedBinaryTree<String> myTree = new LinkedBinaryTree<>();        // myTree.root is null, so myTree is empty, not null        if (myTree == null) {            System.out.println("myTree is null--ERROR");        } else {            System.out.println("myTree is not null--CORRECT.");            if (myTree.isEmpty()) {                System.out.println("myTree is empty--CORRECT.");            } else {                System.out.println("myTree.root is not empty--ERROR");            }        }        System.out.println();    }    /** Tests the code in Segment 23.21. */    public static void TestSeg2321() {        System.out.println("Testing code in Segment 23.21:");        // Represent each leaf as a one-node tree        BinaryTree<String> dTree = new LinkedBinaryTree<>();        dTree.setTree("D");        BinaryTree<String> fTree = new LinkedBinaryTree<>();        fTree.setTree("F");        BinaryTree<String> gTree = new LinkedBinaryTree<>();        gTree.setTree("G");        BinaryTree<String> hTree = new LinkedBinaryTree<>();        hTree.setTree("H");        BinaryTree<String> emptyTree = new LinkedBinaryTree<>();        // Form larger subtrees        BinaryTree<String> eTree = new LinkedBinaryTree<>();        eTree.setTree("E", fTree, gTree); // subtree rooted at E        BinaryTree<String> bTree = new LinkedBinaryTree<>();        bTree.setTree("B", dTree, eTree); // subtree rooted at B        BinaryTree<String> cTree = new LinkedBinaryTree<>();        cTree.setTree("C", emptyTree, hTree); // subtree rooted at C        BinaryTree<String> aTree = new LinkedBinaryTree<>();        aTree.setTree("A", bTree, cTree); // desired tree rooted at A        // Display root, height, number of nodes        System.out.println("Root of tree contains " + aTree.getRootData());        System.out.println("Height of tree is " + aTree.getHeight());        System.out.println("Tree has " + aTree.getNumberOfNodes() + " nodes");        // Display nodes in preorder        System.out.println("A preorder traversal visits nodes in this order:");        Iterator<String> preorder = aTree.preorderIterator();        while (preorder.hasNext()) {            System.out.print(preorder.next() + " ");        }        System.out.println();        System.out.println("Should be \n" + "A B D E F G C H \n");    }}/* *  * Tree 1: *  * A / \ B C / \ / \ D E F G *  * The tree is not empty Root of tree is A; s/b A Height of tree is 3; s/b 3 # * nodes in tree is 7; s/b 7 *  * Preorder: A B D E C F G Expected A B D E C F G Actual using PreorderIterator * A B D E C F G Actual using iterativePreorderTraverse --------------- *  * Inorder: D B E A F C G Expected D B E A F C G Actual using InorderIterator D * B E A F C G Actual using iterativeInorderTraverse --------------- *  * PostOrder: D E B F G C A Expected D E B F G C A Actual using * PostorderIterator --------------- *  * Level order: A B C D E F G Expected A B C D E F G Actual using * LevelOrderIterator --------------- *  * Tree 2: *  * A / \ B C \ / \ E F G *  * The tree is not empty Root of tree is A; s/b A Height of tree is 3; s/b 3 # * nodes in tree is 6; s/b 6 *  * Preorder: A B E C F G Expected A B E C F G Actual using PreorderIterator A B * E C F G Actual using iterativePreorderTraverse --------------- *  * Inorder: B E A F C G Expected B E A F C G Actual using InorderIterator B E A * F C G Actual using iterativeInorderTraverse --------------- *  * PostOrder: E B F G C A Expected E B F G C A Actual using PostorderIterator * --------------- *  * Level order: A B C E F G Expected A B C E F G Actual using LevelOrderIterator * --------------- *  * Tree 3: *  * A / \ B C / \ / D E F \ G *  * The tree is not empty Root of tree is A; s/b A Height of tree is 4; s/b 4 # * nodes in tree is 7; s/b 7 *  * Preorder: A B D E C F G Expected A B D E C F G Actual using PreorderIterator * A B D E C F G Actual using iterativePreorderTraverse --------------- *  * Inorder: D B E A F G C Expected D B E A F G C Actual using InorderIterator D * B E A F G C Actual using iterativeInorderTraverse --------------- *  * PostOrder: D E B G F C A Expected D E B G F C A Actual using * PostorderIterator --------------- *  * Level order: A B C D E F G Expected A B C D E F G Actual using * LevelOrderIterator --------------- *  * Tree 4: *  * A / \ B C / \ / \ D E F G \ H *  * The tree is not empty Root of tree is A; s/b A Height of tree is 4; s/b 4 # * nodes in tree is 8; s/b 8 *  * Preorder: A B D H E C F G Expected A B D H E C F G Actual using * PreorderIterator A B D H E C F G Actual using iterativePreorderTraverse * --------------- *  * Inorder: D H B E A F C G Expected D H B E A F C G Actual using * InorderIterator D H B E A F C G Actual using iterativeInorderTraverse * --------------- *  * PostOrder: H D E B F G C A Expected H D E B F G C A Actual using * PostorderIterator --------------- *  * Level order: A B C D E F G H Expected A B C D E F G H Actual using * LevelOrderIterator --------------- *  * Tree 5: *  * A / \ B C / \ / \ D E F G \ H *  * The tree is not empty Root of tree is A; s/b A Height of tree is 4; s/b 4 # * nodes in tree is 8; s/b 8 *  * Preorder: A B D E H C F G Expected A B D E H C F G Actual using * PreorderIterator A B D E H C F G Actual using iterativePreorderTraverse * --------------- *  * Inorder: D B E H A F C G Expected D B E H A F C G Actual using * InorderIterator D B E H A F C G Actual using iterativeInorderTraverse * --------------- *  * PostOrder: D H E B F G C A Expected D H E B F G C A Actual using * PostorderIterator --------------- *  * Level order: A B C D E F G H Expected A B C D E F G H Actual using * LevelOrderIterator --------------- *  * Tree 6: *  * A / \ B C / \ \ D E H / \ F G *  * The tree is not empty Root of tree is A; s/b A Height of tree is 4; s/b 4 # * nodes in tree is 8; s/b 8 *  * Preorder: A B D E F G C H Expected A B D E F G C H Actual using * PreorderIterator A B D E F G C H Actual using iterativePreorderTraverse * --------------- *  * Inorder: D B F E G A C H Expected D B F E G A C H Actual using * InorderIterator D B F E G A C H Actual using iterativeInorderTraverse * --------------- *  * PostOrder: D F G E B H C A Expected D F G E B H C A Actual using * PostorderIterator --------------- *  * Level order: A B C D E H F G Expected A B C D E H F G Actual using * LevelOrderIterator --------------- *  * Tree 7: *  * A / \ B B / \ / \ D E D E / \ / \ F G F G *  * The tree is not empty Root of tree is A; s/b A Height of tree is 4; s/b 4 # * nodes in tree is 11; s/b 11 *  * Preorder: A B D E F G B D E F G Expected A B D E F G B D E F G Actual using * PreorderIterator A B D E F G B D E F G Actual using iterativePreorderTraverse * --------------- *  * Inorder: D B F E G A D B F E G Expected D B F E G A D B F E G Actual using * InorderIterator D B F E G A D B F E G Actual using iterativeInorderTraverse * --------------- *  * PostOrder: D F G E B D F G E B A Expected D F G E B D F G E B A Actual using * PostorderIterator --------------- *  * Level order: A B B D E D E F G F G Expected A B B D E D E F G F G Actual * using LevelOrderIterator --------------- *  * Test empty tree: myTree is not null--CORRECT. myTree is empty--CORRECT. *  * Testing code in Segment 23.21: Root of tree contains A Height of tree is 4 * Tree has 8 nodes A preorder traversal visits nodes in this order: A B D E F G * C H Should be A B D E F G C H *  * Done. *  */