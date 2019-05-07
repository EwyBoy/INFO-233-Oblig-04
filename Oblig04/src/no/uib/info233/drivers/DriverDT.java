package no.uib.info233.drivers;import java.util.Iterator;import no.uib.info233.tree.LinkedDecisionTree;import no.uib.info233.tree.DecisionTree;/** * A driver that demonstrates the class DecisionTree. *  * @author Frank M. Carrano * @author Timothy M. Henry * @version 4.2 */public class DriverDT {    public static void main(String[] args) {        DecisionTree<String> myTree = new LinkedDecisionTree<>();        createTree(myTree);        testPreorder(myTree, "avocado banana donut egg cake flour grape");        myTree.resetCurrentNode(); // Set current node to root        System.out.println(myTree.getCurrentData() + " (should be avocado)");        myTree.advanceToNo();        System.out.println(myTree.getCurrentData() + " (should be banana)");        myTree.advanceToYes();        System.out.println(myTree.getCurrentData() + " (should be egg)");        System.out.println("--------------------");        myTree.resetCurrentNode();        System.out.println(myTree.getCurrentData() + " (should be avocado)");        myTree.advanceToYes();        System.out.println(myTree.getCurrentData() + " (should be cake)");        myTree.advanceToNo();        System.out.println(myTree.getCurrentData() + " (should be flour)");        // Create a decision tree        System.out.println("--------------------\n--------------------");        System.out.println("Decision tree based on Figure 23-17:");        myTree.resetCurrentNode();        myTree.setCurrentData("Is it in North America?");        myTree.setResponses("Is it in Europe?", "Does its name contain one word?");        myTree.advanceToNo();        myTree.setResponses("Brazil", "England");        myTree.resetCurrentNode();        myTree.advanceToYes();        myTree.setResponses("U.S.A.", "Canada");        // Test some of its methods        myTree.resetCurrentNode();        System.out.println("\nFirst question:");        System.out.println("Is it in North America?  Expected");        System.out.println(myTree.getCurrentData() + "  Actual");        System.out.println("isAnswer(): " + myTree.isAnswer() + " (should be false)");        myTree.advanceToNo();        System.out.println("\nNext question:");        System.out.println("Is it in Europe?  Expected");        System.out.println(myTree.getCurrentData() + "  Actual");        System.out.println("isAnswer(): " + myTree.isAnswer() + " (should be false)");        myTree.advanceToYes();        System.out.println("\nAnswer:");        System.out.println("England  Expected");        System.out.println(myTree.getCurrentData() + "  Actual");        System.out.println("isAnswer(): " + myTree.isAnswer() + " (should be true)");        System.out.println("--------------------");        System.out.println("Go back to root:");        myTree.resetCurrentNode();        myTree.advanceToYes();        System.out.println("\nNext question:");        System.out.println("Does its name contain one word?  Expected");        System.out.println(myTree.getCurrentData() + "  Actual");        myTree.advanceToNo();        System.out.println("\nAnswer:");        System.out.println("U.S.A.  Expected");        System.out.println(myTree.getCurrentData() + "  Actual");        System.out.println("isAnswer(): " + myTree.isAnswer() + " (should be true)");        System.out.println("Done.");    }    /** Precondition: tree is empty but not null */    public static void createTree(DecisionTree<String> tree) {        // Leaves        LinkedDecisionTree<String> dTree = new LinkedDecisionTree<>("donut");        LinkedDecisionTree<String> eTree = new LinkedDecisionTree<>("egg");        LinkedDecisionTree<String> fTree = new LinkedDecisionTree<>("flour");        LinkedDecisionTree<String> gTree = new LinkedDecisionTree<>("grape");        // Subtrees:        LinkedDecisionTree<String> bTree = new LinkedDecisionTree<>("banana", dTree, eTree);        LinkedDecisionTree<String> cTree = new LinkedDecisionTree<>("cake", fTree, gTree);        tree.setTree("avocado", bTree, cTree);        System.out.println("\nTree:\n");        System.out.println("    avocado     ");        System.out.println("   /       \\   "); // '\\' is the escape character for backslash        System.out.println("  banana    cake   ");        System.out.println(" /      \\  /    \\");        System.out.println("donut egg flour grape");        System.out.println();    }    public static void testPreorder(DecisionTree<String> tree, String answer) {        System.out.println("\nPreorder:");        System.out.println(answer + "  Expected");        Iterator<String> preorder = tree.preorderIterator();        while (preorder.hasNext()) {            System.out.print(preorder.next() + " ");        }        System.out.println(" Actual\n---------------");    }}/* *  * Tree: *  * avocado / \ banana cake / \ / \ donut egg flour grape *  *  * Preorder: avocado banana donut egg cake flour grape Expected avocado banana * donut egg cake flour grape Actual --------------- avocado (should be avocado) * banana (should be banana) egg (should be egg) -------------------- avocado * (should be avocado) cake (should be cake) flour (should be flour) * -------------------- -------------------- Decision tree based on Figure * 23-17: *  * First question: Is it in North America? Expected Is it in North America? * Actual isAnswer(): false (should be false) *  * Next question: Is it in Europe? Expected Is it in Europe? Actual isAnswer(): * false (should be false) *  * Answer: England Expected England Actual isAnswer(): true (should be true) * -------------------- Go back to root: *  * Next question: Does its name contain one word? Expected Does its name contain * one word? Actual *  * Answer: U.S.A. Expected U.S.A. Actual isAnswer(): true (should be true) Done. *  */