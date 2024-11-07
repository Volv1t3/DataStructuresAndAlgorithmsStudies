package NFARepresentationInJava;

import DoubleLinkedList.DoubleLinkedList;
import DoubleLinkedList.Node;

public class NFAsTree <E extends Comparable<E>>{

    public static void main(String[] args) {
        //!  Root node of the tree
        NodeForNFA<String> root = new NodeForNFA<String>("abE");
        root.setAcceptState(false);
        root.setForwardLink(new NodeForNFA<>("b"));
        root.setTransitionState("abE");
        root.setNextDependingOnTransition(new NodeForNFA[] {});

        //! Maybe we could break down the transition graph, rather than trying to represent it as a tree we could show it
        //! as a linked list, where we can only connect nodes with their data, and not with their transitions.
    }

    private static class NodeForNFA<E extends Comparable<E>> extends Node<E> {

        //! Additional parameter
        private boolean isAcceptState;
        private E transitionState;
        private Node<E>[] nextDependingOnTransition;

        public NodeForNFA(E data) {
            super(data);
        }

        //! Constructor which can assign all values to the new parameters
        public NodeForNFA(E data, boolean isAcceptState, E transitionState, Node<E>[] nextDependingOnTransition) {
            super(data);
            this.isAcceptState = isAcceptState;
            this.transitionState = transitionState;
            this.nextDependingOnTransition = nextDependingOnTransition;
        }

        //! Method to revise if the node is accepted state
        public boolean isAcceptState() {
            return isAcceptState;
        }

        //! Method to set the node as an accept state
        public void setAcceptState(boolean acceptState) {
            isAcceptState = acceptState;
        }
        //! Method to get the transition state
        public E getTransitionState() {
            return transitionState;
        }//! Method to set the transition state
        public void setTransitionState(E transitionState) {
            this.transitionState = transitionState;
        }//! Method to get the next depending on transition
        public Node<E>[]  getNextDependingOnTransition() {
            return nextDependingOnTransition;
        }//! Method to set the next depending on transition
        public void setNextDependingOnTransition(Node<E>[] nextDependingOnTransition) {
            this.nextDependingOnTransition = nextDependingOnTransition;
        }
    }
}
