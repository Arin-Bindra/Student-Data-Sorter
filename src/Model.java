/**
 * Arin Bindra
 *
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFileChooser;

public class Model extends Observable{
    
    private FileData fileData;
    private BinaryTree binaryTreeName;
    private BinaryTree binaryTreeMark;
    private Student[] students;
    private ArrayList<Student> studentsSave;
    private String data = "";
    private String foundStudent = "";
    private String file;
    private boolean name = true;
    private boolean mark = false;
    private boolean menu = true;
    private boolean dataMenu = false;
    private boolean viewData = false;
    private boolean searchData = false;
    private boolean searchResults = false;
    private boolean fileSelected = false;
    
    public Model()
    {
        this.fileData = new FileData();
        this.binaryTreeName = new BinaryTree();
        this.binaryTreeMark = new BinaryTree();
        this.studentsSave = new ArrayList<>();
    }
    
    //--------------------------------------------------------------------------
    
    public void setFile()
    {
        JFileChooser fileChooser = new JFileChooser(new File("."));
        int stateImageFileChooser = fileChooser.showOpenDialog(null);
        
        if(stateImageFileChooser == JFileChooser.APPROVE_OPTION)
        {
            String fileName = fileChooser.getSelectedFile().getPath();
            this.file = fileChooser.getName();
            
            this.createData(fileName);
        }
        
        if(this.file == null)
        {
            this.fileSelected = true;
        }
    }
    
    //--------------------------------------------------------------------------
    
    public boolean fileSelected()
    {
        return this.fileSelected;
    }
    
    //--------------------------------------------------------------------------
    
    private void createData(String file)
    {
        this.fileData.readFile(file);
        this.students = this.fileData.getStudents();
        this.sortName();
        this.sortMark();
    }
    
    //--------------------------------------------------------------------------
    
    private void sortName()
    {
        for(int i=0; i < this.students.length; i++)
        {
            this.students[i].setKey(this.students[i].getName());
            this.binaryTreeName.add(this.students[i]);
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void sortMark()
    {
        for(int i=0; i < this.students.length; i++)
        {
            this.students[i].setKey(this.students[i].getMark());
            this.binaryTreeMark.add(this.students[i]);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public void saveData()
    {
        this.studentsSave.clear();
        
        if(this.mark == true)
        {
            this.createStudents(this.binaryTreeMark.getRoot());
        }
        else
        {
            this.createStudents(this.binaryTreeName.getRoot());
        }
        
        for(int i=0; i < this.studentsSave.size(); i++)
        {
            this.students[i] = this.studentsSave.get(i);
        }
        
        this.fileData.writeData(this.students);
    }
    
    //--------------------------------------------------------------------------
    
    private void createStudents(Node current)
    {
        if(current.left != null)
        {
            this.createStudents(current.left);
        }
        
        this.studentsSave.add((Student) current.getData());
        
        if(current.right != null)
        {
            this.createStudents(current.right);
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void traversal()
    {
        this.data = "";
        
        if(this.mark == true)
        {
            this.traversal(this.binaryTreeMark.getRoot());
        }
        else
        {
            this.traversal(this.binaryTreeName.getRoot());
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void traversal(Node current)
    {
        if(current.left != null)
        {
            this.traversal(current.left);
        }
        
        this.data = this.data + current.getData() + "\n";
        
        if(current.right != null)
        {
            this.traversal(current.right);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public String getData()
    {
        this.data = "";
        this.traversal();
        return this.data;
    }
    
    //--------------------------------------------------------------------------
    
    public void findStudent(String find)
    {
        int i1 = Integer.parseInt(find);
        Student s1 = new Student("", i1);
        s1.setKey(i1);
        
        this.foundStudent = null;
        
        if(this.binaryTreeMark.findNode(s1) != null)
        {
            this.foundStudent = "" + this.binaryTreeMark.findNode(s1);
        }
        
        if(this.foundStudent == null)
        {
            this.foundStudent = "No student achieved the mark " + find;
        }
        
    }
    
    //--------------------------------------------------------------------------
    
    public String getfindStudent()
    {
        return this.foundStudent;
    }
    
    //--------------------------------------------------------------------------
    
    public void reverseName()
    {
        this.binaryTreeName.reverseOrder();
    }
    
    //--------------------------------------------------------------------------
    
    public void reverseMark()
    {
        this.binaryTreeMark.reverseOrder();
    }
    
    //--------------------------------------------------------------------------
    
    public void setMark()
    {
        this.mark = true;
        this.name = false;
    }
    
    //--------------------------------------------------------------------------
    
    public void setName()
    {
        this.name = true;
        this.mark = false;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getMark()
    {
        return this.mark;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getName()
    {
        return this.name;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getMenu()
    {
        return this.menu;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getDataMenu()
    {
        return this.dataMenu;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getViewData()
    {
        return this.viewData;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getSearchData()
    {
        return this.searchData;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getSearchResults()
    {
        return this.searchResults;
    }
    
    //--------------------------------------------------------------------------
    
    public void setDataMenu()
    {
        this.dataMenu = true;
        
        this.menu = false;
        this.searchData = false;
        this.viewData = false;
        this.searchResults = false;
        
        this.setChanged();
        this.notifyObservers(this);
    }
    
    //--------------------------------------------------------------------------
    
    public void setViewData()
    {
        this.viewData = true;
        
        this.menu = false;
        this.searchData = false;
        this.dataMenu = false;
        this.searchResults = false;
        
        this.setChanged();
        this.notifyObservers(this);
    }
    
    //--------------------------------------------------------------------------
    
    public void setSearchData()
    {
        this.searchData = true;
        
        this.dataMenu = false;
        this.menu = false;
        this.viewData = false;
        this.searchResults = false;
        
        this.setChanged();
        this.notifyObservers(this);
    }
    
    //--------------------------------------------------------------------------
    
    public void setSearchResults()
    {
        this.searchResults = true;
        
        this.searchData = false;
        this.menu = false;
        this.viewData = false;
        
        this.setChanged();
        this.notifyObservers(this);
    }
}
