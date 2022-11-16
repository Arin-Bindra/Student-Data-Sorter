/**
 * Arin Bindra
 * 
 * @param <E>
 */
public class Node <E extends Comparable> implements Comparable <Node>{
    
    private E data;
    public Node left;
    public Node right;
    
    public Node(E data)
    {
        this.setData(data);
    }
    
    //--------------------------------------------------------------------------
    
    private void setData(E data)
    {
        this.data = data;
    }
    
    //--------------------------------------------------------------------------
    
    public E getData()
    {
        return this.data;
    }
    
    //--------------------------------------------------------------------------

    @Override
    public int compareTo(Node node) 
    {
        return this.data.compareTo(node.data);
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public String toString()
    {
        return this.data.toString();
    }
}
