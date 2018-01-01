/*import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class UserHome extends JFrame implements ActionListener
{
	private JLabel labelWelcome, labelName, labelNumber, labelBalance;
	private JButton buttonLogout, buttonChangePassword;
	private JPanel panel;
	
	public UserHome()
	{
		super("User Home Window");
		
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelWelcome = new JLabel("Welcome to Sample Bank");
		labelWelcome.setBounds(200, 50, 200, 30);
		panel.add(labelWelcome);
		
		
		buttonChangePassword = new JButton("Change Password");
		buttonChangePassword.setBounds(150, 250, 150, 30);
		buttonChangePassword.addActionListener(this);
		panel.add(buttonChangePassword);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(320, 250, 150, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae){}
}*/
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserHome extends JFrame implements ActionListener
{
	
	private JLabel title, searchLabel;
	private JTextField searchBox;
	private JButton searchButton, showAllButton;
	private JPanel panel;

	public UserHome()
	{
		super("Job Search");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		
		title = new JLabel("Search Jobs");
		title.setBounds(400, 100, 500, 100);
		title.setFont(new Font("Cambria", Font.BOLD, 35));
		panel.add(title);

		searchLabel = new JLabel("Enter keywords With Commas(example: KW1, KW2, KW3..)");
		searchLabel.setBounds(300, 200, 400, 20);
		searchLabel.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(searchLabel);

		searchBox = new JTextField();
		searchBox.setBounds(300, 250, 400, 40);
		panel.add(searchBox);

		searchButton = new JButton("Search");
		searchButton.setBounds(400, 350, 200, 50);
		searchButton.setBackground(Color.WHITE);
		searchButton.addActionListener(this);
		panel.add(searchButton);

		showAllButton = new JButton("Show All");
		showAllButton.setBounds(800, 550, 100, 30);
		showAllButton.setBackground(Color.WHITE);
		showAllButton.addActionListener(this);
		panel.add(showAllButton);
		
		
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		
		if(elementText.equals(searchButton.getText()))
		{
			String s1 = searchBox.getText();
			String[] words=s1.split(",");
		String fullQuery="";
		for(int i=0;i<words.length;i++)
		{
			fullQuery = fullQuery+""+"+"+words[i];
		}
		System.out.println(fullQuery);
        try
		{
			String query = "SELECT title,link FROM description WHERE MATCH (title,JobRequirements,JobResponsibilities) AGAINST ('"+fullQuery+"' IN NATURAL LANGUAGE MODE);";     
        	String fullQueryCounter = "SELECT COUNT(title) as total FROM description WHERE MATCH (title,JobRequirements,JobResponsibilities) AGAINST ('"+fullQuery+"' IN NATURAL LANGUAGE MODE);";
        	Connection con=null;
        	Statement st = null;
			ResultSet rs = null;
			ResultSet rsCounter = null;
			System.out.println(query);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder","root","");
			System.out.println("connection done");
			st = con.createStatement();
			System.out.println("statement created");
			rs = st.executeQuery(query);
			System.out.println("results received");
			System.out.println("============");
			st = con.createStatement();
			rsCounter = st.executeQuery(fullQueryCounter);
			JobNameShow j = new JobNameShow(rs,rsCounter);
			j.setVisible(true);
			this.setVisible(false);
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
		else if(elementText.equals(showAllButton.getText()))
		{
			String fullQuery = "SELECT `title`, `link` FROM `description`;";
			String fullQueryCounter = "SELECT COUNT(*) AS total FROM `description`;";
			try{
				Connection con=null;
        		Statement st = null;
 				ResultSet rs = null;
 				ResultSet rsCounter = null;
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("driver loaded");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder","root","");
				System.out.println("connection done");
				st = con.createStatement();
				System.out.println("statement created");
				rs = st.executeQuery(fullQuery);
				System.out.println("results received");
				st = con.createStatement();
				rsCounter = st.executeQuery(fullQueryCounter);
				JobNameShow j = new JobNameShow(rs,rsCounter);
				j.setVisible(true);
				}catch(Exception ex){
					System.out.println("Exception : " +ex.getMessage());
				}
		}
		else{}
	}
}
	
