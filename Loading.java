import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Loading extends JFrame
{
	private JLabel imgLabel;
	private ImageIcon image;
	private JPanel panel;
	public Loading()
	{
		super("Loading");
		
		this.setSize(1000, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		image = new ImageIcon("./loader.gif");
		imgLabel = new JLabel(image);
		imgLabel.setBounds(400,200,200,200);
		panel.add(imgLabel);
		
		this.add(panel);

	}
	public static void main(String args[])
	{
				Loading lsa = new Loading();

				lsa.setVisible(true);
	}
}

				