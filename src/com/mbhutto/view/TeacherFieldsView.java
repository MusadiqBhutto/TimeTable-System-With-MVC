package com.mbhutto.view;

import com.mbhutto.controller.DepartmentController;
import com.mbhutto.controller.TeacherController;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Teacher;
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

public class TeacherFieldsView extends JDialog
{
    private int departmentIndex;
    private  JPanel teacherPanel                                                   ;
    private  JPanel fieldsPanel                                                    ;
    private  JPanel buttonsPanel                                                   ;
    private  JLabel teacherIdLabel                                                 ;
    private  JLabel teacherNameLabel                                               ;
    private  JLabel teacherDepartmentLabel                                         ;
    private  JLabel teacherNICLabel                                                ;
    private  JLabel teacherHomePhoneLabel                                          ;
    private  JLabel teacherOfficePhoneLabel                                        ;
    private  JLabel teacherMobilePhoneLabel                                        ;
    private  JLabel teacherEmailLabel                                              ;
    private  JLabel teacherQualificationLabel                                      ;
    private  JLabel teacherDesignationLabel                                        ;
    private  JLabel teacherGradeLabel                                              ;
    private  JTextField teacherIdField                                             ;
    private  JTextField teacherNameField                                           ;
    private  JTextField teacherNICField                                            ;
    private  JTextField teacherHomePhoneField                                      ;
    private  JTextField teacherOfficePhoneField                                    ;
    private  JTextField teacherMobilePhoneField                                    ;
    private  JTextField teacherEmailField                                          ;
    private  JTextField teacherQualificationField                                  ;
    private  JTextField teacherDesignationField                                    ;
    private  JTextField teacherGradeField                                          ;
    private  JComboBox<Department> teacherDepartmentCombo                          ;
    Result result                                                                  ;
    private Teacher teacher                                                        ;
    private  JButton button1                                                       ;
    private  JButton button2                                                       ;
    private String view = ""                                                       ;

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

