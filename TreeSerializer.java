package taboola_takehome;

import java.util.LinkedList;
import java.util.Queue;

public class TreeSerializer {

	/**
	 * Gets the string representation of a binary, acyclic tree.
	 * 
	 * @param root: root of the given tree
	 * @return: A string of tree values in BFS order, separated by whitespace.
	 */
	public String serialize(Node root) {
		if (root == null)
			return "";
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

class Node {
	Node left;
	Node right;
	int num;
	String s;

	public Node(int num) {
		this.num = num;
	}

}

// Make Node a generic class so TreeSerializer can support any type.
// class Node<T> {
// 	  Node<T> left; Node<T> right; T val;
//      public Node(T val) {
//    	  this.val = val;
//      }
//		
// }