package no.uib.info233.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import no.uib.info233.queue.LinkedQueue;
import no.uib.info233.queue.Queue;
import no.uib.info233.stack.LinkedStack;
import no.uib.info233.stack.Stack;

/**
 * This class implements a binary tree by using an array. A display method is
 * defined for testing.
 * 
 * Exercise 17 in Chapter 24 suggests that entries in the tree be assigned to
 * locations in the array in a level-order fashion beginning at index 0 of the
 * array. Gaps in the array correspond to missing nodes in the tree.
 * 
 * The left child of the node at a[i] is a[2 * i + 1]. The right child of the
 * node at a[i] is a[2 * i + 2]. The parent of the node at a[i] is a[(i - 1) /
 * 2].
 * 
 * @author Charles Hoot
 * @version 4.2
 */
public class ArrayBinaryTree<T> implements BinaryTree<T> {
    private T[] theData;
    private int height; // Of tree
    private int size; // Number of locations in array for a full tree of this height

    public ArrayBinaryTree() {
        theData = null;
        height = 0;
        size = 0;
    }

    public ArrayBinaryTree(T rootData) {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[1]; // Unchecked cast
        theData = temp;

        height = 1;
        size = 1;
        theData[0] = rootData;
    }

    public ArrayBinaryTree(T rootData, ArrayBinaryTree<T> leftTree, ArrayBinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    }

    @Override
    public void setTree(T rootData) {
        privateSetTree(rootData, new ArrayBinaryTree<T>(), new ArrayBinaryTree<T>());
    }

    @Override
    public void setTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        if (leftTree == null) {
            leftTree = new ArrayBinaryTree<T>();
        }
        if (rightTree == null) {
            rightTree = new ArrayBinaryTree<T>();
        }

