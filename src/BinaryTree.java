/**
 * Arin Bindra
 *
 * @param <E>
 */
public class BinaryTree <E extends Comparable>{
    
    public int size;
    private Node root;
    private Node foundNode;
    
    public BinaryTree()
    {
        
    }
    
    //--------------------------------------------------------------------------
    /**
    * The add method, takes a data object and first creates a new node and sets
    * the nodes data of type E as the inputted data object.From depending on the size of the binary tree the new Node will either
      recursively be put through a secondary add method or will be set as the root
 
      If the size of the binary tree is larger then 0 the new node will be inputted
      into the private add method where it takes the new Node and the root of the
      binary tree as input.
    *
    * @param data
    */
    
    public void add(E data)
    {
        Node newNode = new Node(data);
        this.size++;
        
        if(this.root == null)
        {
            this.root = newNode;
        }
        else
        {
            this.add(this.root, newNode);
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * The private add method takes a two nodes as input, one which is being added to the 
     * binary tree and another which is being compared to the new Node.
     * 
     * The new node is compared to the current node, which invokes the compare to methods
     * of the nodes data, a value smaller or larger then 0 is produced to recognize if the
     * new Node is smaller or larger then the current Node.
     * 
     * If the new Node is smaller then the current node, it is checked if the current nodes
     * left space is null, and if it is null the new Node is set to the current Nodes left node.
     * If the current Nodes left space is occupied by another node, the current spaces left node will
     * be inputted back into add method recursively until it reaches a node with an empty left or right spot.
     * 
     * This process is repeated if the new Node is larger then the current node and with the right side
     * of the current Node.
     */
    
    private void add(Node current, Node newNode)
    {   
        if(current.compareTo(newNode) > 0)
        {
            if(current.left != null)
            {
                this.add(current.left, newNode);
            }
            else
            {
                current.left = newNode;
            }
        }
        else
        {
            if(current.right != null)
            {
                this.add(current.right, newNode);
            }
            else
            {
                current.right = newNode;
            }
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * The public find node method takes a data object as input and creates and first
     * sets the findNode value to null. From there the inputted data and the root are
     * inputted into the recursive private find node method. 
     * 
     * if the foundNode is found then the method will return the foundNodes
     * data object, else it will return null
     * 
     * @param data
     * @return 
     */
    
    public E findNode(E data)
    {
        this.foundNode = null;
        
        this.findNode(this.root, (E) data);
        
        if(this.foundNode != null)
        {
            return (E) this.foundNode.getData();
        }
        else
        {
            return null;
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * The private findNode method take a current node value and a data object as
     * input then compares the data object to the current Nodes data stored data
     * object.
     * 
     * If the data objects are found to be equal using the compareTo method of the data,
     * the found Node node will be set to the current Node and the method will stop.
     * 
     * If the data objects are not equal the method will recursively input the data
     * and the current nodes left and right side node objects back into the private
     * findNode method, until either the end of the tree is found or the found data is equal
     * to one of the nodes data.
     * 
     * The binary trees data recursively goes from smallest value to largest to find the data.
     * 
     * @param current
     * @param data 
     */
    
    private void findNode(Node current,E data)
    {
        if(current.getData().compareTo(data) == 0)
        {
            this.foundNode = current;
        }
        else
        {
            if(this.foundNode == null)
            {
                if(current.left != null)
                {
                    this.findNode(current.left, data);
                }
                
                if(current.right != null)
                {
                    this.findNode(current.right, data);
                }
            }
        }
    }
    
    //--------------------------------------------------------------------------
     
    public void reverseOrder()
    {
        this.reverseOrder(this.root);
    }
    
    //--------------------------------------------------------------------------
    /**
     * The reverseOrder method takes a node as current and sets its right Node to
     * a temporary node, then sets its left node to its right node, and the temporary
     * node to its left node. This process essentially switches each nodes left and right
     * nodes recursively.
     * 
     * If the current nodes left node is then passed as input back into the reverse Node
     * function until a nodes left is null, which then allows the method to recursively
     * go through each nodes right node, working from bottom to top essentially.
     * 
     * @param current 
     */
    
    private void reverseOrder(Node current)
    {
        Node tempNode = current.right;
        current.right = current.left;
        current.left = tempNode;
        
        if(current.left != null)
        {
            this.reverseOrder(current.left);
        }
        
        if(current.right != null)
        {
            this.reverseOrder(current.right);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public void traversal()
    {
        this.traversal(this.root);
    }
    
    //--------------------------------------------------------------------------
    /**
     * The traversal method first takes the root node as input then first checks
     * the if the node has a left node indicating a smaller node, and if so recursively
     * inputs the nodes left node back into the traversal method until the current
     * nodes left node is null, which indicates that the smallest value in the binary tree
     * has been reached. 
     * 
     * When this happens the nodes data is printed out.
     * 
     * From there that current nodes right node is iterated through recursively until
     * a current nodes right node is null in which the method ends.
     * 
     * Essentially the method recursively iterates to the smallest value in the binary tree
     * then steps up one level in the binary tree and prints the smallest value on that level
     * until the largest value in the binary tree is found and printed last.
     * 
     * @param root 
     */
    
    private void traversal(Node current)
    {   
        if(current.left != null)
        {
            this.traversal(current.left);
        }
        
        System.out.println(current.getData());
        
        if(current.right != null)
        {
            this.traversal(current.right);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public Node getRoot()
    {
        return this.root;
    }
}
