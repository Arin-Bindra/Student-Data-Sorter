/**
 * Arin Bindra
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class FileData {
    
    private String studentDataFile = "./student_Data.txt";
    private ArrayList<String> lines;
    private String[] names;
    private int[] marks;
    private Student[] students;
    
    public FileData()
    {
        
    }
    
    //--------------------------------------------------------------------------
    
    public void readFile(String file)
    {
        this.lines = new ArrayList<>();
        
        try
        {
            BufferedReader inStream = new BufferedReader(new FileReader(file));
           
            String line = null;
        
            while((line = inStream.readLine()) != null)
            {
                this.lines.add(line);
            }
            inStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        
        catch(IOException e)
        {
            System.out.println("Error reading from file");
        }
        
        this.sortData();
    }
    
    //--------------------------------------------------------------------------
    
    private void sortData()
    {
        this.names = new String[this.lines.size()];
        this.marks = new int[this.lines.size()];
        
        for(int i=0; i < this.lines.size(); i++)
        {
            String name = "";
            String markS = "";
            
            int count = 0;
            boolean markT = false;
            
            for(int j=0; j < this.lines.get(i).length(); j++)
            {
                if(this.lines.get(i).equals("") == false)
                {
                    int ascii = (int) this.lines.get(i).charAt(j);

                    if((count > 0) && (ascii != 44))
                    {
                        if(markT == true)
                        {
                            markS = markS + this.lines.get(i).charAt(j);
                        }
                        else
                        {
                            name = name + this.lines.get(i).charAt(j);
                        }
                    }

                    if(ascii == 32)
                    {
                        count++;
                    }

                    if(ascii == 44)
                    {
                        count = 0;
                        markT = true;
                    }
                }
            }
            
            this.names[i] = name;
            this.marks[i] = Integer.parseInt(markS);
        }
        
        this.createStudents();
    }
    
    //--------------------------------------------------------------------------
    
    private void createStudents()
    {
        this.students = new Student[this.lines.size()];
        
        for(int i=0; i < this.lines.size(); i++)
        {
            this.students[i] = new Student(this.names[i], this.marks[i]);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public Student[] getStudents()
    {
        return this.students;
    }
    
    //--------------------------------------------------------------------------
    
    public void writeData(Student[] students)
    {
        PrintWriter outputStream = null;
        
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(this.studentDataFile));
            
            for(int i=0; i < students.length; i++)
            {
                outputStream.println("Student " + students[i].getName() + ", " + students[i].getMark());
            }
            
            outputStream.flush();
            outputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening Student data file for saving");
        }
    }
}