        privateSetTree(rootData, (ArrayBinaryTree<T>) leftTree, (ArrayBinaryTree<T>) rightTree);
    }

    private void privateSetTree(T rootData, ArrayBinaryTree<T> leftTree, ArrayBinaryTree<T> rightTree) {
        height = 1 + Math.max(leftTree.height, rightTree.height);
        size = getSizeFromHeight(height);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size]; // Unchecked cast
        theData = temp;

        theData[0] = rootData;
        setLeftSubtree(leftTree);
        setRightSubtree(rightTree);
    }

    // Finds the size of the array necessary to
    // fit a tree of height h.
    private int getSizeFromHeight(int h) {
        // size is 2^h - 1
        int result = 0;
        for (int i = 0; i < h; i++) {
            result = 2 * result + 1;
        }

        return result;
    }

    // Copies the data values from the given subtree into the leftsubtree.
    // Precondition: The array is large enough to hold the new values.
    private void setLeftSubtree(ArrayBinaryTree<T> subTree) {
        int diff = 1;
        int index = 0;

        for (int i = 0; i < subTree.height; i++) {
            for (int nodeInLevel = 0; nodeInLevel < diff; nodeInLevel++) {
                theData[index + diff] = subTree.theData[index];
                index++;
            }
            diff *= 2;
        }
    }

    // Copies the data values from the given subtree into the rightsubtree.
    // Precondition: The array is large enough to hold the new values.
    private void setRightSubtree(ArrayBinaryTree<T> subTree) {
        int diff = 1;
        int index = 0;

        for (int i = 0; i < subTree.height; i++) {
            for (int nodeInLevel = 0; nodeInLevel < diff; nodeInLevel++) {
                theData[index + 2 * diff] = subTree.theData[index];
                index++;
            }
            diff *= 2;
        }
    }

    @Override
    public T getRootData() {
        T rootData = null;
        if (theData != null) {
            rootData = theData[0];
        }
        return rootData;
    }

    @Override
    public Boolean isEmpty() {
        return theData == null;
    }

    @Override
    public void clear() {
        theData = null;
        height = 0;
        size = 0;
    }

    protected void setRootData(T rootData) {
        assert theData != null;
        theData[0] = rootData;
    }

    @Override
    public Integer getHeight() {
        return getHeight(0);
    }

    private Integer getHeight(int root) {
        int result = 0;

        if (nodeExists(root)) {
            int rightHeight = getHeight(getRightChild(root));
            int leftHeight = getHeight(getLeftChild(root));
            result = 1 + Math.max(leftHeight, rightHeight);
        }
        return result;
    }

    @Override
    public Integer getNumberOfNodes() {
        int result = 0;

        for (int i = 0; i < size; i++) {
            if (theData[i] != null) {
                result++;
            }
        }
        return result;
    }

    // The following operations allow one to move in the tree and
    // to test whether a child exists.

    protected boolean hasLeftChild(int i) {
        return nodeExists(getLeftChild(i));
    }

    protected int getLeftChild(int i) {
        return 2 * i + 1;
    }

    protected boolean hasRightChild(int i) {
        return nodeExists(getRightChild(i));
    }

    protected int getRightChild(int i) {
        return 2 * i + 2;
    }

    protected boolean nodeExists(int i) {
        return (i >= 0 && i < size) && (theData[i] != null);
    }

    protected int getParent(int i) {
        return (i - 1) / 2;
    }

    protected T getData(int i) {
        T result = null;

        if (nodeExists(i)) {
            result = theData[i];
        }
        return result;
    }

    // The following operation allows one to set a node in the tree....
    // it is fairly dangerous.... we will require that if a node is set
    // to non-null, its parent must either exist or be the root.
    // (This requirement guarantees that we only have to extend by at
    // most one level.)
    protected void setNode(int i, T entry) {
        assert (entry == null) || (i == 0) || nodeExists(getParent(i));

        if ((entry == null) && (i < size)) {
            theData[i] = null;
        } else {
            if (i >= size) {
                resizeArray();
            }
            theData[i] = entry;
        }
    }

    private void resizeArray() {
        int newSize = getSizeFromHeight(height + 1);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] larger = (T[]) new Object[newSize]; // Unchecked cast

        for (int i = 0; i < size; i++) {
            larger[i] = theData[i];
        }

        theData = larger;
        height = height + 1;
        size = newSize;
    }

    @Override
    public Iterator<T> inorderIterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<T> {
        private Stack<Integer> nodeStack;
        private Integer currentNode;

        public InorderIterator() {
            nodeStack = new LinkedStack<Integer>();
            currentNode = 0;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || nodeExists(currentNode);
        }

        @Override
        public T next() {
            Integer nextNode = -1;

            // Find leftmost node with no left child
            while (nodeExists(currentNode)) {
                nodeStack.push(currentNode);
                currentNode = getLeftChild(currentNode);
            }

            // Get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                assert nodeExists(nextNode); // Since nodeStack was not empty before the pop
                currentNode = getRightChild(nextNode); // Right subchild
            } else {
                throw new NoSuchElementException();
            }

            return theData[nextNode];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> preorderIterator() {
        return new PreorderIterator();
    }

    private class PreorderIterator implements Iterator<T> {
        private Stack<Integer> nodeStack;

        public PreorderIterator() {
            nodeStack = new LinkedStack<Integer>();
            if (!isEmpty()) {
                nodeStack.push(0);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        @Override
        public T next() {
            T result = null;
            if (nodeStack.isEmpty()) {
                throw new NoSuchElementException();
            } else {
                Integer top = nodeStack.pop();
                result = theData[top];

                // Push the children on the stack: Right then left

                if (hasRightChild(top)) {
                    nodeStack.push(getRightChild(top));
                }
                if (hasLeftChild(top)) {
                    nodeStack.push(getLeftChild(top));
                }
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> postorderIterator() {
        return new PostorderIterator();
    }

    private class PostorderIterator implements Iterator<T> {
        private Stack<PostOrderNode> nodeStack;

        public PostorderIterator() {
            nodeStack = new LinkedStack<PostOrderNode>();
            if (!isEmpty()) {
                nodeStack.push(new PostOrderNode(0, PostOrderState.LEFT));
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        @Override
        public T next() {
            T result = null;
            if (nodeStack.isEmpty()) {
                throw new NoSuchElementException();
            } else {
                PostOrderNode top = nodeStack.pop();
                PostOrderState state = top.state;

                while (state != PostOrderState.TOP) {
                    if (state == PostOrderState.LEFT) {
                        top.state = PostOrderState.RIGHT;
                        nodeStack.push(top);

                        if (hasLeftChild(top.node)) {
                            nodeStack.push(new PostOrderNode(getLeftChild(top.node), PostOrderState.LEFT));
                        }
                    } else {
                        assert state == PostOrderState.RIGHT;
                        top.state = PostOrderState.TOP;
                        nodeStack.push(top);

                        if (hasRightChild(top.node)) {
                            nodeStack.push(new PostOrderNode(getRightChild(top.node), PostOrderState.LEFT));
                        }
                    }
                    top = nodeStack.pop();
                    state = top.state;
                }
                result = theData[top.node];
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private enum PostOrderState {
        TOP, LEFT, RIGHT
    };

    private class PostOrderNode {
        public Integer node;
        public PostOrderState state;

        PostOrderNode(Integer theNode, PostOrderState theState) {
            node = theNode;
            state = theState;
        }
    }

    @Override
    public Iterator<T> levelorderIterator() {
        return new LevelOrderIterator();
    }

    private class LevelOrderIterator implements Iterator<T> {
        private Queue<Integer> nodeQueue;

        public LevelOrderIterator() {
            nodeQueue = new LinkedQueue<Integer>();
            if (!isEmpty()) {
                nodeQueue.enqueue(0);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeQueue.isEmpty();
        }

        @Override
        public T next() {
            Integer nextNode;
            if (hasNext()) {
                nextNode = nodeQueue.dequeue();

                // add to queue in order of recursive calls
                if (hasLeftChild(nextNode)) {
                    nodeQueue.enqueue(getLeftChild(nextNode));
                }

                if (hasRightChild(nextNode)) {
                    nodeQueue.enqueue(getRightChild(nextNode));
                }
            } else {
                throw new NoSuchElementException();
            }

            return getData(nextNode);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // For testing.
    public void display() {
        for (int i = 0; i < size; i++) {
            if (nodeExists(i)) {
                System.out.println("index: " + i + " has " + getData(i));
            }
        }
    }
}
