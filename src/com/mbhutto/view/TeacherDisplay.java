package com.mbhutto.view;

import com.mbhutto.entity.Result;
import com.mbhutto.controller.TeacherController;
import com.mbhutto.entity.Department;
import com.mbhutto.entity.Teacher;
import com.mbhutto.util.Constants;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TeacherDisplay extends JPanel
{
    private JFrame parent;
    JPanel titlePanel;
    JPanel tablePanel;
    JPanel buttonPanel;
    String teacherTableColumnNames[];
    JTable teacherTable;
    DefaultTableModel teacherTableModel;
    JScrollPane teacherTableScroll;
    JButton refreshButton;
    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    private TeacherFieldsView teacherFieldsView;
    private Result teachersResult;
    TeacherController teacherController;

    public TeacherDisplay(JFrame parent) throws SQLException, ClassNotFoundException
    {
        this.parent = parent ;
        setLayout(new BorderLayout());
        teacherController = new TeacherController(); 

        // TABLE
        teacherTableColumnNames = new String[8];
        teacherTable = new JTable();
        loadTeachersFromDatabase();
        teacherTableScroll = new JScrollPane(teacherTable);
        teacherTable.setFillsViewportHeight(true);
        teacherTableScroll.setPreferredSize(new Dimension(1100, 500));


        // BUTTONS
        refreshButton   = new JButton("Refresh Teachers");
        addButton    = new JButton("Add New Teacher");
        viewButton   = new JButton("View Teacher");
        updateButton = new JButton("Update Teacher");
        deleteButton = new JButton("Delete Teacher");

        // TITLE/NORTH PANEL
        titlePanel = new JPanel(); // for title
        add(titlePanel, BorderLayout.NORTH);

        // TABLE/CENTER PANEL
        tablePanel = new JPanel(); // for table
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Teacher Table", TitledBorder.CENTER, TitledBorder.TOP));
        add(tablePanel, BorderLayout.CENTER);
        tablePanel.add(teacherTableScroll);

        // BUTTONS/SOUTH PANEL
        buttonPanel = new JPanel(); // for buttons
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ACTION LISTENER FOR refreshButton
        refreshButton.addActionListener(e -> {
            try {
                loadTeachersFromDatabase();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        // ACTION LISTENER FOR addButton
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                     teacherFieldsView = new TeacherFieldsView(parent, "Add Teacher", true, Constants.ADD_VIEW, null);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        // ACTION LISTENER FOR viewButton
        viewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = teacherTable.getSelectedRow();
                Teacher teacher = new Teacher(Long.parseLong(teachersResult.getRecords()[selectedRow][0]), new Department(Long.parseLong(teachersResult.getRecords()[selectedRow][11]), teachersResult.getRecords()[selectedRow][12], teachersResult.getRecords()[selectedRow][13], Integer.parseInt(teachersResult.getRecords()[selectedRow][14]), Integer.parseInt(teachersResult.getRecords()[selectedRow][15])), teachersResult.getRecords()[selectedRow][2], teachersResult.getRecords()[selectedRow][3], teachersResult.getRecords()[selectedRow][4],teachersResult.getRecords()[selectedRow][5],teachersResult.getRecords()[selectedRow][6],teachersResult.getRecords()[selectedRow][7],(teachersResult.getRecords()[selectedRow][8]),teachersResult.getRecords()[selectedRow][9] ,Byte.parseByte(teachersResult.getRecords()[selectedRow][10]) );


                try
                {
                    teacherFieldsView = new TeacherFieldsView(parent, "View Teacher", true, Constants.DISPLAY_VIEW, teacher);
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException)
                {
                    classNotFoundException.printStackTrace();
                }
            }
        });


        // ACTION LISTENER FOR updateButton
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = teacherTable.getSelectedRow();
                Teacher teacher = new Teacher(Long.parseLong(teachersResult.getRecords()[selectedRow][0]), new Department(Long.parseLong(teachersResult.getRecords()[selectedRow][11]), teachersResult.getRecords()[selectedRow][12], teachersResult.getRecords()[selectedRow][13], Integer.parseInt(teachersResult.getRecords()[selectedRow][14]), Integer.parseInt(teachersResult.getRecords()[selectedRow][15])), teachersResult.getRecords()[selectedRow][2], teachersResult.getRecords()[selectedRow][3], teachersResult.getRecords()[selectedRow][4],teachersResult.getRecords()[selectedRow][5],teachersResult.getRecords()[selectedRow][6],teachersResult.getRecords()[selectedRow][7],(teachersResult.getRecords()[selectedRow][8]),teachersResult.getRecords()[selectedRow][9] ,Byte.parseByte(teachersResult.getRecords()[selectedRow][10]) );
                try
                {
                    teacherFieldsView = new TeacherFieldsView(parent, "Update Teacher", true, Constants.UPDATE_VIEW, teacher);
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
        });

        // ACTION LISTENER FOR deleteButton
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = teacherTable.getSelectedRow();
                Teacher teacher = new Teacher(Long.parseLong(teachersResult.getRecords()[selectedRow][0]), new Department(Long.parseLong(teachersResult.getRecords()[selectedRow][11]), teachersResult.getRecords()[selectedRow][12], teachersResult.getRecords()[selectedRow][13], Integer.parseInt(teachersResult.getRecords()[selectedRow][14]), Integer.parseInt(teachersResult.getRecords()[selectedRow][15])), teachersResult.getRecords()[selectedRow][2], teachersResult.getRecords()[selectedRow][3], teachersResult.getRecords()[selectedRow][4],teachersResult.getRecords()[selectedRow][5],teachersResult.getRecords()[selectedRow][6],teachersResult.getRecords()[selectedRow][7],(teachersResult.getRecords()[selectedRow][8]),teachersResult.getRecords()[selectedRow][9] ,Byte.parseByte(teachersResult.getRecords()[selectedRow][10]) );

                int decision = JOptionPane.showConfirmDialog(tablePanel, "Are you sure you want to delete this item?");
                if (decision == 0)
                {
                    try
                    {
                        int deletedRows = teacherController.deleteRecord(teacher);
                        System.out.println(deletedRows);
                        if (deletedRows > 0)
                        {
                            JOptionPane.showMessageDialog(tablePanel, "Department deleted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                            boolean recordFound = false;
                            String[][] tempRecords = new String[(teachersResult.getSubRecords().length - 1)][8];
                            for (int i = 0; i < teachersResult.getSubRecords().length; i++)
                            {
                                if (teacher.getTeacherId() == Long.parseLong(teachersResult.getSubRecords()[i][0]))
                                {
                                    recordFound = true;
                                    continue;
                                }

                                for (int j = 0; j < teachersResult.getSubRecords()[i].length; j++)
                                {
                                    if (!recordFound)
                                    {
                                        tempRecords[i][j] = teachersResult.getSubRecords()[i][j];
                                    } else
                                    {
                                        tempRecords[i - 1][j] = teachersResult.getSubRecords()[i][j];
                                    }
                                }
                            }
                            teachersResult.setSubRecords( new String[tempRecords.length][8]);
                            for (int i = 0; i < tempRecords.length; i++)
                            {
                                teachersResult.getSubRecords()[i][0] = tempRecords[i][0];
                                teachersResult.getSubRecords()[i][1] = tempRecords[i][1];
                                teachersResult.getSubRecords()[i][2] = tempRecords[i][2];
                                teachersResult.getSubRecords()[i][3] = tempRecords[i][3];
                                teachersResult.getSubRecords()[i][4] = tempRecords[i][4];
                                teachersResult.getSubRecords()[i][5] = tempRecords[i][5];
                                teachersResult.getSubRecords()[i][6] = tempRecords[i][6];
                                teachersResult.getSubRecords()[i][7] = tempRecords[i][7];
                            }
                            loadTeachers(teachersResult.getSubRecords());

                        }

                    }
                    catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        setVisible(true);// making the frame visibe
    }

    private void loadTeachersFromDatabase() throws SQLException, ClassNotFoundException {
    	TeacherController     teacherController = new  TeacherController();
        teachersResult = teacherController.getRecords();
        loadTeachers(teachersResult.getSubRecords());
    }

    private void loadTeachers(String[][] records)
    {
        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
        teacherTableColumnNames = new String[]{"ID", "Department", "Teacher Name", "NIC", "Mobile Number", "Email", "Qualification", "Designation"};
        String[][] data = records;
        model.setDataVector(data, teacherTableColumnNames);
        teacherTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        teacherTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        teacherTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        teacherTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        teacherTable.getColumnModel().getColumn(4).setPreferredWidth(20);
        teacherTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        teacherTable.getColumnModel().getColumn(6).setPreferredWidth(5);
        teacherTable.getColumnModel().getColumn(7).setPreferredWidth(20);
    }
}
