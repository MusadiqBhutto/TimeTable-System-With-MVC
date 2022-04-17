package com.mbhutto.view;

import com.mbhutto.controller.DepartmentController;
import com.mbhutto.controller.TimeTableController;
import com.mbhutto.entity.*;
import com.mbhutto.util.Constants;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;

public class TimeTableFieldsView extends JDialog
{
    private JPanel centerPanel                                             ;
    private JPanel mainPanel                                               ;
    private JPanel deptPanel1                                              ;
    private JPanel deptPanel2                                              ;
    private JPanel timeTablePanel                                          ;
    private JPanel buttonsPanel                                            ;
    private JLabel deptLabel                                               ;
    private JLabel termLabel                                               ;
    private JLabel sectionLabel                                            ;
    private JLabel subjectLabel                                            ;
    private JLabel teacherOnelabel                                         ;
    private JLabel teacherTwoLabel                                         ;
    private JLabel methodLabel                                             ;
    private JLabel selectDayOneLabel                                       ;
    private JLabel selectDayTwoLabel                                       ;
    private JLabel selectDayThreeLabel                                     ;
    private JLabel selectDayFourLabel                                      ;
    private JComboBox deptCombo                                            ;
    private JComboBox termCombo                                            ;
    private JComboBox sectionCombo                                         ;
    private JComboBox subjectCombo                                         ;
    private JComboBox teacherOneDeptCombo                                  ;
    private JComboBox teacherTwoDeptCombo                                  ;
    private JComboBox teacherOneCombo                                      ;
    private JComboBox teacherTwoCombo                                      ;
    private JComboBox selectionCombo                                       ;
    private JComboBox daysCombo1                                           ;
    private JComboBox daysCombo2                                           ;
    private JComboBox daysCombo3                                           ;
    private JComboBox daysCombo4                                           ;
    private JComboBox classesCombo1                                        ;
    private JComboBox classesCombo2                                        ;
    private JComboBox classesCombo3                                        ;
    private JComboBox classesCombo4                                        ;
    JButton createButton                                                   ;
    JButton clearButton                                                    ;

    private long departmentId                                              ;
    private long termId                                                    ;
    private long sectionId                                                 ;
    private long  departmentTermSectionId                                  ;

    private static final String[] termsArray = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"};
    private static final String[] sections = {"I", "II", "III", "IV", "V"};
    private static Result teachersResult                                      ;
    private static Result teachersDepartmentResult                            ;
    private static Result timeTableResult                                     ;
    private static Result subjectDepartmentResult                             ;
    private static Result timeTableDepartmentResult                           ;
    private static Result timeTableDepartmentTermResult                       ;
    private static Result timeTableDepartmentTermSectionResult                ;
    private DepartmentController  departmentController                        ;
    private TimeTableController   timeTableController                         ;

