package com.mbhutto.view;

import com.mbhutto.util.Constants;
import com.mbhutto.controller.DepartmentController;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Result;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DepartmentDisplay extends JPanel
{
    JFrame parent   ;
    JPanel tablePanel;
    JPanel buttonPanel;
    JPanel deptTeachersTablePanel;
    JPanel deptSubjectsTablePanel;
    JPanel deptSubjectsTableSubPanel;
    JDialog deptSubTeacherDialog;
    JTable deptTable;
    JScrollPane deptTableScroll;
    String deptTableColumnNames[];
    JTable deptTeachersTable;
    JScrollPane deptTeachersTableScroll;
    String deptTeachersTableColumnNames[];
    JTable deptSubjectsTable;
    JScrollPane deptSubjectsTableScroll;
    String deptSubjectsTableColumnNames[];
    JComboBox deptSubjectsCombo;
    JButton refreshButton;
    JButton addButton;
    JButton viewButton;
    JButton deptTeachersButton;
    JButton deptSubjectsButton;
    JButton updateButton;
    JButton deleteButton;
    private DepartmentFieldsView departmentFieldsView ;
    private DepartmentController departmentController;
    private Result result;
    private Long deptIdForSubjects;

    public DepartmentDisplay(JFrame parent) throws SQLException, ClassNotFoundException
    {
        // layout
        setLayout(new BorderLayout());
        this.parent = parent ;

        // deptTable
        departmentController = new DepartmentController();
        result = departmentController.getRecords();
        deptTableColumnNames = new String[5];
        deptTable = new JTable();
        loadDepartments(result.getRecords());
        deptTableScroll = new JScrollPane(deptTable);
        deptTable.setFillsViewportHeight(true);
        deptTableScroll.setPreferredSize(new Dimension(1100, 500));

        // deptTeachersTable
        deptTeachersTable = new JTable();
        deptTeachersTableScroll = new JScrollPane(deptTeachersTable);
        deptTeachersTable.setFillsViewportHeight(true);
        deptTeachersTableScroll.setPreferredSize(new Dimension(550, 250));

        // deptSubjectsTable
        deptSubjectsTable = new JTable();
        deptSubjectsTableScroll = new JScrollPane(deptSubjectsTable);
        deptSubjectsTable.setFillsViewportHeight(true);
        deptSubjectsTableScroll.setPreferredSize(new Dimension(550, 250));

        // deptSubTeacherDialog
        deptSubTeacherDialog = new JDialog();

        // deptSubjectsCombo
        deptSubjectsCombo = new JComboBox();

        // BUTTONS
        refreshButton   = new JButton("Refresh Departments");
        addButton = new JButton("Create New Department");
        viewButton = new JButton("View Department");
        deptTeachersButton = new JButton("Department Teachers");
        deptSubjectsButton = new JButton("Department Subjects");
        updateButton = new JButton("Update Department");
        deleteButton = new JButton("Delete Department");

        // TABLE/CENTER PANEL
        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Department Table", TitledBorder.CENTER, TitledBorder.TOP));
        tablePanel.add(deptTableScroll);
        this.add(tablePanel, BorderLayout.CENTER);

        // deptTeachersTablePanel
        deptTeachersTablePanel = new JPanel();
        deptTeachersTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Department Teachers", TitledBorder.CENTER, TitledBorder.TOP));
        deptTeachersTablePanel.setLayout(new BorderLayout());

        // deptSubjectsTablePanel
        deptSubjectsTablePanel = new JPanel();
        deptSubjectsTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Department Subjects", TitledBorder.CENTER, TitledBorder.TOP));
        deptSubjectsTablePanel.setLayout(new BorderLayout());

        // deptSubjectsTableSubPanel
        deptSubjectsTableSubPanel = new JPanel();
        deptSubjectsTableSubPanel.setBorder(new EmptyBorder(10,0,10,0));
        deptSubjectsTableSubPanel.setLayout(new GridLayout(1,2,25,25));
        deptSubjectsTableSubPanel.add(new JLabel("Select Term : "));

        // BUTTONS/SOUTH PANEL
        buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deptTeachersButton);
        buttonPanel.add(deptSubjectsButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENER FOR refreshButton
        refreshButton.addActionListener(e ->
        {
            try {
                result = departmentController.getRecords();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            loadDepartments(result.getRecords());
        });

        // ACTION LISTENER FOR addButton
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                departmentFieldsView = new DepartmentFieldsView(parent, "Add Department", true, Constants.ADD_VIEW, null);
            }
        });

        // ACTION LISTENER FOR viewButton
        viewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                departmentFieldsView = new DepartmentFieldsView(parent, "View Department", true,Constants.DISPLAY_VIEW, department);
            }
        });

        // ACTION LISTENER FOR deptTeachersButton
        deptTeachersButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                try
                {
                    loadDepartmentTeachers(DepartmentController.getDeptTeachers(department).records);
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        // ACTION LISTENER FOR deptSubjectsButton
        deptSubjectsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                deptIdForSubjects = department.getDepartmentID();
                addItemsInDeptSubjectsCombo(department.getDepartmentTerms());

            }
        });

        // ACTION LISTENER FOR updateButton
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++) {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                departmentFieldsView = new DepartmentFieldsView(parent, "Update Department", true,Constants.UPDATE_VIEW, department);
            }
        });

        // ACTION LISTENER FOR deleteButton
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = deptTable.getSelectedRow();
                String selectedDepartment[] = new String[5];

                for (int i = 0; i < deptTableColumnNames.length; i++)
                {
                    selectedDepartment[i] = (String) ((deptTable.getModel().getValueAt(selectedRow, i)));
                }

                Department department = new Department(
                        Long.parseLong(selectedDepartment[0]),
                        selectedDepartment[1],
                        selectedDepartment[2],
                        Integer.parseInt(selectedDepartment[3]),
                        Integer.parseInt(selectedDepartment[4]));

                int decision = JOptionPane.showConfirmDialog(tablePanel, "Are you sure you want to delete this item?");
                if (decision == 0)
                {
                    try
                    {
                        int deletedRows = departmentController.deleteRecord(department);
                        System.out.println(deletedRows);
                        if (deletedRows > 0)
                        {
                            JOptionPane.showMessageDialog(tablePanel, "Department deleted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                            boolean recordFound = false;
                            String[][] tempRecords = new String[(result.getRecords().length - 1)][5];
                            for (int i = 0; i < result.getRecords().length; i++)
                            {
                                if (department.getDepartmentName() == result.getRecords()[i][1])
                                {
                                    recordFound = true;
                                    continue;
                                }

                                for (int j = 0; j < result.getRecords()[i].length; j++)
                                {
                                    if (!recordFound)
                                    {
                                        tempRecords[i][j] = result.getRecords()[i][j];
                                    } else
                                    {
                                        tempRecords[i - 1][j] = result.getRecords()[i][j];
                                    }
                                }
                            }
                            result.setRecords( new String[tempRecords.length][5]);
                            for (int i = 0; i < tempRecords.length; i++)
                            {
                                result.getRecords()[i][0] = tempRecords[i][0];
                                result.getRecords()[i][1] = tempRecords[i][1];
                                result.getRecords()[i][2] = tempRecords[i][2];
                                result.getRecords()[i][3] = tempRecords[i][3];
                                result.getRecords()[i][4] = tempRecords[i][4];
                                System.out.println(result.getRecords()[i][1]);
                            }

                            loadDepartments(result.getRecords());

                        }

                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }


            }

        });

        // ACTION LISTENER FOR deptSubjectsCombo
        deptSubjectsCombo.addActionListener(e ->
        {
            if(e.getActionCommand() == "comboBoxChanged" && ((JComboBox) e.getSource()).getSelectedItem() != null)
            {
                int selectedTerm = Integer.parseInt(deptSubjectsCombo.getSelectedItem().toString());

                try
                {
                    loadDepartmentTermSubjects(DepartmentController.getDeptTermSubjects(deptIdForSubjects, selectedTerm).records);
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

    }

    // deptTable
    private void loadDepartments(String[][] records)
    {
        DefaultTableModel model = (DefaultTableModel) deptTable.getModel();
        deptTableColumnNames = new String[]{"ID", "Department Name", "Short Name", "Terms", "Sections"};
        String[][] data = records;
        model.setDataVector(data, deptTableColumnNames);
    }

    // deptTeachersTable
    private void loadDepartmentTeachers(String[][] records)
    {
        DefaultTableModel model = (DefaultTableModel) deptTeachersTable.getModel();
        deptTeachersTableColumnNames = new String[]{"ID", "Name", "Qualification", "Designation"};
        String[][] data = records;
        model.setDataVector(data, deptTeachersTableColumnNames);
        deptTeachersTablePanel.add(deptTeachersTableScroll, BorderLayout.CENTER);
        showDialog(deptTeachersTablePanel, "Department Teachers");
    }

    // deptSubjectsTable
    private void loadDepartmentTermSubjects(String[][] records)
    {
       DefaultTableModel model = (DefaultTableModel) deptSubjectsTable.getModel();
       deptSubjectsTableColumnNames = new String[]{"ID", "Code", "Name", "Theory (C.H)", "Practical (C.H)"};
       String[][] data = records;
       model.setDataVector(data, deptSubjectsTableColumnNames);
       deptSubjectsTable.getColumnModel().getColumn(0).setPreferredWidth(10);
       deptSubjectsTable.getColumnModel().getColumn(1).setPreferredWidth(10);
       deptSubjectsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
       deptSubjectsTable.getColumnModel().getColumn(3).setPreferredWidth(10);
       deptSubjectsTable.getColumnModel().getColumn(4).setPreferredWidth(10);
       deptSubjectsTableSubPanel.add(deptSubjectsCombo);
       deptSubjectsTablePanel.add(deptSubjectsTableSubPanel, BorderLayout.NORTH);
       deptSubjectsTablePanel.add(deptSubjectsTableScroll, BorderLayout.CENTER);
       showDialog(deptSubjectsTablePanel, "Department Subjects");
    }

    // showDialog for Departments's teachers, Departments's Subjects
    private void showDialog(JPanel panel, String title)
    {
        deptSubTeacherDialog.getContentPane().removeAll();
        deptSubTeacherDialog.revalidate();
        deptSubTeacherDialog.add(panel);
        deptSubTeacherDialog.setTitle(title);
        deptSubTeacherDialog.pack();
        deptSubTeacherDialog.setLocationRelativeTo(parent);
        deptSubTeacherDialog.setResizable(false);
        deptSubTeacherDialog.setVisible(true);
    }

    // adding items in deptSubjectsCombo
    private void addItemsInDeptSubjectsCombo(int terms)
    {
        deptSubjectsCombo.removeAllItems();
        for(int i=1; i<=terms; i++)
        {
            deptSubjectsCombo.addItem(i);
        }
    }
}