/**
 * Arin Bindra
 *
 */

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.*;

public class View extends JFrame implements Observer{
    
    boolean started = false;
    
    //All Panels
    private JPanel menuPanel = new JPanel();
    private JPanel dataMenuPanel = new JPanel();
    private JPanel viewDataPanel = new JPanel();
    private JPanel searchDataPanel = new JPanel();
    private JPanel searchResultsPanel = new JPanel();
    
    //Menu Panel
    private JLabel welcomeLabelMP = new JLabel("Welcome to the Student Data Viewer");
    private JButton menuLoadFile = new JButton("Load txt File");
    
    //Data Menu Panel
    private JLabel dataMenuLabelDMP = new JLabel("What would you like to do?");
    private JButton viewDataButtonDMP = new JButton("View all Students");
    private JButton searchDataButtonDMP = new JButton("Search for Student");
    
    //View Data Panel
    private JButton sortNameButtonVDP = new JButton("Sort by Name");
    private JButton sortGradeButtonVDP = new JButton("Sort by Grade");
    private JButton reverseOrderButtonVDP = new JButton("Reverse Order");
    private JButton saveButtonVDP = new JButton("Save File");
    private JButton menuButtonVDP = new JButton("Back to Menu");
    private JScrollPane scrollDataVDP = new JScrollPane();
    
    private JTextArea dataText = new JTextArea();
    
    
    //Search Data Panel
    private JLabel searchDataLabelSDP = new JLabel("Please search for a Student via their mark");
    public JTextField studentInput = new JTextField(10);
    private JButton searchButtonSDP = new JButton("Search");
    private JButton menuButtonSDP = new JButton("Back to Menu");
    
    //Seach Results Panel
    private JLabel searchResultsSRP = new JLabel();
    private JButton menuButtonSRP = new JButton("Back to Menu");
    
    //--------------------------------------------------------------------------
    
    public View()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        
        this.menuPanel();
    }
    
    //--------------------------------------------------------------------------
    
    public void addActionListener(ActionListener listener)
    {
        //Menu Panel
        this.menuLoadFile.addActionListener(listener);
        
        //Data Menu Panel
        this.viewDataButtonDMP.addActionListener(listener);
        this.searchDataButtonDMP.addActionListener(listener);
        
        //View Data Panel
        this.sortNameButtonVDP.addActionListener(listener);
        this.sortGradeButtonVDP.addActionListener(listener);
        this.reverseOrderButtonVDP.addActionListener(listener);
        this.menuButtonVDP.addActionListener(listener);
        this.saveButtonVDP.addActionListener(listener);
        
        //Search Data Panel
        this.searchButtonSDP.addActionListener(listener);
        this.menuButtonSDP.addActionListener(listener);
        
        //Search Results Panel
        this.menuButtonSRP.addActionListener(listener);
    }
    
    //--------------------------------------------------------------------------
    
    private void menuPanel()
    {
        this.menuPanel.add(this.welcomeLabelMP);
        this.menuPanel.add(this.menuLoadFile);
        
        this.add(this.menuPanel);
        this.setVisible(true);
    }
    
    //--------------------------------------------------------------------------
    
    private void dataMenuPanel()
    {
        this.dataMenuPanel.add(this.dataMenuLabelDMP);
        this.dataMenuPanel.add(this.viewDataButtonDMP);
        this.dataMenuPanel.add(this.searchDataButtonDMP);
        
        this.getContentPane().removeAll();
        this.dataMenuPanel.setVisible(true);
        this.add(this.dataMenuPanel);
        this.revalidate();
        this.repaint();
    }
    
    //--------------------------------------------------------------------------
    
    private void viewDataPanel()
    {
        this.dataText.setEditable(false);
        this.dataText.setLineWrap(true);
        this.dataText.setWrapStyleWord(true);
        
        this.scrollDataVDP.setViewportView(this.dataText);
        this.scrollDataVDP.setPreferredSize(new Dimension(600, 200));
        this.scrollDataVDP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollDataVDP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.viewDataPanel.add(this.sortNameButtonVDP);
        this.viewDataPanel.add(this.sortGradeButtonVDP);
        this.viewDataPanel.add(this.reverseOrderButtonVDP);
        this.viewDataPanel.add(this.saveButtonVDP);
        this.viewDataPanel.add(this.menuButtonVDP);
        this.viewDataPanel.add(this.scrollDataVDP);
        
        this.getContentPane().removeAll();
        this.viewDataPanel.setVisible(true);
        this.add(this.viewDataPanel);
        this.revalidate();
        this.repaint();
    }
    
    //--------------------------------------------------------------------------
    
    private void searchDataPanel()
    {
        this.searchDataPanel.add(this.searchDataLabelSDP);
        this.searchDataPanel.add(this.studentInput);
        this.searchDataPanel.add(this.searchButtonSDP);
        this.searchDataPanel.add(this.menuButtonSDP);
        
        this.getContentPane().removeAll();
        this.searchDataPanel.setVisible(true);
        this.add(this.searchDataPanel);
        this.revalidate();
        this.repaint();
    }
    
    //--------------------------------------------------------------------------
    
    private void searchResultsPanel()
    {
        this.searchResultsPanel.add(this.searchResultsSRP);
        this.searchResultsPanel.add(this.menuButtonSRP);
        
        this.getContentPane().removeAll();
        this.searchResultsPanel.setVisible(true);
        this.add(this.searchResultsPanel);
        this.revalidate();
        this.repaint();
    }

    
    
    //--------------------------------------------------------------------------
    
    @Override
    public void update(Observable o, Object arg) 
    {
        Model model = (Model) arg;
        
        if(model != null)
        {
            if(model.getViewData() == true)
            {
                this.dataText.setText(model.getData());
                this.dataText.updateUI();
            }
        }
        
        if(model != null)
        {
            if(model.getSearchResults() == true)
            {
                this.searchResultsSRP.setText(model.getfindStudent());
            }
        }
        
        if(model != null)
        {
            if(model.getDataMenu() == true)
            {
                this.dataMenuPanel();
            }
            
            if(model.getViewData() == true)
            {
                this.viewDataPanel();
            }
            
            if(model.getSearchData() == true)
            {
                this.searchDataPanel();
            }
            
            if(model.getSearchResults() == true)
            {
                this.searchResultsPanel();
            }
        }
    }
    
}
