/*
* Binary search tree data structure
* @author : 
*/
import java.util.*;

public class BST<T>
{
    /*
    * The root of the BST
    */
    Node<T> root;

    /*
    * Node class for a BST
    */
    private class Node<T>
    {
    	Comparable<T> data;
        Node<T> left;
        Node<T> right;
        int instance;

        Node(Comparable<T> item)
        {
            data=item;
            instance = 1;
        }
    }

    public BST()
    {
        root = null;
    }

    /*
    * Find function that finds an item in the BST
    * @param item to be found
    * @return boolean if the item was found
    */
    public boolean find(Comparable<T> item)
    {
        return find(item, root);
    }

    /*
    * Function override of the find function
    * @param item to be found
    * @param node the current node you are at
    * @return boolean if the item was found
    */
    private boolean find(Comparable<T> item, Node<T> node)
    {
       //if node is null return false
    	if(node == null)
    	{
    		return false;
    	}
    	//if the item is at the node return true
    	else if(item.compareTo((T) node.data) == 0)
    	{
    		return true;
    	}
    	//if item is less than data traverse left
    	if(item.compareTo((T) node.data)<0)
    	{
    		find(item, node.left);
    	}
    	//else traverse right
    	else
    	{
    		find(item, node.right);
    	}
    	//return true
    	return true;
        
    }

    /*
    * Insert an item to the tree
    * @param item to insert
    */
    public void insert(Comparable<T> item)
    {
        root = insert(item, root);
    }

    /*
    * Helper function for insert
    * @param item to add
    * @param node you are at
    * @return node you traverse to
    */
    private Node<T> insert(Comparable<T> item, Node<T> node)
    {
       //If empty tree, create new node
    	if(node == null)
    	{
    		node = new Node(item);
    		return node;
    	}
    	//Else if the item is less than the node, traverse left
    	else if(item.compareTo((T) node.data) < 0)
    	{
    		node.left = insert(item, node.left);
    	}
    	//Else, the item is greater than the node traverse right
    	else
    	{
    		node.right = insert(item, node.right);
    	}
    	//Return the final node
        return node;
    }

    /*
    * Function for deletion of a node
    * @param item to delete
    */
    public void delete(Comparable<T> item)
    {
        root = delete(item, root);
    }

    /*
    * Helper function for deletion of a node
    * @param item to delete
    * @param node you are at
    * @return node you traverse to
    */
    private Node<T> delete(Comparable<T> item, Node<T> node)
    {
    	//If the node is empty, create a new node
    	if(node == null)
    	{
    		node = new Node(item);
    		return node;
    	}
    	//If the item is less than the node data,, traverse left
    	if(item.compareTo((T) node.data) < 0)
    	{
    		node.left = delete(item, node.left);
    	}
    	//Else if the item is greater than the node data, traverse right
    	else if(item.compareTo((T) node.data) > 0)
    	{
    		node.right = delete(item, node.right);
    	}
    	//Else check if left or right is null
    	else
    	{
    		if(node.left == null)
    		{
    			return node.right;
    		}
    		else if(node.right == null)
    		{
    			return node.left;
    		}
    		
    		//Pass the data through the min value helper
    		node.data = (Comparable<T>) minVal(node.right);
    		node.right = delete(node.data, node.right);
    	}
    	//Return the final node
    	return node;
    }
    
    //Delete min value helper to find the min value
    private Node<T> minVal(Node<T> node)
    {
    	//Create temp variable min to hold min node
    	Node<T> min = (BST<T>.Node<T>) node.data;
    	//While there is still a left node
    	while(node.left != null)
    	{
    		//Set min to that node
    		min = (BST<T>.Node<T>) node.left.data;
    		// Traverse left
    		node = node.left;
    	}
    	//return the min node value
    	return min;
    }

    /*
    * Function to find the range sum of the binary tree
    * @param L the left bound
    * @param R the right bound
    * @return The sum of the range in the binary tree
    */
    public int rangeSum(int L, int R)
    {
        //Checking if the tree is empty
        if(root == null)
        {
        	return 0;
        }
        //Pass through helper function
        return rangeSum(root, L, R);
    	
    }
    //Range sum helper
    public int rangeSum(Node<T> node, int L, int R)
    {
    	//Initialize int sum and count
    	int sum = 0,count = 0;
    	//recursively call range sum with left and right nodes
    	sum = rangeSum(root.left, L, R);
    	sum += rangeSum(root.right, L, R);
    	//Add the value to sum
    	while(count<L&&count>R)
    	{
    		sum += root.instance;
    	}
    	//Return sum
    	return sum;
    }

    /*
    * Function to print the Binary tree
    */
    public void print()
    {
        print(root);
    }

    /*
    * Helper Function to print the Binary tree
    * @param root the root of the tree
    */
    private void print(Node<T> root)
    {
    	//Print the tree
        if(root == null)
        {
        	return;
        }
        
        System.out.println(" "+root.data);
        print(root.left);
        print(root.right);
    }
}