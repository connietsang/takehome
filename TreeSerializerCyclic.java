package taboola_takehome;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


// TreeSerializerCyclic accounts for "cyclic trees" by checking if the given
// graph is a valid binary tree, i.e. makes sure it does not have cycles. 
// I have implemented a "isTree" method to check if a graph is a valid tree,
// and a "toTree" method to alter a given graph so it is a valid tree.
// serialize and deserialize can either be done by 1. using isTree, in 
// which case they will return "" and null respectively, or 2. using toTree,
// in which case they will return the serialized and deserialized form of the 
// valid tree. 
public class TreeSerializerCyclic {

	/**
	 * Tests if a given graph is a valid tree by looking for cycles. A graph
	 * cannot be a tree if it contains a cycle. Assumes the graph is UNDIRECTED.
	 * 
	 * @param root: a node of the graph
	 * @return: whether or not the given graph is a valid tree
	 */
	public static boolean isTree(Node root) {
		if (root == null)
			return false;
		Stack<Node> stack = new Stack();
		stack.push(root);
		Set<Node> visited = new HashSet();
		visited.add(root);
		while (!stack.isEmpty()) {
			Node first = stack.pop();
			if (visited.contains(first.right) && first.right != null) {
				return false;
			}
			if (visited.contains(first.left) && first.left != null) {
				return false;
			}
			if (first.right != null) {
				stack.push(first.right);
				visited.add(first.right);
			}
			if (first.left != null) {
				stack.push(first.left);
				visited.add(first.left);
			}
		}
		return true;

	}
	
	/**
	 * Turns a graph into a tree by removing edges which create a cycle.
	 * 
	 * @param root: the root of the "cyclic tree"
	 * @return: the root of the new acyclic tree
	 */
	public static Node toTree(Node root) {
		if (root == null)
			return null;
		Stack<Node> stack = new Stack();
		stack.push(root);
		Set<Node> visited = new HashSet();
		visited.add(root);
		while (!stack.isEmpty()) {
			Node first = stack.pop();
			if (visited.contains(first.right) && first.right != null) {
				first.right = null;
			}
			if (visited.contains(first.left) && first.left != null) {
				first.left = null;
			}
			if (first.right != null) {
				stack.push(first.right);
				visited.add(first.right);
			}
			if (first.left != null) {
				stack.push(first.left);
				visited.add(first.left);
			}
		}
		return root;

	}

	/**
	 * Gets the string representation of a binary tree.
	 * 
	 * @param root: root of the given tree
	 * @return: A string of tree values in BFS order, separated by whitespace.
	 */
	public static String serialize(Node root) {
		if (!isTree(root) || root == null)
			return ""; // 
		// or can do:
		// root = toTree(root);
		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node first = queue.poll();
			if (first == null)
				sb.append("null").append(" ");
			else {
				sb.append(first.num).append(" ");
				queue.add(first.left);
				queue.add(first.right);
			}
		}
		return sb.toString();
	}

	/**
	 * Converts a string representing a tree's node values to a tree.
	 * 
	 * @param str: string representation of a tree in BFS order
	 * @return: a tree in the same order as its string representation
	 */
	public Node deserialize(String str) {
		if (str.equals(""))
			return null;
		String[] vals = str.split("\\s+");
		Queue<Node> queue = new LinkedList<>();
		Node root = stringToNode(vals[0]);
		queue.add(root);
		int i = 1;
		while (!queue.isEmpty()) {
			Node first = queue.poll();
			if (first != null) {
				first.left = stringToNode(vals[i++]);
				if (first.left != null)
					queue.add(first.left);
				first.right = stringToNode(vals[i++]);
				if (first.right != null)
					queue.add(first.right);
			}

		}
		return root;
	}

	/**
	 * @param str: A string representing an integer.
	 * @return: A node with its 'num' parameter set to the integer value of str.
	 */
	public Node stringToNode(String str) {
		if (str.equals("null"))
			return null;
		return new Node(Integer.valueOf(str));
	}
}