    public TimeTableFieldsView(JFrame frame, String title, boolean modal) throws SQLException, ClassNotFoundException
    {
        super(frame, title, modal);
        setLayout(new BorderLayout());
        departmentController = new DepartmentController();
        timeTableController  = new TimeTableController();

        // centerPanel
        centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TIME TABLE", TitledBorder.CENTER, TitledBorder.TOP));
        centerPanel.setLayout( new GridLayout(2,1,20,5));
        JScrollPane scrollPane = new JScrollPane(centerPanel);

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout( new GridLayout(7,2,20,5));

        // timeTablePanel
        timeTablePanel = new JPanel();
        timeTablePanel.setLayout( new GridLayout(5,3,20,5));
        timeTablePanel.setBorder( new CompoundBorder(new EmptyBorder(100,0,100,0), new EtchedBorder()));
        timeTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SET TIME TABLE MANUALLY",TitledBorder.CENTER, TitledBorder.TOP));

        // deptPanel1
        deptPanel1 = new JPanel();
        deptPanel1.setLayout( new GridLayout(1,2,20,5));

        // deptPanel2
        deptPanel2 = new JPanel();
        deptPanel2.setLayout( new GridLayout(1,2,20,5));

        // BUTTONS
        createButton    = new JButton("Create");
        clearButton     = new JButton("Clear");

        createButton.addActionListener(e -> {
            System.out.println(isValidSelectedPracticalClass());
            try
            {
                if(isValidSelectedPracticalClass())
                {
                    int isTimeTableSetForDeptTermSec = subjectCombo.getItemCount() == 1 ? 1 :  0 ;
                    createTimeTable(daysCombo1.getSelectedIndex() + 1, Integer.parseInt(classesCombo1.getSelectedItem().toString()), Constants.CLASS_TYPE_THEORY, 0, 0);
                    createTimeTable(daysCombo2.getSelectedIndex() + 1, Integer.parseInt(classesCombo2.getSelectedItem().toString()), Constants.CLASS_TYPE_THEORY, 0, 0);
                    createTimeTable(daysCombo3.getSelectedIndex() + 1, Integer.parseInt(classesCombo3.getSelectedItem().toString()), Constants.CLASS_TYPE_THEORY, 1, isTimeTableSetForDeptTermSec);
                    if(((Subject) subjectCombo.getSelectedItem()).getSubjectPracticalClassesInWeek() > 0)
                    {
                        createTimeTable(daysCombo4.getSelectedIndex() + 1, Integer.parseInt(classesCombo4.getSelectedItem().toString()), Constants.CLASS_TYPE_PRACTICAL, 0, 0);
                        createTimeTable(daysCombo4.getSelectedIndex() + 1, Integer.parseInt(classesCombo4.getSelectedItem().toString()) + 1, Constants.CLASS_TYPE_PRACTICAL, 0, 0);
                        createTimeTable(daysCombo4.getSelectedIndex() + 1, Integer.parseInt(classesCombo4.getSelectedItem().toString()) + 2, Constants.CLASS_TYPE_PRACTICAL, 1, isTimeTableSetForDeptTermSec);
                    }
                    JOptionPane.showMessageDialog(null, "Time table has been created successfully.");
                    disposeDialog();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select correct class for practical.");
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        // buttonsPanel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(1,2,100,100));
        buttonsPanel.add(createButton);
        buttonsPanel.add(clearButton);
        
        // LABELS
        deptLabel               = new JLabel("Department : ")                                              ;
        termLabel               = new JLabel("Term : ")                                                    ;                                         ;
        sectionLabel            = new JLabel("Section : ")                                                 ;                                        ;
        subjectLabel            = new JLabel("Subject : ")                                                 ;                                        ;
        teacherOnelabel         = new JLabel("Teacher One : ")                                             ;                                      ;
        teacherTwoLabel         = new JLabel("Teacher Two : ")                                             ;                                      ;
        methodLabel             = new JLabel("Select Method : ")                                           ;                                      ;
        selectDayOneLabel       = new JLabel("Select Day & Class No. (for 1st theory class) : ")                                      ;                                      ;
        selectDayTwoLabel       = new JLabel("Select Day & Class No. (for 2nd theory class) : ")                                      ;                                      ;
        selectDayThreeLabel     = new JLabel("Select Day & Class No. (for 3rd theory class) : ")                                      ;                                      ;
        selectDayFourLabel      = new JLabel("Select Day & Class No. (for practical  class) : ")                                      ;                                      ;

        // COMBOS
        deptCombo                  = new JComboBox();
        termCombo                  = new JComboBox();
        sectionCombo               = new JComboBox();
        subjectCombo               = new JComboBox();
        teacherOneCombo            = new JComboBox();
        teacherTwoCombo            = new JComboBox();
        teacherOneDeptCombo        = new JComboBox();
        teacherTwoDeptCombo        = new JComboBox();
        selectionCombo             = new JComboBox();
        daysCombo1                 = new JComboBox();
        daysCombo2                 = new JComboBox();
        daysCombo3                 = new JComboBox();
        daysCombo4                 = new JComboBox();
        classesCombo1              = new JComboBox();
        classesCombo2              = new JComboBox();
        classesCombo3              = new JComboBox();
        classesCombo4              = new JComboBox();
        selectionCombo.addItem("Automatic");
        selectionCombo.addItem("Manual");

        // ADDING IN PANEL
        mainPanel.add(deptLabel);
        mainPanel.add(deptCombo);
        mainPanel.add(termLabel);
        mainPanel.add(termCombo);
        mainPanel.add(sectionLabel);
        mainPanel.add(sectionCombo);
        mainPanel.add(subjectLabel);
        mainPanel.add(subjectCombo);
        mainPanel.add(teacherOnelabel);
        mainPanel.add(deptPanel1);
        deptPanel1.add(teacherOneDeptCombo);
        deptPanel1.add(teacherOneCombo);
        mainPanel.add(teacherTwoLabel);
        mainPanel.add(deptPanel2);
        deptPanel2.add(teacherTwoDeptCombo);
        deptPanel2.add(teacherTwoCombo);
        mainPanel.add(methodLabel);
        mainPanel.add(selectionCombo);
        timeTablePanel.add(selectDayOneLabel);
        timeTablePanel.add(daysCombo1);
        timeTablePanel.add(classesCombo1);
        timeTablePanel.add(selectDayTwoLabel);
        timeTablePanel.add(daysCombo2);
        timeTablePanel.add(classesCombo2);
        timeTablePanel.add(selectDayThreeLabel);
        timeTablePanel.add(daysCombo3);
        timeTablePanel.add(classesCombo3);
        timeTablePanel.add(selectDayFourLabel);
        timeTablePanel.add(daysCombo4);
        timeTablePanel.add(classesCombo4);

        // Getting distinct departments for the

        getDepartmentsForTheTimeTable();


        // Getting Departments for Teachers Combos
        Department dept = new Department();
        teachersDepartmentResult = departmentController.getRecords();
        for (int i = 0; i < teachersDepartmentResult.getLength(); i++)
        {
            dept = new Department(
                    Long.parseLong(teachersDepartmentResult.getRecords()[i][0]),
                    teachersDepartmentResult.getRecords()[i][1],
                    teachersDepartmentResult.getRecords()[i][2],
                    Integer.parseInt(teachersDepartmentResult.getRecords()[i][3]),
                    Integer.parseInt(teachersDepartmentResult.getRecords()[i][4]));
                    teacherOneDeptCombo.addItem(dept);
                    teacherTwoDeptCombo.addItem(dept);
        }

        getSubjects() ;
        getTeachers(0) ;
        freeClasses();

        deptCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null)
            {
                try {
                    Department department = (Department) ((JComboBox) e.getSource()).getSelectedItem();
                    departmentId = department.getDepartmentID() ;
                    getDepartmentsTermsForTimeTable(departmentId);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (NullPointerException nullPointerException) {
                    nullPointerException.printStackTrace();
                }
            }
        });

        termCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null)
            {
                try {

                    termId = Long.parseLong(((JComboBox) e.getSource()).getSelectedItem().toString());
                    getDepartmentsTermsSectionsForTimeTable(departmentId, termId);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (NullPointerException nullPointerException)  {
                    nullPointerException.printStackTrace();
                }
            }
        });

        sectionCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null)
            {

                sectionId =  Long.parseLong(((JComboBox) e.getSource()).getSelectedItem().toString());
                try {
                    getSubjects() ;
                    freeClasses();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (NullPointerException nullPointerException)  {
                    nullPointerException.printStackTrace();
                }
            }

        });

        subjectCombo.addActionListener(e -> {
            if(e.getActionCommand() == "comboBoxChanged")
            {
                Subject subject = (Subject) ((JComboBox) e.getSource()).getSelectedItem();
                if(subject != null && selectionCombo.getSelectedIndex() == 1)
                {
                    setClassVisibility(subject.getSubjectPracticalMarks());
                }
            }
        });

        teacherOneDeptCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged")
            {
                try {
                    getTeachers(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        teacherTwoDeptCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged")
            {
                try {
                    getTeachers(2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        selectionCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged")
            {
                if (selectionCombo.getSelectedIndex() == 1) {
                    Subject subject = (Subject) subjectCombo.getSelectedItem();
                    if(subject != null)
                    {
                        setClassVisibility(subject.getSubjectPracticalMarks());
                    }
                } else {
                    timeTablePanel.setVisible(false);
                }
            }
        });


        daysCombo1.addActionListener(e ->
        {
            if (e.getActionCommand() == "comboBoxChanged")
            {
                addItemsIntoClassesCombo(classesCombo1, ((JComboBox) e.getSource()).getSelectedIndex() + 1);
            }
        });


        daysCombo2.addActionListener(e ->
        {
            if (e.getActionCommand() == "comboBoxChanged")
            {
                addItemsIntoClassesCombo(classesCombo2, ((JComboBox) e.getSource()).getSelectedIndex() + 1);
            }
        });


        daysCombo3.addActionListener(e ->
        {
            if (e.getActionCommand() == "comboBoxChanged")
            {
                addItemsIntoClassesCombo(classesCombo3, ((JComboBox) e.getSource()).getSelectedIndex() + 1);
            }
        });

        daysCombo4.addActionListener(e ->
        {
            if (e.getActionCommand() == "comboBoxChanged")
            {
                addItemsIntoClassesCombo(classesCombo4, ((JComboBox) e.getSource()).getSelectedIndex() + 1);
            }
        });

        // ADDING PANELS IN FRAME
        centerPanel.add(mainPanel);
        centerPanel.add(timeTablePanel);
        timeTablePanel.setVisible(false);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    private void getDepartmentsForTheTimeTable() throws SQLException, ClassNotFoundException
    {
        TimeTable timeTable = new TimeTable();
        timeTableDepartmentResult = timeTableController.getDistinctDepartmentsForTimeTable();
        deptCombo.removeAllItems();
        for (int i = 0; i < timeTableDepartmentResult.getLength(); i++)
        {
            Department dept = new Department(
                    Long.parseLong(timeTableDepartmentResult.getRecords()[i][0]),
                    timeTableDepartmentResult.getRecords()[i][1],
                    timeTableDepartmentResult.getRecords()[i][2],
                    Integer.parseInt(timeTableDepartmentResult.getRecords()[i][3]),
                    Integer.parseInt(timeTableDepartmentResult.getRecords()[i][4]));
            deptCombo.addItem(dept);
            if(i == 0)
            {
                getDepartmentsTermsForTimeTable(Long.parseLong(timeTableDepartmentResult.getRecords()[i][0]));
                departmentId = dept.getDepartmentID();
            }
        }
    }

    private void getDepartmentsTermsForTimeTable(long selectedDepartmentId) throws SQLException, ClassNotFoundException {
    	TimeTableController timeTableController = new TimeTableController();
        timeTableDepartmentTermResult = timeTableController.getDepartmentTermsForTimeTable(selectedDepartmentId);
        termCombo.removeAllItems();

        for (int i = 0; i < timeTableDepartmentTermResult.getLength(); i++)
        {
            termCombo.addItem(timeTableDepartmentTermResult.getRecords()[i][0]);
            if(i == 0)
            {
                termId = Long.parseLong(timeTableDepartmentTermResult.getRecords()[i][0]) ;
                getDepartmentsTermsSectionsForTimeTable(Long.parseLong(timeTableDepartmentResult.getRecords()[i][0]),
                        Long.parseLong(timeTableDepartmentTermResult.getRecords()[i][0]));
            }
        }
    }

    private void getDepartmentsTermsSectionsForTimeTable(long selectedDepartmentId, long selectedTermId) throws SQLException, ClassNotFoundException {
    	TimeTableController timeTableController = new TimeTableController();
        timeTableDepartmentTermSectionResult = timeTableController.getDepartmentTermsSectionsForTimeTable(selectedDepartmentId, selectedTermId);
        sectionCombo.removeAllItems();
        for (int i = 0; i < timeTableDepartmentTermSectionResult.getLength(); i++)
        {
            sectionCombo.addItem(timeTableDepartmentTermSectionResult.getRecords()[i][0]);
            if(i==0)
            {
                sectionId = Long.parseLong(timeTableDepartmentTermSectionResult.getRecords()[i][0]) ;
            }
        }

        getSubjects() ;
        freeClasses();
    }

    private boolean isValidSelectedPracticalClass() {
        boolean isValid = false  ;
        if(((Subject) subjectCombo.getSelectedItem()).getSubjectPracticalClassesInWeek() > 0)
        {
            int currentSelectedClass = Integer.parseInt(classesCombo4.getSelectedItem().toString());
            int currentSelectedIndex = classesCombo4.getSelectedIndex();
            int nextSelectedClass, nextNextSelectedClass ;

            if(classesCombo4.getItemCount() >=  (currentSelectedIndex + 3))
            {
                nextSelectedClass     = Integer.parseInt(classesCombo4.getItemAt(currentSelectedIndex + 1).toString());
                nextNextSelectedClass = Integer.parseInt(classesCombo4.getItemAt(currentSelectedIndex + 2).toString());
                if((currentSelectedClass + 1) == nextSelectedClass && (currentSelectedClass + 2) == nextNextSelectedClass)
                {
                    isValid = true ;
                }
            }
        }
        else
        {
            isValid = true ;
        }

        return isValid ;
    }

    private void addItemsIntoClassesCombo(JComboBox jComboBox, int dayIndex)
    {
        jComboBox.removeAllItems();
        for (int i = 0; i < timeTableResult.getLength(); i++)
        {
            if (dayIndex == Integer.parseInt(timeTableResult.getRecords()[i][3])) {
                jComboBox.addItem(timeTableResult.getRecords()[i][4]);
            }
        }
    }

    private void createTimeTable(int day, int classNo, String type, int isTimeTableSetForSubject, int isTimeTableSetForDeptTermSec) throws SQLException, ClassNotFoundException
    {   
    	TimeTableController timeTableController = new TimeTableController();
        TimeTable timeTable = new TimeTable(departmentId);
        timeTable.setTimeTableDay(day);
        timeTable.setTimeTableClassNo(classNo);
        timeTable.setDepartmentTermSectionId(departmentTermSectionId);
        timeTable.setIsTimeTableSetForSubject((byte) isTimeTableSetForSubject);
        timeTable.setIsTimeTableSetForDeptTermSection((byte) isTimeTableSetForDeptTermSec);

        timeTable.setSubject((Subject) subjectCombo.getSelectedItem());
        timeTable.setAllocated((byte) 1);
        timeTable.setTimeTableClassType(type);
        timeTable.setTeacherOne((Teacher) teacherOneCombo.getSelectedItem());
        if(teacherTwoCombo.getSelectedIndex() >= 0)
        {
            timeTable.setTeacherTwo((Teacher) teacherTwoCombo.getSelectedItem());
        }

        for (int i = 0; i < timeTableResult.getLength(); i++)
        {
            if (day == Integer.parseInt(timeTableResult.getRecords()[i][3]) && classNo ==  Integer.parseInt(timeTableResult.getRecords()[i][4])) {
                System.out.println(timeTableResult.getRecords()[i][0]);
                timeTable.setTimeTableId(Long.parseLong(timeTableResult.getRecords()[i][0]));
                break ;
            }
        }

        for (int i = 0; i < subjectDepartmentResult.getLength(); i++)
        {
            if(timeTable.getSubject().getSubjectId() == Long.parseLong(subjectDepartmentResult.getRecords()[i][1]))
            {
                timeTable.setSubjectDepartmentTermSectionId(Long.parseLong(subjectDepartmentResult.getRecords()[i][0]));
            }
        }

        timeTable.createTimeTable();
    }

    private void disposeDialog()
    {
        setVisible(false);
        dispose();
    }

    private void freeClasses() throws SQLException, ClassNotFoundException
    {   
    	TimeTableController timeTableController = new TimeTableController();
        TimeTable timeTable = new TimeTable(departmentTermSectionId);
        timeTableResult = timeTableController.getFreeClasses(timeTable);
        String[][] freeDaysAndClassess = new String[timeTableResult.getLength()][3];

        int uniqueDays = 0;
        int currentDay = 0;
        for(int i=0; i< timeTableResult.getLength(); i++)
        {
            if(currentDay != Integer.parseInt(timeTableResult.getRecords()[i][3]))
            {
                uniqueDays++;
                currentDay = Integer.parseInt(timeTableResult.getRecords()[i][3]);
            }
            freeDaysAndClassess[i][0] = timeTableResult.getRecords()[i][0]; //id
            freeDaysAndClassess[i][1] = timeTableResult.getRecords()[i][3]; //day
            freeDaysAndClassess[i][2] = timeTableResult.getRecords()[i][4]; //class
        }

        String[] freeDays = new String[uniqueDays];
        currentDay = 0;
        uniqueDays = -1;
        for(int i=0; i< timeTableResult.getLength(); i++)
        {
            if(currentDay != Integer.parseInt(timeTableResult.getRecords()[i][3]))
            {
                currentDay = Integer.parseInt(timeTableResult.getRecords()[i][3]);
                uniqueDays++;
                if(Integer.parseInt(timeTableResult.getRecords()[i][3]) == 1)
                {
                    freeDays[uniqueDays] = "Monday" ;
                }
                else if(Integer.parseInt(timeTableResult.getRecords()[i][3]) == 2)
                {
                    freeDays[uniqueDays] = "Tuesday" ;
                }
                else if(Integer.parseInt(timeTableResult.getRecords()[i][3]) == 3)
                {
                    freeDays[uniqueDays] = "Wednesday" ;
                }
                else if(Integer.parseInt(timeTableResult.getRecords()[i][3]) == 4)
                {
                    freeDays[uniqueDays] = "Thursday" ;
                }
                else if(Integer.parseInt(timeTableResult.getRecords()[i][3]) == 5)
                {
                    freeDays[uniqueDays] = "Friday" ;
                }

            }
        }
        daysCombo1.removeAllItems();
        daysCombo2.removeAllItems();
        daysCombo3.removeAllItems();
        daysCombo4.removeAllItems();
        for(int i=0; i<freeDays.length; i++)
        {
            daysCombo1.addItem(freeDays[i]);
            daysCombo2.addItem(freeDays[i]);
            daysCombo3.addItem(freeDays[i]);
            daysCombo4.addItem(freeDays[i]);
        }

        if(freeDays.length >= 1)
        {
            daysCombo2.setSelectedIndex(1);
        }

        if(freeDays.length >= 2)
        {
            daysCombo3.setSelectedIndex(2);
        }

        if(freeDays.length >= 3)
        {
            daysCombo4.setSelectedIndex(3);
        }

        addItemsIntoClassesCombo(classesCombo1, daysCombo1.getSelectedIndex() + 1);
        addItemsIntoClassesCombo(classesCombo2, daysCombo2.getSelectedIndex() + 1);
        addItemsIntoClassesCombo(classesCombo3, daysCombo3.getSelectedIndex() + 1);
        addItemsIntoClassesCombo(classesCombo4, daysCombo4.getSelectedIndex() + 1);


        //System.out.println("Unique days: " + uniqueDays);

    }

    private void setClassVisibility(int subjectPracticalMarks)
    {
        if(subjectPracticalMarks == 0)
        {
            selectDayFourLabel.setVisible(false);
            classesCombo4.setVisible(false);
            daysCombo4.setVisible(false);
        }
        else if(subjectPracticalMarks != 0)
        {
            selectDayFourLabel.setVisible(true);
            classesCombo4.setVisible(true);
            daysCombo4.setVisible(true);
        }
        timeTablePanel.setVisible(true);
    }

    private void getSubjects() throws SQLException, ClassNotFoundException
    {
        SubjectDepartmentTermSection subjectDepartmentTermSection = new SubjectDepartmentTermSection(departmentId, termId, sectionId);
        subjectDepartmentResult = subjectDepartmentTermSection.getSubDeptTermSec();
        subjectCombo.removeAllItems();
        for(int i=0; i<subjectDepartmentResult.getLength(); i++)
        {
            departmentTermSectionId = Long.parseLong(subjectDepartmentResult.getRecords()[0][2]);
            Subject sub = new Subject(
                    Long.parseLong(subjectDepartmentResult.getRecords()[i][4]),
                    subjectDepartmentResult.getRecords()[i][5],
                    subjectDepartmentResult.getRecords()[i][6],
                    subjectDepartmentResult.getRecords()[i][7],
                    Integer.parseInt(subjectDepartmentResult.getRecords()[i][8]),
                    Integer.parseInt(subjectDepartmentResult.getRecords()[i][9]),
                    Integer.parseInt(subjectDepartmentResult.getRecords()[i][10]),
                    Integer.parseInt(subjectDepartmentResult.getRecords()[i][11]));
            subjectCombo.addItem(sub);

        }

    }

    private void getTeachers(int type) throws SQLException, ClassNotFoundException
    {
        Department department ;
        if(type == 0 || type == 1)
        {
            department = (Department) teacherOneDeptCombo.getSelectedItem();
        }
        else
        {
            department = (Department) teacherTwoDeptCombo.getSelectedItem();
        }

        SubjectDepartmentTermSection subjectDepartmentTermSectionFinal = new SubjectDepartmentTermSection(department.getDepartmentID());
        teachersResult = subjectDepartmentTermSectionFinal.getDeptTeachers();


        if(type == 0)
        {
            removeTeacherComboItems(teacherOneCombo);
            removeTeacherComboItems(teacherTwoCombo);
        }
        else if(type == 1)
        {
            removeTeacherComboItems(teacherOneCombo);
        }
        else if(type == 2)
        {
            removeTeacherComboItems(teacherTwoCombo);
        }
    }

    private void removeTeacherComboItems(JComboBox teacherCombo) {
        teacherCombo.removeAllItems();
        for(int i=0; i<teachersResult.getLength(); i++)
        {

            Teacher teacher = new Teacher(
                    Long.parseLong(teachersResult.getRecords()[i][0]),
                    teachersResult.getRecords()[i][2],
                    teachersResult.getRecords()[i][3],
                    teachersResult.getRecords()[i][4],
                    teachersResult.getRecords()[i][5],
                    teachersResult.getRecords()[i][6],
                    teachersResult.getRecords()[i][7],
                    teachersResult.getRecords()[i][8],
                    teachersResult.getRecords()[i][9],
                    Byte.parseByte(teachersResult.getRecords()[i][10]));

            teacherCombo.addItem(teacher);
        }
    }
}




/*
        Subject sub = new Subject();
        result = sub.getRecords();
        for (int i = 0; i < result.length; i++)
        {
            sub = new Subject(
                    Long.parseLong(result.records[i][0]),
                    result.records[i][1],
                    result.records[i][2],
                    result.records[i][3],
                    Integer.parseInt(result.records[i][4]),
                    Integer.parseInt(result.records[i][5]),
                    Integer.parseInt(result.records[i][6]),
                    Integer.parseInt(result.records[i][7]));
                    subjectCombo.addItem(sub);
        }
*/