//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    The Bus Stop Tree
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  geeksforgeeks: A program to check if a Binary Tree is a 
//                  BST or not.
//                  https://www.geeksforgeeks.org/
//                  a-program-to-check-if-a-binary-tree-is-bst-or-not/
//                  Helped me with the isValidBST method.  
///////////////////////////////////////////////////////////////////////////////
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Stack;

/**
 * <p>
 * The BusStopTree class represents a binary search tree of Bus objects that stop at a particular
 * BusStop (specified when constructing the tree). In other words, all Bus-es in the tree are known
 * to stop at the specified stop. We sort the Bus-es by a combination of their arrival time and
 * route names (see the Bus.compareTo() method).
 * </p>
 * 
 * <p>
 * The class provides methods to insert, search, and delete Bus objects from the tree, and iterate.
 * </p>
 */
public class BusStopTree {
  /**
   * The root node of the binary search tree.
   */
  private Node<Bus> root;

  /**
   * The bus stop this tree is organized around.
   */
  private BusStop userStop;

  /**
   * <p>
   * Construct an empty binary search tree.
   * </p>
   * 
   * @param stopId the particular stop we care about. We will filter out routes that do not go to
   *               the BusStop represented by this ID.
   */
  public BusStopTree(int stopId) {
    // Find BusStop.
    this.userStop = BusStop.getStop(stopId);
    if (this.userStop == null) {
      throw new IllegalArgumentException("cannot find stop " + stopId);
    }
    // Initialize an empty tree.
    this.root = null;
  }

  /**
   * <p>
   * Checks if the binary subtree rooted at the given node is a valid binary search tree.
   * </p>
   * 
   * <p>
   * In order to be a valid BST, the following must be true: For every internal (non-leaf) node X of
   * a binary tree, all the values in the node's left subtree are less than the value in X, and all
   * the values in the node's right subtree are greater than the value in X.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root node of the binary tree
   * @return true if the binary tree is a valid binary search tree, false otherwise
   */
  public static boolean isValidBST(Node<Bus> node) {
    if (node == null) return true; //base case
    
    //return false if max val of left subtree is greater than this node.
    if (node.getLeft() != null 
        && maxValSubtree(node.getLeft()).compareTo(node.getValue()) >= 0) {
      return false;
    }
    //return false if min val of right subtree is less than this node.
    if (node.getRight() != null 
        && minValSubtree(node.getRight()).compareTo(node.getValue()) <= 0) {
      return false;
    }
    
    //recursively check left and right subtrees.
    if (isValidBST(node.getLeft()) == false 
        || isValidBST(node.getRight()) == false) {
      return false;
    }
    
    return true;
  }

  /**
   * helper method for isValidBST which returns the node of maximum 
   * value in a given subtree.
   * 
   * @param node the root of the subtree
   * @return the node of greatest value in the subtree
   */
  private static Bus maxValSubtree(Node<Bus> node) {
    if (node == null) return null;
    
    Bus val = node.getValue();
    Bus maxRight = maxValSubtree(node.getRight());
    Bus maxLeft = maxValSubtree(node.getLeft());
    
    return biggestBus(maxRight, maxLeft, val);
  }
  
  /**
   * Helper method for maxValSubtree which returns the greatest of three buses.
   * @param bus1 one of the child node buses
   * @param bus2 one of the child node buses
   * @param currBus the bus at the current position in our maxValSubtree recursion
   * @return the bus of highest value
   */
  private static Bus biggestBus(Bus bus1, Bus bus2, Bus currBus) {
    Bus big = null;
    if (bus1 != null) {
      if (bus1.compareTo(bus2) > 0) big = bus1;
      else big = bus2;
    }
    else big = bus2;
    
    Bus max = null;
    if (big == null) max = currBus;
    else if (big.compareTo(currBus) > 0) max = big;
    else max = currBus;
    
    return max;
  }
  
  /**
   * helper method for isValidBST which returns the node of minimum
   * value in a given subtree.
   * 
   * @param node the root of the subtree
   * @return the node of least value in the subtree
   */
  private static Bus minValSubtree(Node<Bus> node) {
    if (node == null) return null;
    
    Bus val = node.getValue();
    Bus minRight = minValSubtree(node.getRight());
    Bus minLeft = minValSubtree(node.getLeft());
    
    return smallestBus(minRight, minLeft, val);
  }
  
