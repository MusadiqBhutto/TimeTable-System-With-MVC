package com.mbhutto.view;

import com.mbhutto.util.Constants;
import com.mbhutto.controller.DepartmentController;
import com.mbhutto.entity.Department;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DepartmentFieldsView extends JDialog
{
    private  JPanel addDepartmentPanel                   ;
    private  JPanel fieldsPanel                          ;
    private  JPanel buttonsPanel                         ;
    private  JLabel departmentIdLabel                    ;
    private  JLabel departmentNameLabel                  ;
    private  JLabel departmentShortNameLabel             ;
    private  JLabel departmentTermsLabel                 ;
    private  JLabel departmentSectionsLabel              ;
    private  JTextField departmentIdField                ;
    private  JTextField departmentNameField              ;
    private  JTextField departmentShortNameField         ;
    private  JTextField departmentTermsField             ;
    private  JTextField departmentSectionsField          ;
    private  JButton button1                             ;
    private  JButton button2                             ;
    private Department department                        ;
    private String view = ""                             ;
    private DepartmentController departmentController    ;

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

    public DepartmentFieldsView(JFrame frame, String title, boolean modal, String view, Department department)
    {
        super(frame, title, modal);
        this.department = department ;
        setView(view);

        setLayout(new BorderLayout());

        // addDepartmentPanel
        addDepartmentPanel = new JPanel();
        addDepartmentPanel.setLayout(new BorderLayout());
        addDepartmentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add New Department", TitledBorder.CENTER, TitledBorder.TOP));

        // fieldsPanel
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout( new GridLayout(5,2,10,10));
        fieldsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,15,10,15), new EtchedBorder()));

        // buttonsPanel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(1,2,50,10));
        buttonsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,50,10,50), new EtchedBorder()));

        // LABELS & TEXTFIELDS
        departmentIdLabel          = new JLabel("Department ID : ");
        departmentNameLabel        = new JLabel("Department Name : ");
        departmentShortNameLabel   = new JLabel("Department Short Name : ");
        departmentTermsLabel       = new JLabel("Department Terms : ");
        departmentSectionsLabel    = new JLabel("Department Sections : ");

        if(view == Constants.ADD_VIEW)
        {
            departmentIdField          = new JTextField("To be generated...");
            departmentNameField        = new JTextField();
            departmentShortNameField   = new JTextField();
            departmentTermsField       = new JTextField();
            departmentSectionsField    = new JTextField();
        }
        else if (view == Constants.UPDATE_VIEW)
        {
            setDepartmentFieldValues();
        }

        else if (view == Constants.DISPLAY_VIEW)
        {
            setDepartmentFieldValues();
            changeFieldsEditableMode(false);
        }

        departmentIdField.setEditable(false);
        fieldsPanel.add(departmentIdLabel);
        fieldsPanel.add(departmentIdField);
        fieldsPanel.add(departmentNameLabel);
        fieldsPanel.add(departmentNameField);
        fieldsPanel.add(departmentShortNameLabel);
        fieldsPanel.add(departmentShortNameField);
        fieldsPanel.add(departmentTermsLabel);
        fieldsPanel.add(departmentTermsField);
        fieldsPanel.add(departmentSectionsLabel);
        fieldsPanel.add(departmentSectionsField);

        // BUTTONS
        button1 = new JButton();
        button2 = new JButton();
        setButtonsLabels();
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);

        departmentController = new DepartmentController();

        // ACTION LISTENER FOR button1
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand() == Constants.CREATE_BUTTON_LABEL)
                {
                    String TERMS = departmentTermsField.getText();
                    int deptTerms = Integer.parseInt(TERMS);
                    String SECTIONS = departmentSectionsField.getText();
                    int deptSections = Integer.parseInt(SECTIONS);
                    String message = "";

                    Department addDept = new Department(-1, departmentNameField.getText(), departmentShortNameField.getText(), deptTerms, deptSections);
                    try
                    {
                        addDept.setDepartmentID(departmentController.addRecord(addDept));
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
                    if (addDept.getDepartmentID() != -1)
                    {
                        message = "Department added successfully.";
                    }
                    else
                    {
                        message = "Department not added";
                    }
                    JOptionPane.showMessageDialog(fieldsPanel, message, "Message", JOptionPane.INFORMATION_MESSAGE);
                    if (addDept.getDepartmentID() != -1)
                    {
                        dispose();
                    }
                }
                else if (e.getActionCommand() == Constants.UPDATE_BUTTON_LABEL)
                {
                    String TERMS = departmentTermsField.getText();
                    int deptTerms = Integer.parseInt(TERMS);
                    String SECTIONS = departmentSectionsField.getText();
                    int deptSections = Integer.parseInt(SECTIONS);

                    department.setDepartmentName(departmentNameField.getText());
                    department.setDepartmentShortName(departmentShortNameField.getText());
                    department.setDepartmentTerms(deptTerms);
                    department.setDepartmentSections(deptSections);

                    try
                    {
                    	departmentController.updateRecord(department);
                    }
                    catch (SQLException throwables)
                    {
                        throwables.printStackTrace();
                    }
                    catch (ClassNotFoundException classNotFoundException)
                    {
                        classNotFoundException.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(fieldsPanel, "Department Updated Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                }
                else if (e.getActionCommand() == Constants.EDIT_BUTTON_LABEL)
                {
                    changeView(Constants.UPDATE_VIEW);
                }

            }

        });

        // ACTION LISTENER FOR button2
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
                    departmentNameField.setText(department.getDepartmentName());
                    departmentShortNameField.setText(department.getDepartmentShortName());
                    departmentTermsField.setText(String.valueOf(department.getDepartmentTerms()));
                    departmentSectionsField.setText(String.valueOf(department.getDepartmentSections()));
                }
                else if (e.getActionCommand() == Constants.CANCEL_BUTTON_LABEL)
                {
                    dispose();
                }
            }

        });

        // ADDING PANELS INTO THE FRAME
        addDepartmentPanel.add(fieldsPanel, BorderLayout.CENTER);
        addDepartmentPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(addDepartmentPanel,BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);

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

    private void changeFieldsEditableMode(boolean mode)
    {
        departmentNameField.setEditable(mode);
        departmentShortNameField.setEditable(mode);
        departmentTermsField.setEditable(mode);
        departmentSectionsField.setEditable(mode);
    }

    private void setDepartmentFieldValues()
    {
        departmentIdField          = new JTextField(String.valueOf(department.getDepartmentID()));
        departmentNameField        = new JTextField(department.getDepartmentName());
        departmentShortNameField   = new JTextField(department.getDepartmentShortName());
        departmentTermsField       = new JTextField(String.valueOf(department.getDepartmentTerms()));
        departmentSectionsField    = new JTextField(String.valueOf(department.getDepartmentSections()));
    }


    private void clearFields()
    {
        departmentNameField.setText("")              ;
        departmentShortNameField.setText("")         ;
        departmentTermsField.setText("")             ;
        departmentSectionsField.setText("")          ;
    }
}

