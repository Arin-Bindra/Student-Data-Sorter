/**
 * Arin Bindra
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    
    public View view;
    public Model model;
    
    //--------------------------------------------------------------------------
    
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    
    //--------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        
        if(this.model.getMenu() == true)
        {
            switch(command)
            {
                case "Load txt File" -> {
                    this.model.setFile();
                    
                    if(this.model.fileSelected() == true)
                    {
                        this.model.setDataMenu();
                    }
                    
                    break;
                }
                    
                default -> {
                    break;
                }
            }
        }
        
        if(this.model.getDataMenu() == true)
        {
            switch(command)
            {
                case "View all Students" -> {
                    this.model.setViewData();
                    break;
                }
                
                case "Search for Student" -> {
                    this.model.setSearchData();
                    break;
                }
                
                default -> {
                    break;
                }
            }
        }
        
        if(this.model.getViewData() == true)
        {
            switch(command)
            {
                case "Sort by Name" -> {
                    this.model.setName();
                    this.model.setViewData();
                    break;
                }
                
                case "Sort by Grade" -> {
                    this.model.setMark();
                    this.model.setViewData();
                    break;
                }
                
                case "Reverse Order" -> {
                    if(this.model.getMark() == true)
                    {
                        this.model.reverseMark();
                    }
                    
                    if(this.model.getName() == true)
                    {
                        this.model.reverseName();
                    }
                    this.model.setViewData();
                    break;
                }
                
                case "Save File" -> {
                    this.model.saveData();
                    this.model.setDataMenu();
                    break;
                }
                
                case "Back to Menu" -> {
                    this.model.setDataMenu();
                    break;
                }
                
                default -> {
                    break;
                }
            }
        }
        
        if(this.model.getSearchData() == true)
        {
            switch(command)
            {
                case "Search" -> {
                    String input = this.view.studentInput.getText();
                    this.model.findStudent(input);
                    this.model.setSearchResults();
                    break;
                }
                
                case "Back to Menu" -> {
                    this.model.setDataMenu();
                    break;
                }
                
                default -> {
                    break;
                }
            }
        }
        
        if(this.model.getSearchResults() == true)
        {
            switch(command)
            {
                case "Back to Menu" -> {
                    this.model.setDataMenu();
                    break;
                }
                
                default -> {
                    break;
                }
            }
        }
    }
    
}