    public TeacherFieldsView(JFrame frame, String title, boolean modal, String view, Teacher teacher) throws SQLException, ClassNotFoundException
    {
        super(frame, title, modal);
        this.teacher = teacher ;
        setView(view);
        departmentIndex = -1 ;
        
        TeacherController     teacherController = new  TeacherController();
        DepartmentController  departmentController = new  DepartmentController();
        

        setLayout(new BorderLayout());

        // teacherPanel
        teacherPanel = new JPanel();
        teacherPanel.setLayout(new BorderLayout());
        teacherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Teacher", TitledBorder.CENTER, TitledBorder.TOP));

        // fieldsPanel
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout( new GridLayout(11,2,20,5));
        fieldsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,150,10,150), new EtchedBorder()));

        // buttonsPanel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(1,2,20,5));
        buttonsPanel.setBorder( new CompoundBorder(new EmptyBorder(10,150,10,150), new EtchedBorder()));

        // LABELS & TEXTFIELDS
        teacherIdLabel                    = new JLabel("Teacher ID : ");
        teacherDepartmentLabel            = new JLabel("Teacher Department : ");
        teacherNameLabel                  = new JLabel("Teacher Name : ");
        teacherNICLabel                   = new JLabel("Teacher NIC : ");
        teacherHomePhoneLabel             = new JLabel("Teacher Home Phone : ");
        teacherOfficePhoneLabel           = new JLabel("Teacher Office Phone : ");
        teacherMobilePhoneLabel           = new JLabel("Teacher Mobile Phone : ");
        teacherEmailLabel                 = new JLabel("Teacher Email  : ");
        teacherQualificationLabel         = new JLabel("Teacher Qualification  : ");
        teacherDesignationLabel           = new JLabel("Teacher Designation  : ");
        teacherGradeLabel                 = new JLabel("Teacher Grade  : ");


        if(view == Constants.ADD_VIEW)
        {
               teacherIdField                  = new JTextField("To be generated...");
               teacherDepartmentCombo          = new JComboBox<Department>();
               teacherNameField                = new JTextField();
               teacherNICField                 = new JTextField();
               teacherHomePhoneField           = new JTextField();
               teacherOfficePhoneField         = new JTextField();
               teacherMobilePhoneField         = new JTextField();
               teacherEmailField               = new JTextField();
               teacherQualificationField       = new JTextField();
               teacherDesignationField         = new JTextField();
               teacherGradeField               = new JTextField();
        }

        else if (view == Constants.UPDATE_VIEW)
        {
              setTeacherFieldValues();
        }

        else if (view == Constants.DISPLAY_VIEW)
        {
            setTeacherFieldValues();
            changeFieldsEditableMode(false);
        }


        Department dept = new Department();
        result = departmentController.getRecords();
        Department department;
        for (int i = 0; i < result.getLength(); i++)
        {
            department = new Department(
                    Long.parseLong(result.getRecords()[i][0]),
                    result.getRecords()[i][1],
                    result.getRecords()[i][2],
                    Integer.parseInt(result.getRecords()[i][3]),
                    Integer.parseInt(result.getRecords()[i][4])
            );
            teacherDepartmentCombo.addItem(department);

            if (view == Constants.UPDATE_VIEW || view == Constants.DISPLAY_VIEW)
            {
                if (teacher.getDepartment().getDepartmentID() == Long.parseLong(result.getRecords()[i][0]))
                {
                    this.departmentIndex = i ;
                    teacherDepartmentCombo.setSelectedIndex(departmentIndex);
                }
            }
        }

        teacherIdField.setEditable(false);

        fieldsPanel.add(teacherIdLabel);
        fieldsPanel.add(teacherIdField);
        fieldsPanel.add(teacherNameLabel);
        fieldsPanel.add(teacherNameField);
        fieldsPanel.add(teacherDepartmentLabel);
        fieldsPanel.add(teacherDepartmentCombo);
        fieldsPanel.add(teacherNICLabel);
        fieldsPanel.add(teacherNICField);
        fieldsPanel.add(teacherHomePhoneLabel);
        fieldsPanel.add(teacherHomePhoneField);
        fieldsPanel.add(teacherOfficePhoneLabel);
        fieldsPanel.add(teacherOfficePhoneField);
        fieldsPanel.add(teacherMobilePhoneLabel);
        fieldsPanel.add(teacherMobilePhoneField);
        fieldsPanel.add(teacherEmailLabel);
        fieldsPanel.add(teacherEmailField);
        fieldsPanel.add(teacherQualificationLabel);
        fieldsPanel.add(teacherQualificationField);
        fieldsPanel.add(teacherDesignationLabel);
        fieldsPanel.add(teacherDesignationField);
        fieldsPanel.add(teacherGradeLabel);
        fieldsPanel.add(teacherGradeField);



        // BUTTONS
        // BUTTONS
        button1 = new JButton();
        button2 = new JButton();
        setButtonsLabels();
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);


        // ACTION LISTENER FOR button1
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand() == Constants.CREATE_BUTTON_LABEL)
                {
                    String GRADE = teacherGradeField.getText();
                    byte teacherGrade = Byte.parseByte(GRADE);
                    String message = "";
                    Teacher teacher = new Teacher(-1, (Department) teacherDepartmentCombo.getSelectedItem(), teacherNameField.getText(), teacherNICField.getText(), teacherHomePhoneField.getText(), teacherOfficePhoneField.getText(), teacherMobilePhoneField.getText(), teacherEmailField.getText(), teacherQualificationField.getText(), teacherDesignationField.getText(), teacherGrade);

                    try
                    {
                        teacher.setTeacherId(teacherController.addRecord(teacher));
                    }
                    catch (SQLException | ClassNotFoundException throwables)
                    {
                        throwables.printStackTrace();
                    }

                    if(teacher.getTeacherId() != -1)
                    {
                        message = "Teacher added successfully.";

                    }
                    else
                    {
                        message = "Teacher not  added.";
                    }

                    JOptionPane.showMessageDialog(fieldsPanel, message, "Message", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    if (teacher.getTeacherId() != -1)
                    {
                        disposeDialog();
                    }
                }
                else if (e.getActionCommand() == Constants.UPDATE_BUTTON_LABEL)
                {
                    teacher.setDepartment((Department) teacherDepartmentCombo.getSelectedItem());
                    teacher.setTeacherName(teacherNameField.getText());
                    teacher.setTeacherNicNumber(teacherNICField.getText());
                    teacher.setTeacherHomePhone(teacherHomePhoneField.getText());
                    teacher.setTeacherOfficePhone(teacherOfficePhoneField.getText());
                    teacher.setTeacherMobilePhone(teacherMobilePhoneField.getText());
                    teacher.setTeacherEmail(teacherEmailField.getText());
                    teacher.setTeacherQualifications(teacherQualificationField.getText());
                    teacher.setTeacherDesignation(teacherDesignationField.getText());
                    teacher.setTeacherGrade(Byte.parseByte(teacherGradeField.getText()));

                    try
                    {
                    	teacherController.updateRecord(teacher);
                        JOptionPane.showMessageDialog(fieldsPanel, "Teacher Updated Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                        disposeDialog();
                    }
                    catch (SQLException throwables)
                    {
                        throwables.printStackTrace();
                    }
                    catch (ClassNotFoundException classNotFoundException)
                    {
                        classNotFoundException.printStackTrace();
                    }
                }
                else if (e.getActionCommand() == Constants.EDIT_BUTTON_LABEL)
                {
                    changeView(Constants.UPDATE_VIEW);
                }
            }

        });


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
                    teacherDepartmentCombo.setSelectedIndex(departmentIndex);
                    teacherNameField.setText(teacher.getTeacherName());
                    teacherNICField.setText(teacher.getTeacherNicNumber());
                    teacherHomePhoneField.setText(teacher.getTeacherHomePhone());
                    teacherOfficePhoneField.setText(teacher.getTeacherOfficePhone());
                    teacherMobilePhoneField.setText(teacher.getTeacherMobilePhone());
                    teacherEmailField.setText(teacher.getTeacherEmail());
                    teacherQualificationField.setText(teacher.getTeacherQualifications());
                    teacherDesignationField.setText(teacher.getTeacherDesignation());
                    teacherGradeField.setText(String.valueOf(teacher.getTeacherGrade()));
                }

                else if (e.getActionCommand() == Constants.CANCEL_BUTTON_LABEL)
                {
                    disposeDialog();
                }
            }


        });

        // ADDING PANELS INTO THE FRAME
        teacherPanel.add(fieldsPanel, BorderLayout.CENTER);
        teacherPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(teacherPanel,BorderLayout.CENTER);

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
        teacherDepartmentCombo.setEnabled(mode);
        teacherNameField.setEditable(mode);
        teacherNICField.setEditable(mode);
        teacherHomePhoneField.setEditable(mode);
        teacherOfficePhoneField.setEditable(mode);
        teacherMobilePhoneField.setEditable(mode);
        teacherEmailField.setEditable(mode);
        teacherQualificationField .setEditable(mode);
        teacherDesignationField.setEditable(mode);
        teacherGradeField.setEditable(mode);

    }

    private void setTeacherFieldValues()
    {
        teacherIdField                  = new JTextField(String.valueOf(teacher.getTeacherId()));
        teacherDepartmentCombo          = new JComboBox<Department>();
        teacherNameField                = new JTextField(teacher.getTeacherName());
        teacherNICField                 = new JTextField(teacher.getTeacherNicNumber());
        teacherHomePhoneField           = new JTextField(teacher.getTeacherHomePhone());
        teacherOfficePhoneField         = new JTextField(teacher.getTeacherOfficePhone());
        teacherMobilePhoneField         = new JTextField(teacher.getTeacherMobilePhone());
        teacherEmailField               = new JTextField(teacher.getTeacherEmail());
        teacherQualificationField       = new JTextField(teacher.getTeacherQualifications());
        teacherDesignationField         = new JTextField(teacher.getTeacherDesignation());
        teacherGradeField               = new JTextField(String.valueOf(teacher.getTeacherGrade()));
    }

    private void disposeDialog()
    {
        setVisible(false);
        dispose();
    }

    private void clearFields()
    {
        teacherDepartmentCombo.setSelectedIndex(0);
        teacherNameField.setText("");
        teacherNICField.setText("");
        teacherHomePhoneField.setText("");
        teacherOfficePhoneField.setText("");
        teacherMobilePhoneField .setText("");
        teacherEmailField.setText("");
        teacherQualificationField.setText("");
        teacherDesignationField.setText("");
        teacherGradeField.setText("");
    }
}
