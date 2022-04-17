package com.mbhutto.view;

import com.mbhutto.controller.DepartmentController;
import com.mbhutto.controller.TimeTableController;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.TimeTable;
import com.mbhutto.util.Constants;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TimeTableDisplay extends JPanel
{
    JFrame parent   ;
    JPanel mainPanel;
    JPanel comboPanel;
    JPanel tablePanel;
    JPanel detailsPanel;
    JLabel deptLable;
    JLabel termLable;
    JLabel sectionLable;
    JComboBox deptComboBox;
    JComboBox termComboBox;
    JComboBox sectionComboBox;
    JPanel buttonPanel;
    String deptTableColumnNames[];
    JTable deptTable;
    JScrollPane deptTableScroll;
    JButton addButton;
    String timeTables[][];
    String timeTableSubjects[][];
    Result result;
    TimeTableFieldsView timeTableFieldsView ;
    Department selectedDepartment ;
    long selectedTerm ;
    long selectedSection;
    Result timeTableResult ;
    DefaultTableModel dm;
    ListModel lm;
    DepartmentController departmentController;
    TimeTableController  timeTableController;


    public TimeTableDisplay(JFrame parent) throws SQLException, ClassNotFoundException
    {
        setLayout(new BorderLayout());
        this.parent = parent ;
        departmentController = new DepartmentController();
        
       //  LABLES & COMBOBOXES
        deptLable    = new JLabel("Select Department : ");
        termLable    = new JLabel("Select Term : ");
        sectionLable = new JLabel("Select Section : ");
        deptComboBox                  = new JComboBox();
        termComboBox                  = new JComboBox();
        sectionComboBox               = new JComboBox();
        
        deptComboBox.addItem("Select Department");
        Department dept = new Department();
        result = departmentController.getRecords();
        for (int i = 0; i < result.getLength(); i++)
        {
        	Department department = new Department(
                    Long.parseLong(result.getRecords()[i][0]),
                    result.getRecords()[i][1],
                    result.getRecords()[i][2],
                    Integer.parseInt(result.getRecords()[i][3]),
                    Integer.parseInt(result.getRecords()[i][4])
            );
            deptComboBox.addItem(department); 
        }
        
        
       
        deptComboBox.addActionListener(e ->
        {
        	if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null)
            {
            	selectedDepartment = (Department) ((JComboBox) e.getSource()).getSelectedItem();
                int terms =  selectedDepartment.getDepartmentTerms();
                int sections =  selectedDepartment.getDepartmentSections();
                termComboBox.removeAllItems();
                sectionComboBox.removeAllItems();
                termComboBox.addItem("Select Term");
                sectionComboBox.addItem("Select Section");
                for (int i = 1; i <= terms; i++)
                {
                    termComboBox.addItem("" + i);     
                }
                for (int i = 1; i <= sections; i++)
                {
                    sectionComboBox.addItem("" + i);     
                }  
            }
        });      
            
        termComboBox.addActionListener(e ->
        {
        	if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null &&
        			((JComboBox) e.getSource()).getSelectedIndex() != 0)
            {
                try {
                    TimeTable timeTable = new TimeTable();
                    selectedTerm = Long.parseLong(((JComboBox) e.getSource()).getSelectedItem().toString());
                    timeTableController = new TimeTableController();
        			timeTableResult =  timeTableController.getTimeTable(selectedDepartment.getDepartmentID(), selectedTerm, selectedSection );
        			getClassTimeTable(timeTableResult);
        			
        		} catch (SQLException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}  catch (ClassNotFoundException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}  catch (NumberFormatException nfe) {
        			// TODO Auto-generated catch block
        			nfe.printStackTrace();
        		} 
            }
        });
        
        sectionComboBox.addActionListener(e ->
        {
        	if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null &&
        			((JComboBox) e.getSource()).getSelectedIndex() != 0)
            {
                try {
            		TimeTable timeTable = new TimeTable();
                    selectedSection = Long.parseLong(((JComboBox) e.getSource()).getSelectedItem().toString());
					timeTableResult =  timeTableController.getTimeTable(selectedDepartment.getDepartmentID(), selectedTerm, selectedSection );
					getClassTimeTable(timeTableResult);
					setTableAndData();
					displayInfoTable(selectedDepartment.getDepartmentID(), selectedTerm, selectedSection );;
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  catch (NumberFormatException nfe) {
        			// TODO Auto-generated catch block
        			nfe.printStackTrace();
        		} 
            }
        });
        
        
        
       


         
        
        
  /*      TimeTable timeTableSubjectTeachers = new TimeTable();
        Result timeTableSubjectTeachersResult = timeTableSubjectTeachers.getDistinctSubjectsTimeTable(6, 1, 3);
        timeTableSubjects = new String[timeTableSubjectTeachersResult.getLength()-1][4];
        String subCode = timeTableSubjectTeachersResult.getRecords()[0][0];
        for(int i=0; i<timeTableSubjects.length; i++)
        {
        	timeTableSubjects[i][0] = String.valueOf(i+1);
        	timeTableSubjects[i][1] = timeTableSubjectTeachersResult.getRecords()[i][1]+ "    "+timeTableSubjectTeachersResult.getRecords()[i][0];
        	timeTableSubjects[i][2] = timeTableSubjectTeachersResult.getRecords()[i][3]+ " + "+ String.valueOf(Integer.parseInt(timeTableSubjectTeachersResult.getRecords()[i][4]) / 3);
        	timeTableSubjects[i][3] = timeTableSubjectTeachersResult.getRecords()[i][5];
        }

        // TAble For Subject Information
        Object subjectColumnNames[] = { "S#", "Subject Name & Code", "C.H", "Teacher" };
        DefaultTableModel subjectModel = new DefaultTableModel(timeTableSubjects, subjectColumnNames);
        JTable Table_BookList = new JTable(subjectModel);
        JScrollPane scroll2 = new JScrollPane(Table_BookList);
        //  scroll2.setPreferredSize(new Dimension(600, 600));  */
        
        
        
        // TABLE/CENTER PANEL
        comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(3,3,10,10));
        comboPanel.add(deptLable);
        comboPanel.add(deptComboBox);
        comboPanel.add(termLable);
        comboPanel.add(termComboBox);
        comboPanel.add(sectionLable);
        comboPanel.add(sectionComboBox);
   
        // MAIN CENTER PANEL
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Class TimeTable", TitledBorder.CENTER, TitledBorder.TOP));
        mainPanel.setLayout(new GridLayout(3,1,20,20));
        
        // TABLE & DETAILS PANEL
        tablePanel = new JPanel();
         detailsPanel = new JPanel();
        
        // ADDING SUBPANELS IN MAIN PANEL
        mainPanel.add(comboPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(detailsPanel);
       
        this.add(mainPanel, BorderLayout.CENTER);

        // BUTTONS
        addButton = new JButton("Create Timetable");
        // BUTTONS/SOUTH PANEL
        buttonPanel = new JPanel(); // for buttons
        buttonPanel.add(addButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENER FOR addButton
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    timeTableFieldsView = new TimeTableFieldsView(parent, "Create Time Table", true);
               } catch (SQLException throwable) {
                    throwable.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
    }
    

    private void setTableAndData() {
		// TODO Auto-generated method stub
    	removeTable();
		setTable();
		setTableModal();
	}


	public void getClassTimeTable(Result timeTableResult )
    {
	   if(timeTableResult.getLength() == 0)
	   {
		   return ;
	   }
	   timeTables = new String[7][5];
       int timeTableIndex = 0 ;
       for(int i=0; i < 7; i++)
       {
           for(int j=0; j< 5; j++)
           {
               timeTables[i][j] = timeTableResult.getRecords()[timeTableIndex][7] ;
               if(timeTableIndex == 32)
               {
                   break ;
               }
               timeTableIndex++ ;
           }
       }
   }

    public void setTable()
    {
        // Data Table
        lm = new AbstractListModel()
       {
           String headers[] = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh"};
           public int getSize() { return headers.length; }
           public Object getElementAt(int index) {
               return headers[index];
           }
       };
    }
    
    public void setTableModal()
    {
        dm = new DefaultTableModel(timeTables,new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        JTable table = new JTable( dm );
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JList rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(100);
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(table));
        JScrollPane scroll1 = new JScrollPane( table );
        scroll1.setRowHeaderView(rowHeader);
        scroll1.setPreferredSize(new Dimension(478, 135));
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Displaying Class TimeTable", TitledBorder.CENTER, TitledBorder.TOP));
        tablePanel.add(scroll1);
    }
    
    public void removeTable() {
    	tablePanel.removeAll();
    	tablePanel.revalidate();
	}
    
    public void displayInfoTable(long deptId, long term, long sec) throws ClassNotFoundException, SQLException
    {    
    	 detailsPanel.removeAll();
    	 detailsPanel.revalidate();
    	 TimeTable timeTableSubjectTeachers = new TimeTable();
         Result timeTableSubjectTeachersResult =  timeTableController.getDistinctSubjectsTimeTable(deptId, term, sec);
         timeTableSubjects = new String[timeTableSubjectTeachersResult.getLength()-1][4];
         String subCode = timeTableSubjectTeachersResult.getRecords()[0][0];
         for(int i=0; i<timeTableSubjects.length; i++)
         {
         	timeTableSubjects[i][0] = String.valueOf(i+1);
         	timeTableSubjects[i][1] = timeTableSubjectTeachersResult.getRecords()[i][1]+ "    "+timeTableSubjectTeachersResult.getRecords()[i][0];
         	timeTableSubjects[i][2] = timeTableSubjectTeachersResult.getRecords()[i][3]+ " + "+ String.valueOf(Integer.parseInt(timeTableSubjectTeachersResult.getRecords()[i][4]) / 3);
         	timeTableSubjects[i][3] = timeTableSubjectTeachersResult.getRecords()[i][5];
         }

         // TAble For Subject Information
         Object subjectColumnNames[] = { "S#", "Subject Name & Code", "C.H", "Teacher" };
         DefaultTableModel subjectModel = new DefaultTableModel(timeTableSubjects, subjectColumnNames);
         JTable subjectInfoTable = new JTable(subjectModel);
         subjectInfoTable.getColumnModel().getColumn(0).setPreferredWidth(20);
         subjectInfoTable.getColumnModel().getColumn(1).setPreferredWidth(200);
         subjectInfoTable.getColumnModel().getColumn(2).setPreferredWidth(100);
         subjectInfoTable.getColumnModel().getColumn(3).setPreferredWidth(100);
         JScrollPane scroll2 = new JScrollPane(subjectInfoTable);
         detailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Subject & Teachers Info", TitledBorder.CENTER, TitledBorder.TOP));
         scroll2.setPreferredSize(new Dimension(600, 150)); 
         detailsPanel.add(scroll2);
         
    }
}

class RowHeaderRenderer extends JLabel implements ListCellRenderer {

    RowHeaderRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    public Component getListCellRendererComponent( JList list,
                                                   Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}