  /**
   * Helper method for mintValSubtree which returns the least of three buses.
   * @param bus1 one of the child node buses
   * @param bus2 one of the child node buses
   * @param currBus the bus at the current position in our minValSubtree recursion
   * @return the bus of least value
   */
  private static Bus smallestBus(Bus bus1, Bus bus2, Bus currBus) {
    Bus small = null;
    if (bus1 != null) {
      if (bus1.compareTo(bus2) > 0) small = bus2;
      else small = bus1;
    }
    else small = bus2;
    
    Bus min = null;
    if (small == null) min = currBus;
    else if (small.compareTo(currBus) > 0) min = currBus;
    else min = small;
    
    return min;
  }

  /**
   * <p>
   * Adds a new Bus to this BusStopTree.
   * </p>
   * 
   * <p>
   * If the root is null, creates a new node with the given bus and sets it as the root. Otherwise,
   * calls the addBusHelper method to recursively add the bus to the tree. If the bus is already in
   * the tree, it will not be added again (return false).
   * </p>
   *
   * @param bus the Bus to be added to the tree
   * @return true if the bus was successfully added, false otherwise
   * @throws IllegalArgumentException if the bus does not go to this tree's stop
   */
  public boolean addBus(Bus bus) {
    if (!bus.goesTo(userStop)) {
      throw new IllegalArgumentException("attempt to add bus that does not go to stop");
    }

    if (root == null) {
      root = new Node<Bus>(bus);
      return true;
    }

    return addBusHelper(root, bus);
  }

  /**
   * <p>
   * Adds a new bus to the binary search tree rooted at the given node. If the bus is already in the
   * tree, it will not be added again.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root node of the tree to add the bus to
   * @param bus  the bus to add to the tree
   * @return true if the bus was successfully added, false otherwise
   */
  public boolean addBusHelper(Node<Bus> node, Bus bus) {
    if (node == null) return false;
    
    //figure out which subtree node belongs in
    int compare = bus.compareTo(node.getValue());
    if (compare < 0) {
      //if left node is null just set left node
      if (node.getLeft() == null) { 
        node.setLeft(new Node<Bus>(bus));
        return true;
      } else { //else we have to make recursive call
        return addBusHelper(node.getLeft(), bus);
      }
    } else if (compare > 0) {
      //if right node is null we can just set right node
      if (node.getRight() == null) {
        node.setRight(new Node<Bus>(bus));
        return true;
      } else { //else we must make recursive call
        return addBusHelper(node.getRight(), bus);
      }
    }
    
    return false; //appease compiler
  }

  /**
   * <p>
   * Removes the given bus from the BusStopTree.
   * </p>
   * 
   * <p>
   * This method should handle the case where the tree is empty and should call removeBusHelper to
   * recursively remove the nodes from the tree.
   * </p>
   * 
   * @param bus the bus to be removed
   * @return true if the bus was successfully removed, false otherwise. Specifically, this method
   *         returns false if the tree is empty or the requested Bus is not in the tree.
   */
  public boolean removeBus(Bus bus) {
    // Remove from an empty tree
    if (root == null) {
      return false;
    }

    Node<Bus> newRoot = removeBusHelper(root, bus);

    // Removing the root node?
    if (bus.compareTo(root.getValue()) == 0) {
      root = newRoot;
      return true;
    } else {
      // The only non-success case where we newRoot can be null is when we are removing the last
      // node in the tree. We handle that case in the if-case above. Here a null value indicates
      // failure.
      return newRoot != null;
    }
  }

