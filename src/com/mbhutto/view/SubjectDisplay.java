package com.mbhutto.view;

import com.mbhutto.controller.SubjectController;
import com.mbhutto.entity.Result;
import com.mbhutto.entity.Subject;
import com.mbhutto.util.Constants;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SubjectDisplay extends JPanel
{
    private JFrame parent;
    JPanel titlePanel;
    JPanel tablePanel;
    JPanel buttonPanel;
    String subjectTableColumnNames[];
    JTable subjectTable;
    DefaultTableModel subjectTableModel;
    JScrollPane subjectTableScroll;
    JButton refreshButton;
    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    private Result subjectsResult;
    private SubjectController   subjectController;


    public SubjectDisplay(JFrame parent)   throws SQLException, ClassNotFoundException
    {
        this.parent = parent ;
        setLayout(new BorderLayout());

        // TABLE
        subjectTableColumnNames = new String[8];
        subjectTable = new JTable();
        loadSubjectsFromDatabase();
        subjectTableScroll = new JScrollPane(subjectTable);
        subjectTable.setFillsViewportHeight(true);
        subjectTableScroll.setPreferredSize(new Dimension(1100, 500));

        // BUTTONS
        refreshButton   = new JButton("Refresh Subjects");
        addButton    = new JButton("Add New Subject");
        viewButton   = new JButton("View Subject");
        updateButton = new JButton("Update Subject");
        deleteButton = new JButton("Delete Subject");

        // TITLE/NORTH PANEL
        titlePanel = new JPanel(); // for title
        add(titlePanel, BorderLayout.NORTH);

        // TABLE/CENTER PANEL
        tablePanel = new JPanel(); // for table
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Subject Table", TitledBorder.CENTER, TitledBorder.TOP));
        add(tablePanel, BorderLayout.CENTER);
        tablePanel.add(subjectTableScroll);

        // BUTTONS/SOUTH PANEL
        buttonPanel = new JPanel(); // for buttons
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        subjectController = new  SubjectController();

        // ACTION LISTENER FOR refreshButton
        refreshButton.addActionListener(e -> {
            try {
                loadSubjectsFromDatabase();
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
                    SubjectFieldsView addSub    = new SubjectFieldsView(parent, "Create Subject", true, Constants.ADD_VIEW , null);
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
                int selectedRow = subjectTable.getSelectedRow();
                String selectedSubject[] = new String[8];

                for (int i = 0; i < subjectTableColumnNames.length; i++)
                {
                    selectedSubject[i] = (String) ((subjectTable.getModel().getValueAt(selectedRow, i)));
                }

                Subject subject = null;
                try
                {
                    subject = new Subject( Long.parseLong(selectedSubject[0]), selectedSubject[1], selectedSubject[2], selectedSubject[3], Integer.parseInt(selectedSubject[4]), Integer.parseInt(selectedSubject[5]), Integer.parseInt(selectedSubject[6]), Integer.parseInt(selectedSubject[7]));
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
                try {
                    SubjectFieldsView displaySub = new SubjectFieldsView(parent, "View Subject", true, Constants.DISPLAY_VIEW ,subject);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });


        // ACTION LISTENER FOR updateButton
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = subjectTable.getSelectedRow();
                String selectedSubject[] = new String[8];

                for (int i = 0; i < subjectTableColumnNames.length; i++)
                {
                    selectedSubject[i] = (String) ((subjectTable.getModel().getValueAt(selectedRow, i)));
                }

                Subject subject = null;
                try
                {
                    subject = new Subject( Long.parseLong(selectedSubject[0]), selectedSubject[1], selectedSubject[2], selectedSubject[3], Integer.parseInt(selectedSubject[4]), Integer.parseInt(selectedSubject[5]), Integer.parseInt(selectedSubject[6]), Integer.parseInt(selectedSubject[7]));
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }

                try {
                    SubjectFieldsView updateSub = new SubjectFieldsView(parent, "Update Subject", true, Constants.UPDATE_VIEW, subject);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int selectedRow = subjectTable.getSelectedRow();
                String selectedSubject[] = new String[8];

                for (int i = 0; i < subjectTableColumnNames.length; i++)
                {
                    selectedSubject[i] = (String) ((subjectTable.getModel().getValueAt(selectedRow, i)));
                }

                Subject subject = null;
                try
                {
                     subject = new Subject( Long.parseLong(selectedSubject[0]), selectedSubject[1], selectedSubject[2], selectedSubject[3], Integer.parseInt(selectedSubject[4]), Integer.parseInt(selectedSubject[5]), Integer.parseInt(selectedSubject[6]), Integer.parseInt(selectedSubject[7]));
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }

                int decision = JOptionPane.showConfirmDialog(tablePanel, "Are you sure you want to delete this item?");
                if (decision == 0)
                {
                    try
                    {
                        int deletedRows = subjectController.deleteRecord(subject);
                        System.out.println(deletedRows);
                        if (deletedRows > 0)
                        {
                            JOptionPane.showMessageDialog(tablePanel, "Subject deleted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                            boolean recordFound = false;
                            String[][] tempRecords = new String[(subjectsResult.getRecords().length - 1)][8];
                            for (int i = 0; i < subjectsResult.getRecords().length; i++)
                            {
                                if (subject.getSubjectId() == Long.parseLong(subjectsResult.getRecords()[i][0]))
                                {
                                    recordFound = true;
                                    continue;
                                }

                                for (int j = 0; j < subjectsResult.getRecords()[i].length; j++)
                                {
                                    if (!recordFound)
                                    {
                                        tempRecords[i][j] = subjectsResult.getRecords()[i][j];
                                    } else
                                    {
                                        tempRecords[i - 1][j] = subjectsResult.getRecords()[i][j];
                                    }
                                }
                            }
                            subjectsResult.setRecords(new String[tempRecords.length][8]);
                            for (int i = 0; i < tempRecords.length; i++)
                            {
                                subjectsResult.getRecords()[i][0] = tempRecords[i][0];
                                subjectsResult.getRecords()[i][1] = tempRecords[i][1];
                                subjectsResult.getRecords()[i][2] = tempRecords[i][2];
                                subjectsResult.getRecords()[i][3] = tempRecords[i][3];
                                subjectsResult.getRecords()[i][4] = tempRecords[i][4];
                                subjectsResult.getRecords()[i][5] = tempRecords[i][5];
                                subjectsResult.getRecords()[i][6] = tempRecords[i][6];
                                subjectsResult.getRecords()[i][7] = tempRecords[i][7];

                            }

                            loadSubjects(subjectsResult.getRecords());

                        }

                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }


            }
        });




        setVisible(true);// making the frame visible
    }

    private void loadSubjectsFromDatabase() throws SQLException, ClassNotFoundException
    {   
    	subjectController = new  SubjectController();
        subjectsResult = subjectController.getRecords();
        loadSubjects(subjectsResult.getRecords());
    }

    private void loadSubjects(String[][] records)
    {
        DefaultTableModel model = (DefaultTableModel) subjectTable.getModel();
        subjectTableColumnNames = new String[]{"ID", "Subject Code", "Subject Name", "Short Name", "Theory Marks", "Theory Classes (per week)", "Practical Marks", "Practical Classes (per week)"};
        String[][] data = records;
        model.setDataVector(data, subjectTableColumnNames);
        subjectTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        subjectTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        subjectTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        subjectTable.getColumnModel().getColumn(3).setPreferredWidth(15);
        subjectTable.getColumnModel().getColumn(4).setPreferredWidth(15);
        subjectTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        subjectTable.getColumnModel().getColumn(6).setPreferredWidth(15);
        subjectTable.getColumnModel().getColumn(7).setPreferredWidth(60);
    }
}
