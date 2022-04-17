package com.mbhutto.view;

import javax.swing.border.* ;
import javax.swing.*        ;
import java.awt.*           ;

public class TitleBar extends JPanel
{


	public TitleBar()
	{
		setLayout(new GridLayout( 1,1,0,0));
		setBorder(new EmptyBorder(2,45,2,45));
		addLabel ("Time Table System", JLabel.CENTER);
	}


	private void addLabel (String str, int allign)
	{
		JLabel titleLabel = new JLabel (str,allign);
		titleLabel.setFont(new Font("Monotype Corsiva", Font.BOLD + Font.ITALIC,96));
		titleLabel.setForeground(Color.magenta);
		titleLabel.setBorder(new EmptyBorder(0,36,0,36));
		this.add(titleLabel);
	}
}