package com.mbhutto.view;

import com.mbhutto.util.Constants;

import javax.swing.border.* ;
import java.awt.event.* 	;
import javax.swing.*   		;
import java.awt.*       	;
import java.sql.SQLException;

public class Navigation extends JPanel
{
	private EmptyBorder    outer    ;
	private Border         middle  	;
	private EmptyBorder    inner   	;
	private TeacherDisplay teacherDisplay; 
	private TimeTableDisplay timeTableDisplay;
	private SubjectDisplay subjectDisplay;
	private DepartmentDisplay departmentDisplay;
	private JFrame parent;
	final String[] current = new String[1];

	public Navigation(JFrame parent) throws SQLException, ClassNotFoundException {
		this.parent = parent ;
        outer  = new EmptyBorder(0,5,0,0);
        middle = BorderFactory.createRaisedBevelBorder();
        inner  = new EmptyBorder(17,5,18,5);
		setBorder(new CompoundBorder(outer,new CompoundBorder(middle,inner)));
		setLayout( new GridLayout (4,1,0,15));
		addButton (Constants.DEPARTMENTS_TEXT, new ImageIcon("Teacher.gif"));			//add Teacher Button
		addButton (Constants.TEACHERS_TEXT   , new ImageIcon("Teacher.gif"));			//add Teacher Button
		addButton (Constants.SUBJECTS_TEXT   , new ImageIcon("Subject.png"));			//add Subject Button
		addButton (Constants.TIME_TABLE_TEXT   , new ImageIcon("TimeTable.png"));		//add TimeTable Button

		addDepartmentsDisplay(parent);
	}

	private void addButton(String text, ImageIcon icon )
	{
		JButton navigationButton = new JButton (text,icon);
		navigationButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		navigationButton.setHorizontalTextPosition(SwingConstants.CENTER);
		navigationButton.setFont(new Font("Monotype Corsiva", Font.ITALIC,32));
		navigationButton.setForeground(new Color (127,0,0));
		navigationButton.setMargin(new Insets(0, 0, 0, 0));
		this.add(navigationButton);
		current[0] = Constants.DEPARTMENTS_TEXT;

		navigationButton.addActionListener(e ->
		{
		//	System.out.println("Current in nav: " + current[0]);
		//	System.out.println("Button text: " + e.getActionCommand());
			if(e.getActionCommand().equals(Constants.DEPARTMENTS_TEXT) && current[0] != Constants.DEPARTMENTS_TEXT)
			{
			//	System.out.println("1. " + e.getActionCommand());
				removeView(current[0]);
				try
				{
					current[0] = Constants.DEPARTMENTS_TEXT;
				//	System.out.println("current in if: " + current[0]);
					addDepartmentsDisplay(this.parent);
				}
				catch (SQLException throwable) {
					throwable.printStackTrace();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				}
			}
			
		   else if(e.getActionCommand().equals(Constants.TEACHERS_TEXT) && current[0] != Constants.TEACHERS_TEXT)
		   {
		   	 //   System.out.println("2. " + e.getActionCommand());
			    removeView(current[0]);
			    try
				{
					current[0] = Constants.TEACHERS_TEXT;
				//	System.out.println("current in if: " + current[0]);
			    	addTeachersDisplay(this.parent);
			    }
			    catch (SQLException throwable) {
			    throwable.printStackTrace(); } catch (ClassNotFoundException
			    classNotFoundException) { classNotFoundException.printStackTrace();
			    }
		   }
			
		   else if(e.getActionCommand().equals(Constants.SUBJECTS_TEXT) && current[0] != Constants.SUBJECTS_TEXT)
		   {
		   	//	System.out.println("3. " + e.getActionCommand());
			    removeView(current[0]);
			  	try
			  	{
					current[0] = Constants.SUBJECTS_TEXT;
			//		System.out.println("current in if: " + current[0]);
				  	addSubjectsDisplay(this.parent);
			  	}
			    catch (SQLException throwable) {
			  	throwable.printStackTrace(); } catch (ClassNotFoundException
			  	classNotFoundException) { classNotFoundException.printStackTrace(); }
		   }

		   else if(e.getActionCommand().equals(Constants.TIME_TABLE_TEXT) && current[0] != Constants.TIME_TABLE_TEXT)
		   {
			// 	System.out.println("4. " + e.getActionCommand());
			    removeView(current[0]);;
			  	try
				{
					 current[0] = Constants.TIME_TABLE_TEXT;
				//	 System.out.println("current in if: " + current[0]);
					 addTimeTableDisplay(this.parent);
				}
			  	catch (SQLException throwable) {
				throwable.printStackTrace(); } catch (ClassNotFoundException
				classNotFoundException) { classNotFoundException.printStackTrace(); }
		   }

		});
	}

	private void removeView(String currentView)
	{
		if(currentView == Constants.DEPARTMENTS_TEXT)
		{
			removeDepartmentsView();
		}
		else if (currentView == Constants.TEACHERS_TEXT)
		{
			removeTeachersView();
		}
		else if (currentView == Constants.SUBJECTS_TEXT)
		{
			removeSubjectsView();
		}
		else if (currentView == Constants.TIME_TABLE_TEXT)
		{
			removeTimeTableView();
		}
	}


	private void removeDepartmentsView()
	  {
	  	 if(departmentDisplay != null)
	 	 {
	  		parent.getContentPane().remove(departmentDisplay);
	  		departmentDisplay = null;
	 	 }
	  	 parent.revalidate();
	  }
	  
	 
	
	  private void removeTeachersView() { if(teacherDisplay != null) {
	  parent.getContentPane().remove(teacherDisplay); teacherDisplay = null; }
	  parent.revalidate(); }
	 
	  
	  private void removeSubjectsView() { if(subjectDisplay != null) {
	  parent.getContentPane().remove(subjectDisplay); subjectDisplay = null; }
	  parent.revalidate(); }
	  
	
	  private void removeTimeTableView() { if(timeTableDisplay != null) {
	  parent.getContentPane().remove(timeTableDisplay); timeTableDisplay = null ; }
	  parent.revalidate(); }
	 

	private void addDepartmentsDisplay(JFrame parent) throws SQLException, ClassNotFoundException {
		departmentDisplay = new DepartmentDisplay(parent);
		parent.getContentPane().add(departmentDisplay, BorderLayout.CENTER);
		parent.revalidate();
	}
	
	  private void addTeachersDisplay(JFrame parent) throws SQLException,
	  ClassNotFoundException { teacherDisplay = new TeacherDisplay(parent);
	  parent.getContentPane().add(teacherDisplay, BorderLayout.CENTER);
	  parent.revalidate(); }
	  
	   private void addSubjectsDisplay(JFrame parent) throws SQLException, ClassNotFoundException 
	   { 
		   subjectDisplay = new SubjectDisplay(parent);
		   parent.getContentPane().add(subjectDisplay, BorderLayout.CENTER);
		   parent.revalidate(); 
	   }
	  
	  private void addTimeTableDisplay(JFrame parent) throws SQLException,
	  ClassNotFoundException { timeTableDisplay = new TimeTableDisplay(parent);
	  parent.getContentPane().add(timeTableDisplay, BorderLayout.CENTER);
	  parent.revalidate(); }
	 
	   }