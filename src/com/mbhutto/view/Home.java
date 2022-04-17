package com.mbhutto.view;

import javax.swing.*        ;
import java.awt.*           ;
import java.awt.event.*     ;
import java.sql.SQLException;

public class Home extends JFrame
{
    private Navigation navigation ;
    private TitleBar titleBar     ;
    
    public Home() throws SQLException, ClassNotFoundException 
    {
        super("Home");
        navigation      = new Navigation(this);
        titleBar		= new TitleBar();
        getContentPane().add(navigation, BorderLayout.WEST);
        getContentPane().add(titleBar, BorderLayout.NORTH);
        setDefaultCloseOperation (DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    final int confirm   = JOptionPane.showConfirmDialog(null, "Do you want to close ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if ( confirm == JOptionPane.YES_OPTION )
                    {
                        JOptionPane.showMessageDialog(null,"Thank you for using Expert Time Table System !", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }
            }
        );

    }
}