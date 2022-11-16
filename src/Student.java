/**
 * Arin Bindra
 * 
 * @param <E>
 */
public class Student<E extends Comparable> implements Comparable<Student>{
    
    private String name;
    private int mark;
    private E key;
    
    public Student(String name, int mark)
    {
        this.setName(name);
        this.setMark(mark);
    }
    
    public Student()
    {
        
    }
    
    //--------------------------------------------------------------------------
    
    private void setName(String name)
    {
        this.name = name;
    }
    
    //--------------------------------------------------------------------------
    
    private void setMark(int mark)
    {
        this.mark = mark;
    }
    
    //--------------------------------------------------------------------------
    
    public String getName()
    {
        return this.name;
    }
    
    //--------------------------------------------------------------------------
    
    public int getMark()
    {
        return this.mark;
    }
    
    //--------------------------------------------------------------------------
    
    public E getKey()
    {
        return this.key;
    }
    
    //--------------------------------------------------------------------------
    
    public void setKey(E key)
    {
        this.key = key;
    }
    
    //--------------------------------------------------------------------------

    @Override
    public int compareTo(Student student) 
    {
        return this.key.compareTo(student.getKey());
    }
    
    //--------------------------------------------------------------------------
    
    public boolean equals(Student student)
    {
        boolean equals = false;
        
        if(this.key.equals(student.getKey()))
        {
            equals = true;
        }
        
        return equals;
    }
    
    @Override
    public String toString()
    {
        return "Name: " + this.name + " | Mark: " + this.mark;
    }
    
    
    
}
