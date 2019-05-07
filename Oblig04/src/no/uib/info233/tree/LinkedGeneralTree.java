package no.uib.info233.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

import no.uib.info233.stack.LinkedStack;
import no.uib.info233.stack.Stack;

/**
 * Implementation of a general tree using a binary tree. Solves Project 5 in
 * Chapter 24.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class LinkedGeneralTree<T> implements GeneralTree<T> {
    private BinaryTreeNode<T> root;

    public LinkedGeneralTree() {
        root = null;
    }

    public LinkedGeneralTree(T rootData) {
        root = new BinaryTreeNode<>(rootData);
    }

    public LinkedGeneralTree(T rootData, LinkedGeneralTree<T>[] children) {
        privateSetTree(rootData, children);
    }

    @Override
    public void setTree(T rootData) {
        root = new BinaryTreeNode<>(rootData);
    }

    @Override
    public void setTree(T rootData, GeneralTree<T>[] children) {
        privateSetTree(rootData, children);
    }

    private void privateSetTree(T rootData, GeneralTree<T>[] children) {
        root = new BinaryTreeNode<>(rootData);

        // Find the first valid child and put it on the left of the root
        int i = 0;
        boolean found = false;
        BinaryTreeNode<T> childList = null;

        while (i < children.length && !found) {
            // Force the child to be one of our trees
            LinkedGeneralTree<T> child = (LinkedGeneralTree<T>) children[i];

            if ((child != null) && !child.isEmpty()) {
                // Found it
                found = true;
                root.setLeftChild(child.root.copy());
                childList = root.getLeftChild();
            }
            i++;
        }

        // The rest of the children are turned into a chain of linked nodes
        // using right children "next" references
        while (i < children.length) {
            LinkedGeneralTree<T> child = (LinkedGeneralTree<T>) children[i];
            if ((child != null) && !child.isEmpty()) {
                childList.setRightChild(child.root.copy());
                childList = childList.getRightChild();
            }
            i++;
        }
    }

    @Override
    public T getRootData() {
        T rootData = null;
        if (root != null) {
            rootData = root.getData();
        }

        return rootData;
    }

    @Override
    public Boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    protected void setRootData(T rootData) {
        root.setData(rootData);
    }

    protected void setRootNode(BinaryTreeNode<T> rootNode) {
        root = rootNode;
    }

    protected BinaryTreeNode<T> getRootNode() {
        return root;
    }

    @Override
    public Integer getHeight() {
        return root.getHeight();
    }

    @Override
    public Integer getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    @Override
    public Iterator<T> preorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> postorderIterator() {
        return new PostorderIterator();
    }

    @Override
    public Iterator<T> inorderIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> levelorderIterator() {
        throw new UnsupportedOperationException();
    }

    private class PreorderIterator implements Iterator<T> {
        private Stack<BinaryTreeNode<T>> nodeStack;

        public PreorderIterator() {
            nodeStack = new LinkedStack<>();
            if (root != null) {
                nodeStack.push(root);
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
                BinaryTreeNode<T> top = nodeStack.pop();
                result = top.getData();

                // Push the children on the stack, right then left.

                if (top.hasRightChild()) {
                    nodeStack.push(top.getRightChild());
                }
                if (top.hasLeftChild()) {
                    nodeStack.push(top.getLeftChild());
                }
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class PostorderIterator implements Iterator<T> {
        private Stack<PostOrderNode> nodeStack;

        public PostorderIterator() {
            nodeStack = new LinkedStack<>();
            if (root != null) {
                nodeStack.push(new PostOrderNode(root, PostOrderState.CHILDREN));
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
                    assert state == PostOrderState.CHILDREN;
                    Stack<PostOrderNode> children = new LinkedStack<>();
                    BinaryTreeNode<T> child = top.node.getLeftChild();
                    while (child != null) {
                        children.push(new PostOrderNode(child, PostOrderState.CHILDREN));
                        child = child.getRightChild();
                    }

                    // push the parent and the children on the stack
                    nodeStack.push(new PostOrderNode(top.node, PostOrderState.TOP));
                    while (!children.isEmpty()) {
                        nodeStack.push(children.pop());
                    }

                    top = nodeStack.pop();
                    state = top.state;
                }
                result = top.node.getData();
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private enum PostOrderState {
        TOP, CHILDREN
    };

    private class PostOrderNode {
        public BinaryTreeNode<T> node;
        public PostOrderState state;

        PostOrderNode(BinaryTreeNode<T> theNode, PostOrderState theState) {
            node = theNode;
            state = theState;
        }
    }
}
