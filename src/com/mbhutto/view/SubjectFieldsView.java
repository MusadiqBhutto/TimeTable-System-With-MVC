package com.mbhutto.view;

import com.mbhutto.controller.DepartmentController;
import com.mbhutto.controller.SubjectController;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.DepartmentTermSubject;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Subject;
import com.mbhutto.util.Constants;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectFieldsView extends JDialog
{
    private  JPanel subjectPanel                                                   ;
    private  JPanel fieldsPanel                                                    ;
    private  JPanel deptSelectPanel                                                ;
    private  JPanel checkBoxPanel                                                  ;
    private  JPanel comboBoxPanel                                                  ;
    private  JPanel buttonsPanel                                                   ;
    private  JLabel subjectIdLabel                                                 ;
    private  JLabel subjectCodeLabel                                               ;
    private  JLabel subjectNameLabel                                               ;
    private  JLabel subjectShortNameLabel                                          ;
    private  JLabel subjectTheoryMarksLabel                                        ;
    private  JLabel subjectTheoryClassessLabel                                     ;
    private  JLabel subjectPracticalMarksLabel                                     ;
    private  JLabel subjectPracticalClassessLabel                                  ;
    private  JTextField subjectIdField                                             ;
    private  JTextField subjectCodeField                                           ;
    private  JTextField subjectNameField                                           ;
    private  JTextField subjectShortNameField                                      ;
    private  JTextField subjectTheoryMarksField                                    ;
    private  JTextField subjectTheoryClassessField                                 ;
    private  JTextField subjectPracticalMarksField                                 ;
    private  JTextField subjectPracticalClassessField                              ;
    private  JCheckBox departmentCheckBox[]                                        ;
    private  JLabel    selectTermLabel[];                                          ;
    private  JComboBox deptTermsCombo[]                                            ;
    Result result                                                                  ;
    private Subject subject                                                        ;
    private  JButton button1                                                       ;
    private  JButton button2                                                       ;
    private String view = ""                                                       ;
    private Result subjectDepartmentsTermsResult                                   ;
    private SubjectController   subjectController                                  ;
    DepartmentController    departmentController                                   ;

    private void setView(String view)
    {
        this.view = view;
    }

    public void changeView(String view)
    {
        setView(view);
        changeFieldsEditableMode(true);
        setButtonsLabels();
    }

    public SubjectFieldsView(JFrame frame, String title, boolean modal, String view, Subject subject) throws SQLException, ClassNotFoundException
    {    
        super(frame, title, modal);
        this.subject = subject ;
        setView(view);

        subjectController    = new SubjectController();
        departmentController = new  DepartmentController ();
        Department dept      = new Department();
        result = departmentController.getRecords();

        if(subject != null)
        {
            subjectDepartmentsTermsResult = subjectController.getSubjectDepartmentTerms(subject);
        }

        setLayout(new BorderLayout());

        // subjectPanel
        subjectPanel = new JPanel();
        subjectPanel.setLayout(new GridLayout(2,1,20,5));
        subjectPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Subject", TitledBorder.CENTER, TitledBorder.TOP));
        JScrollPane scroll = new JScrollPane(subjectPanel);

        // fieldsPanel
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout( new GridLayout(8,2,20,5));
        fieldsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,15,10,15), new EtchedBorder()));

        // deptSelectPanel
        deptSelectPanel = new JPanel();
        deptSelectPanel.setLayout( new GridLayout(1,2,20,5));

        // checkBoxPanel
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout( new GridLayout(result.getLength(), 2,20,5));
        deptSelectPanel.add(checkBoxPanel);

        // comboBoxPanel
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout( new GridLayout(result.getLength(),1,20,5));
        deptSelectPanel.add(comboBoxPanel);

        // buttonsPanel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(1,2,20,5));
        buttonsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,150,10,150), new EtchedBorder()));


        // LABELS & TEXTFIELDS
        subjectIdLabel                    = new JLabel("Subject ID : ");
        subjectCodeLabel                  = new JLabel("Subject Code : ");
        subjectNameLabel                  = new JLabel("Subject Name : ");
        subjectShortNameLabel             = new JLabel("Subject Short Name : ");
        subjectTheoryMarksLabel           = new JLabel("Subject Theory Marks: ");
        subjectTheoryClassessLabel        = new JLabel("Subject Theory Classess (per week) : ");
        subjectPracticalMarksLabel        = new JLabel("Subject Practical Marks: ");
        subjectPracticalClassessLabel     = new JLabel("Subject Practical Classess (per week) : ");

        if(view == Constants.ADD_VIEW)
        {
            subjectIdField                      = new JTextField("To be generated...");
            subjectCodeField                    = new JTextField();
            subjectNameField                    = new JTextField();
            subjectShortNameField               = new JTextField();
            subjectTheoryMarksField             = new JTextField();
            subjectTheoryClassessField          = new JTextField();
            subjectPracticalMarksField          = new JTextField();
            subjectPracticalClassessField       = new JTextField();
        }

        else if (view == Constants.UPDATE_VIEW)
        {
            setSubjectFieldValues();
        }

        else if (view == Constants.DISPLAY_VIEW)
        {
            setSubjectFieldValues();
            changeFieldsEditableMode(false);
        }


        departmentCheckBox                  = new JCheckBox[result.getLength()];
        selectTermLabel                     = new JLabel[result.getLength()];
        deptTermsCombo                      = new JComboBox[result.getLength()];
        for(int i=0; i<deptTermsCombo.length; i++)
        {
            deptTermsCombo[i] = new JComboBox();

        }

        long departmentId = -1;
        long termId = -1;
        Department department;
        
        
        for (int i = 0; i < result.getLength(); i++)
        {   System.out.println(result.getRecords()[i][0]);
        System.out.println(result.getRecords()[i][1]);
        System.out.println(result.getRecords()[i][2]);
        System.out.println(result.getRecords()[i][3]);
        System.out.println(result.getRecords()[i][4]);
           
            department = new Department(
                    Long.parseLong(result.getRecords()[i][0]),
                    result.getRecords()[i][1],
                    result.getRecords()[i][2],
                    Integer.parseInt(result.getRecords()[i][3]),
                    Integer.parseInt(result.getRecords()[i][4])
            );

            if(subjectDepartmentsTermsResult != null && subjectDepartmentsTermsResult.getLength() > 0)
            {
                for(int j=0; j < subjectDepartmentsTermsResult.getLength(); j++)
                {
                    if(Long.parseLong(subjectDepartmentsTermsResult.getRecords()[j][0]) == department.getDepartmentID())
                    {
                        departmentId = Long.parseLong(subjectDepartmentsTermsResult.getRecords()[j][0]) ;
                        termId = Long.parseLong(subjectDepartmentsTermsResult.getRecords()[j][1]) ;
                    }
                }
            }

            departmentCheckBox[i] = new JCheckBox(department.toString());
            if(departmentId != -1)
            {
                departmentCheckBox[i].setSelected(true);
            }

            deptTermsCombo[i].addItem("Select Term");
            for(int j=0; j<Integer.parseInt(result.getRecords()[i][3]); j++)
            {
                deptTermsCombo[i].addItem(j+1);
                if(departmentId != -1 && termId != -1 && termId == (j+1))
                {
                    deptTermsCombo[i].setSelectedIndex(j+1);
                }

            }

             departmentId = -1;
             termId = -1;

        }

        for(int i=0; i<departmentCheckBox.length; i++)
        {
            checkBoxPanel.add(departmentCheckBox[i]);
        }

        for(int i=0; i<deptTermsCombo.length; i++)
        {
            comboBoxPanel.add(selectTermLabel[i] = new JLabel("Select Term :"));
            comboBoxPanel.add(deptTermsCombo[i]);
        }


        subjectIdField.setEditable(false);
        fieldsPanel.add(subjectIdLabel);
        fieldsPanel.add(subjectIdField);
        fieldsPanel.add(subjectCodeLabel);
        fieldsPanel.add(subjectCodeField);
        fieldsPanel.add(subjectNameLabel);
        fieldsPanel.add(subjectNameField);
        fieldsPanel.add(subjectShortNameLabel);
        fieldsPanel.add(subjectShortNameField);
        fieldsPanel.add(subjectTheoryMarksLabel);
        fieldsPanel.add(subjectTheoryMarksField);
        fieldsPanel.add(subjectTheoryClassessLabel);
        fieldsPanel.add(subjectTheoryClassessField);
        fieldsPanel.add(subjectPracticalMarksLabel);
        fieldsPanel.add(subjectPracticalMarksField);
        fieldsPanel.add(subjectPracticalClassessLabel);
        fieldsPanel.add(subjectPracticalClassessField);


        // BUTTONS
        button1 = new JButton();
        button2 = new JButton();
        setButtonsLabels();
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);


        // ACTION LISTENER FOR button1
        String finalView = view;
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                if(e.getActionCommand() == Constants.CREATE_BUTTON_LABEL)
                {
                    String theorymarks = subjectTheoryMarksField.getText();
                    int theoryMarks = Integer.parseInt(theorymarks);
                    String theoryclasses = subjectTheoryClassessField.getText();
                    int theoryClasses = Integer.parseInt(theoryclasses);
                    String practicalmarks = subjectPracticalMarksField.getText();
                    int practicalMarks = Integer.parseInt(practicalmarks);
                    String practicalclasses = subjectPracticalClassessField.getText();
                    int practicalClasses = Integer.parseInt(practicalclasses);

                    String message = "";
                    ArrayList<DepartmentTermSubject> departmentTermSubjects = new ArrayList<DepartmentTermSubject>() ;
                    String error = "";
                    int totalSelectedDepartments = 0;

                    DepartmentTermSubject departmentTermSubject ;
                    for(int i=0; i<departmentCheckBox.length; i++)
                    {
                        if(departmentCheckBox[i].isSelected() && deptTermsCombo[i].getSelectedIndex()==0)
                        {
                            error = "Please select departments and terms correctly.";

                        }
                        else  if(departmentCheckBox[i].isSelected() && deptTermsCombo[i].getSelectedIndex()!=0)
                        {
                            departmentTermSubject = new DepartmentTermSubject(Long.parseLong(result.getRecords()[i][0]), (int) deptTermsCombo[i].getSelectedItem());
                            departmentTermSubjects.add(departmentTermSubject);
                            totalSelectedDepartments++;
                        }
                    }

                    Subject insertSubject = null;





                    if(error != "")
                    {
                        JOptionPane.showMessageDialog(fieldsPanel, error, "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        try
                        {
                            insertSubject = new Subject(-1, subjectCodeField.getText(), subjectNameField.getText(), subjectShortNameField.getText(), theoryMarks, theoryClasses, practicalMarks, practicalClasses);
                            insertSubject.setSubjectId(subjectController.addRecord(insertSubject));
                            for(int i=0; i<departmentTermSubjects.size(); i++)
                            {
                                departmentTermSubjects.get(i).setSubjectId(insertSubject.getSubjectId());
                                departmentTermSubjects.get(i).addRecord();
                                System.out.println(departmentTermSubjects.get(i).toString());
                            }
                            clearFields();
                        }
                        catch (SQLException throwables)
                        {
                            throwables.printStackTrace();
                        }
                        catch (ClassNotFoundException classNotFoundException)
                        {
                            classNotFoundException.printStackTrace();
                        }
                        if (insertSubject.getSubjectId() != -1)
                        {
                            message = "Subject added successfully.";
                        }
                        else
                        {
                            message = "Subject not added.";
                        }

                        JOptionPane.showMessageDialog(fieldsPanel, message, "Message", JOptionPane.INFORMATION_MESSAGE);

                        if (insertSubject.getSubjectId() != -1)
                        {
                            disposeDialog();
                        }
                    }
                }
                else if (e.getActionCommand() == Constants.UPDATE_BUTTON_LABEL)
                {
                    subject.setSubjectId(Long.parseLong(subjectIdField.getText()));
                    subject.setSubjectCode(subjectCodeField.getText());
                    subject.setSubjectName(subjectNameField.getText());
                    subject.setSubjectShortName(subjectShortNameField.getText());
                    subject.setSubjectTheoryMarks(Integer.parseInt(subjectTheoryMarksField.getText()));
                    subject.setSubjectTheoryClassesInWeek(Integer.parseInt(subjectTheoryClassessField.getText()));
                    subject.setSubjectPracticalMarks(Integer.parseInt(subjectPracticalMarksField.getText()));
                    subject.setSubjectPracticalClassesInWeek(Integer.parseInt(subjectPracticalClassessField.getText()));

                    try
                    {
                    	subjectController.updateRecord(subject);
                    }
                    catch (SQLException throwables)
                    {
                        throwables.printStackTrace();
                    }
                    catch (ClassNotFoundException classNotFoundException)
                    {
                        classNotFoundException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(fieldsPanel, "Subject Updated Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                    disposeDialog();
                }
                else if (e.getActionCommand() == Constants.EDIT_BUTTON_LABEL)
                {
                    changeView(Constants.UPDATE_VIEW);
                }
            }

        });


        // ACTION LISTENER FOR button2
        String finalView1 = view;
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand() == Constants.CLEAR_BUTTON_LABEL)
                {
                    clearFields();
                }

                else if (e.getActionCommand() == Constants.RESET_BUTTON_LABEL)
                {
                    subjectIdField.setText(String.valueOf(subject.getSubjectId()));
                    subjectCodeField.setText(subject.getSubjectCode());
                    subjectNameField.setText(subject.getSubjectName());
                    subjectShortNameField.setText(subject.getSubjectShortName());
                    subjectTheoryMarksField.setText(String.valueOf(subject.getSubjectTheoryMarks()));
                    subjectTheoryClassessField.setText(String.valueOf(subject.getSubjectTheoryClassesInWeek()));
                    subjectPracticalMarksField.setText(String.valueOf(subject.getSubjectPracticalMarks()));
                    subjectPracticalClassessField.setText(String.valueOf(subject.getSubjectPracticalClassesInWeek()));
                }

                else if (e.getActionCommand() == Constants.CANCEL_BUTTON_LABEL)
                {
                    disposeDialog();
                }
            }
        });


        // ADDING PANELS INTO THE FRAME
        subjectPanel.add(fieldsPanel);
        subjectPanel.add(deptSelectPanel);

        add(scroll,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);

}

    private void clearFields()
    {
        subjectCodeField.setText("");
        subjectNameField.setText("");
        subjectShortNameField.setText("");
        subjectTheoryMarksField.setText("");
        subjectTheoryClassessField.setText("");
        subjectPracticalMarksField.setText("");
        subjectPracticalClassessField.setText("");
    }

    private void setButtonsLabels()
    {
        if(view == Constants.ADD_VIEW)
        {
            button1.setText(Constants.CREATE_BUTTON_LABEL);
            button2.setText(Constants.CLEAR_BUTTON_LABEL);
        }
        else if (view == Constants.UPDATE_VIEW)
        {
            button1.setText(Constants.UPDATE_BUTTON_LABEL);
            button2.setText(Constants.RESET_BUTTON_LABEL);
        }
        else if (view == Constants.DISPLAY_VIEW)
        {
            button1.setText(Constants.EDIT_BUTTON_LABEL);
            button2.setText(Constants.CANCEL_BUTTON_LABEL);
        }
    }

    private void disposeDialog()
    {
        setVisible(false);
        dispose();
    }

    private void changeFieldsEditableMode(boolean mode)
    {
        subjectCodeField.setEditable(mode);
        subjectNameField.setEditable(mode);
        subjectShortNameField.setEditable(mode);
        subjectTheoryMarksField.setEditable(mode);
        subjectTheoryClassessField.setEditable(mode);
        subjectPracticalMarksField.setEditable(mode);
        subjectPracticalClassessField.setEditable(mode);
    }

    private void setSubjectFieldValues()
    {
        subjectIdField                      = new JTextField(String.valueOf(subject.getSubjectId()));
        subjectCodeField                    = new JTextField(subject.getSubjectCode());
        subjectNameField                    = new JTextField(subject.getSubjectName());
        subjectShortNameField               = new JTextField(subject.getSubjectShortName());
        subjectTheoryMarksField             = new JTextField(String.valueOf(subject.getSubjectTheoryMarks()));
        subjectTheoryClassessField          = new JTextField(String.valueOf(subject.getSubjectTheoryClassesInWeek()));
        subjectPracticalMarksField          = new JTextField(String.valueOf(subject.getSubjectPracticalMarks()));
        subjectPracticalClassessField       = new JTextField(String.valueOf(subject.getSubjectPracticalClassesInWeek()));
    }
}