  /**
   * <p>
   * This method is <b>OPTIONAL</b>. You do not need to implement it for full credit.
   * <b>However,</b> you should absolutely implement it at some point! This is an important
   * functionality of BSTs, and we may ask you to do it in the future, hint, hint...
   * </p>
   * 
   * <p>
   * Removes a bus from the binary search subtree rooted at the given node and returns the new root
   * of the tree (which may or may not be the same as the old root). Note that the caller is
   * responsible for updating its references to the root. Also note that the "root" of a subtree
   * might be the left or right child of another node.
   * </p>
   * 
   * <p>
   * Hint: draw lots of pictures!
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the current root node of the binary search subtree
   * @param bus  the bus to be removed from the binary search subtree
   * @return the root node of this binary search subtree after the bus has been removed, or null if
   *         the bus was not found in the subtree.
   */
  public Node<Bus> removeBusHelper(Node<Bus> node, Bus bus) {
    //traverse left and right until we find the node to be removed
    if (node.getValue().compareTo(bus) > 0) {
      node.setLeft(removeBusHelper(node.getLeft(), bus));
    }
    else if (node.getValue().compareTo(bus) < 0) {
      node.setRight(removeBusHelper(node.getRight(), bus));
    }
    
    //we found the node to be removed
    else {
      //set current node to predecessor
      if (node.getLeft() != null) {
        Node<Bus> cur = node.getLeft();
        while (cur.getRight() != null) {
          cur = cur.getRight();
        }  
        node = cur;
        node.setLeft(removeBusHelper(node.getLeft(), node.getValue()));
      }   
      //set current node to successor
      else if (node.getRight() != null) {
        Node<Bus> cur = node.getRight();
        while (cur.getLeft() != null) {
          cur = cur.getLeft();
        }  
        node = cur;
        node.setRight(removeBusHelper(node.getRight(), node.getValue()));
      }
      //it's a leaf node
      else {
        node = null;
      }
    }
    return node;
  }
  
  
  
  
  
//if (node.getLeft() != null) {
//if (node.getLeft().getValue().compareTo(bus) == 0) {
//  if (node.getLeft().getLeft() != null) {
//    Node<Bus> returnNode = getLastBusHelper(node.getLeft().getLeft());
//  } else if (node.getLeft().getRight() != null) {
//    Node<Bus> returnNode = getFirstBusHelper(node.getLeft().getRight());
//  } else {
//    Node<Bus> returnNode = node;
//  }
//}
//}
//
//if (node.getRight() != null) {
//if (node.getRight().getValue().compareTo(bus) == 0) {
//  if (node.getRight().getLeft() != null) {
//    Node<Bus> returnNode = getLastBusHelper(node.getRight().getLeft());
//  } else if (node.getRight().getRight() != null) {
//    Node<Bus> returnNode = getFirstBusHelper(node.getRight().getRight());
//  } else {
//    Node<Bus> returnNode = node;
//  }
//}
//}
  
  
  
  

  /**
   * <p>
   * Checks if the BusStopTree contains a given Bus object.
   * </p>
   * 
   * <p>
   * This method should handle the case where the tree is empty and then call containsHelper.
   * </p>
   * 
   * @param bus the Bus object to search for
   * @return true if the BusStopTree contains the Bus object, false otherwise
   */
  public boolean contains(Bus bus) {
    if (root == null) {
      return false;
    }

    return containsHelper(root, bus);
  }

  /**
   * <p>
   * Checks if the given bus is contained in the subtree rooted at the given node using the
   * Bus.compareTo method to compare Bus-es.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root of the subtree to search in
   * @param bus  the bus to search for
   * @return true if the bus is found in the subtree, false otherwise
   */
  public boolean containsHelper(Node<Bus> node, Bus bus) {
    if (node == null) return false; // if we fall off tree return false
    
    int compare = node.getValue().compareTo(bus);
    if (compare == 0) return true; //if it matches return true
    
    //check which subtree it should be in and make recursive calls
    if (compare < 0) return containsHelper(node.getRight(), bus);
    if (compare > 0) return containsHelper(node.getLeft(), bus);
    
    return false; //appease compiler
  }

  /**
   * Returns an iterator over all buses arriving at this tree's stop at or after the given time.
   * 
   * @param time the time at or after which all Bus-es returned by the iterator arrive at the stop.
   * @return an Iterator that returns all Bus-es at or after the given time.
   */
  public Iterator<Bus> getNextBuses(LocalTime time) {
    return new BusForwardIterator(root, getFirstNodeAfter(time, root));
  }

