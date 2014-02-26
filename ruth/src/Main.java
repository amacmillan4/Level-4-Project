
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {


	public static void main(String[] args) {

		JFrame frame = new JFrame("Just a wee message to say");
		frame.setMinimumSize(new Dimension(400, 150));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       

		JLabel myLabel = new JLabel("Happy Valentines Day", SwingConstants.CENTER);
		myLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		myLabel.setOpaque(true);
		myLabel.setPreferredSize(new Dimension(50, 50));
		
		JLabel myLabel1 = new JLabel("Love Allan", SwingConstants.CENTER);
		myLabel1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		myLabel1.setOpaque(true);
		myLabel1.setPreferredSize(new Dimension(50, 50));
		
		frame.setVisible(true);
		frame.getContentPane().add(myLabel, BorderLayout.NORTH);
		frame.getContentPane().add(myLabel1);
	}

}