  /**
   * <p>
   * Returns the node with the soonest arrival time at userStop that is at or after the given
   * `time`, in the subtree rooted at the given node. That is, within the subtree rooted at node,
   * find the first Bus at or after `time`.
   * </p>
   * 
   * <p>
   * Note: you will want to compare the **arrivalTimes** of the Bus-es here, not the whole Bus
   * objects. You can use `Bus.getArrivalTime()` to get a Bus-es arrival time at userStop. You can
   * LocalTime.compareTo() to compare two times.
   * </p>
   * 
   * <p>
   * Hint: consider the three places that the soonest Bus could be: (1) the left subtree, (2)
   * `node`, or (3) the right subtree. You will need to find the soonest Bus at or after `time` in
   * each option and then choose the best among them.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param time the desired time -- the returned Node will have an arrival time at or after this
   *             time.
   * @param node the root of the relevant subtree.
   * @return the node in the subtree that has the soonest arrival time at or after the desired time;
   *         or null if there is no such node.
   */
  protected Node<Bus> getFirstNodeAfter(LocalTime time, Node<Bus> node) {
    if (node == null) return null; //base case
    
    Node<Bus> current = node;
    Node<Bus> subL = getFirstNodeAfter(time, node.getLeft());
    Node<Bus> subR = getFirstNodeAfter(time, node.getRight());
    
    Node<Bus> answer = null;
    
    //check if subL is greater than time and store in answer
    if (subL != null) {
      if (subL.getValue().getArrivalTime().compareTo(time) >= 0) {
        answer = subL;
      } 
    } 
    
    //check if subR is greater than time and less than current answer
    if (subR != null) {
      if (subR.getValue().getArrivalTime().compareTo(time) >= 0) {
        if (answer == null) answer = subR;
        else if (subR.getValue().getArrivalTime().compareTo(answer.getValue().getArrivalTime()) < 0) {
          answer = subR;
        }
      }
    } 
    
    //find out if value is greater than time and less than current answer
    if (current.getValue().getArrivalTime().compareTo(time) >= 0) {
      if (answer == null) answer = current;
      else if (current.getValue().getArrivalTime().compareTo(answer.getValue().getArrivalTime()) < 0) {
        answer = current;
      }
    }
    
    return answer;
  }

  /**
   * Returns an iterator over all Bus-es at or after the given time that go to the specified
   * destination.
   * 
   * @param time        the time we want to start iterating from.
   * @param destination the destination we want all Bus-es to go to.
   * @return an iterator that that returns all Bus-es at or after the given time that go to the
   *         specified destination.
   */
  public Iterator<Bus> getNextBusesTo(LocalTime time, BusStop destination) {
    Iterator<Bus> unfiltered = getNextBuses(time);
    return new BusFilteredIterator(unfiltered, destination);
  }

  /**
   * Returns the next bus available at or after the given time.
   * 
   * @param time the time to compare with the bus schedules
   * @return the next bus available at or after the given time, or null if there are no buses
   *         available
   */
  public Bus getNextBus(LocalTime time) {
    Node<Bus> node = getFirstNodeAfter(time, root);
    if (node == null) {
      return null;
    } else {
      return node.getValue();
    }
  }

  /**
   * Returns the next bus to arrive at the given stop at or after the given time.
   * 
   * @param time the time after which the bus should arrive
   * @param stop the bus stop to check for the next bus
   * @return the next bus to arrive at the given stop at or after the given time, or null if there
   *         is no such bus
   */
  public Bus getNextBusTo(LocalTime time, BusStop stop) {
    Iterator<Bus> iterator = getNextBusesTo(time, stop);
    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      return null;
    }
  }

  /**
   * <p>
   * Returns the first bus in the bus stop tree. If the tree is empty, returns null. <br>
   * </p>
   * 
   * <p>
   * This method should handle the case of an empty tree and then call getFirstBusHelper to
   * recursively find the earliest Bus.
   * </p>
   *
   * @return the earliest bus in the bus stop tree, or null if the tree is empty.
   */
  public Bus getFirstBus() {
    if (root == null) {
      return null;
    }

    Node<Bus> node = getFirstBusHelper(root);

    if (node == null) {
      return null;
    }

    return node.getValue();
  }

  /**
   * <p>
   * Returns the first node in the tree.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   *
   * @param node the root node of the tree to search; should not be null.
   * @return the node containing the earliest Bus in the tree.
   */
  public static Node<Bus> getFirstBusHelper(Node<Bus> node) {
    if (node.getLeft() == null) return node;
    
    return getFirstBusHelper(node.getLeft());
  }

  /**
   * <p>
   * Returns the last bus in the bus stop tree. If the tree is empty, returns null.
   * </p>
   *
   * <p>
   * This method should handle the case of an empty tree and then call getLastBusHelper to
   * recursively find the latest Bus.
   * </p>
   *
   * @return the last bus in the bus stop tree, or null if the tree is empty.
   */
  public Bus getLastBus() {
    if (root == null) {
      return null;
    }

    Node<Bus> node = getLastBusHelper(root);

    if (node == null) {
      return null;
    }

    return node.getValue();
  }

  /**
   * <p>
   * Returns the last node in the tree.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   *
   * @param node the root node of the tree to search; should not be null.
   * @return the node containing the latest Bus in the tree.
   */
  public static Node<Bus> getLastBusHelper(Node<Bus> node) {
    if (node.getRight() == null) return node;
    
    return getLastBusHelper(node.getRight());
  }

  /**
   * <p>
   * This static method finds the path from the given currentNode to one of its descendants destNode
   * and pushes all the nodes on the path to the given stack.
   * </p>
   * 
   * <p>
   * In a binary search tree, a "path" is a set of node that one must traverse to get from one node
   * to another.
   * </p>
   * 
   * <p>
   * You may assume that `destNode` is indeed a descendant of `currentNode` (or they are the same
   * node). You may assume that neither node is null.
   * </p>
   * 
   * <p>
   * The nodes are pushed in order from the currentNode to the destNode. That is, currentNode is
   * pushed to the stack, then it's child, then it's grandchild, etc. until we reach the destNode.
   * So at the end of this routine, destNode is the top element on the stack, followed by its
   * parent, followed by its grandparent, etc all the way to the currentNode.
   * </p>
   * 
   * <p>
   * This method should not modify the stack in any other way. Notably, it should not pop any
   * elements from the stack.
   * </p>
   * 
   * <p>
   * For example, if we are given the following tree:
   * </p>
   * 
   * <pre>
   *       A
   *     /   \
   *    B     C
   *   / \   / \
   *  D   E  F  G
   * </pre>
   * 
   * And the following stack:
   * 
   * <pre>
   * top -> Z Y X
   * </pre>
   * 
   * And we call pathToNode with the above stack, currentNode A, and destNode F, then the path from
   * A to F would A->C->F, and when we return from pathToNode, the stack will contain:
   * 
   * <pre>
   * top-> F C A Z Y X
   * </pre>
   * 
   * @param stack       the stack to push the path of nodes to.
   * @param currentNode the root of the subtree that contains node (and the first node in the path).
   * @param destNode    the desired destination node (and the last node in the path). This node is
   *                    guaranteed to be in the subtree rooted at root.
   */
  public static void pathToNode(Stack<Node<Bus>> stack, Node<Bus> currentNode, Node<Bus> destNode) {
    // Push the node (our current position on the path).
    stack.push(currentNode);

    // Descend left or right, if not done.
    if (currentNode == destNode) {
      return;
    } else if (destNode.getValue().compareTo(currentNode.getValue()) < 0) {
      pathToNode(stack, currentNode.getLeft(), destNode);
    } else {
      pathToNode(stack, currentNode.getRight(), destNode);
    }
  }

  /**
   * Returns the root node of the binary search tree.
   * 
   * @return the root node of the binary search tree
   */
  public Node<Bus> getRoot() {
    return root;
  }

  /**
   * A human readable string representation of the tree.
   * 
   * @return A human readable string representation of the tree useful for debugging.
   */
  @Override
  public String toString() {
    return toStringHelper(0, root);
  }

  /**
   * Helper used to implement toString. Prints the subtree rooted at the given node.
   * 
   * @param level the depth of node in the tree.
   * @param node  the root of the subtree we are printing.
   * @return a human readable string representing the subtree.
   */
  private String toStringHelper(int level, Node<Bus> node) {
    if (node == null) {
      return "| ".repeat(level) + "null";
    }

    return "| ".repeat(level) + node + "\n" + toStringHelper(level + 1, node.getLeft()) + "\n"
        + toStringHelper(level + 1, node.getRight());
  }

  /**
   * Returns the size of the BST.
   * 
   * @return the number of Bus-es in the tree.
   */
  public int size() {
    return sizeHelper(root);
  }

  /**
   * Returns the size of the subtree rooted at the given node.
   * 
   * @param node the root of the subtree
   * @return the number of Bus-es in the subtree
   */
  private int sizeHelper(Node<Bus> node) {
    if (node == null) {
      return 0;
    }

    return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
  }

  /**
   * Returns true if the BST is empty.
   * 
   * @return true if the size of the tree is 0; false otherwise.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Returns the height of the tree (counting nodes, not edges). The height of a tree with 1 node is
   * 1.
   * 
   * @returns the height of the tree.
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * <p>
   * Returns the height of the subtree rooted at the given node (counting nodes, not edges). The
   * height of a tree with 1 node is 1.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root of the subtree (may be null)
   * @return the height of the subtree
   */
  private int heightHelper(Node<Bus> node) {
    if (node == null) return 0;
    //recursively find height of left and right subtrees
    int heightLeft = 1 + heightHelper(node.getLeft());
    int heightRight = 1 + heightHelper(node.getRight());
    //return whichever one is bigger
    return Math.max(heightLeft, heightRight);
  }
}
