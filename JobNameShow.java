import java.lang.*;
import java.util.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class JobNameShow extends JFrame implements ActionListener{
	private JLabel intro;
	private JButton button[];
	private JPanel panel;
	private int y_axis,numberOfRecords;
	private JScrollPane pane;
	private ArrayList jLinks = new ArrayList();
	public JobNameShow(ResultSet rs,ResultSet rs1){
		super("Local IT Job Finder");
		this.setSize(1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
		{
			panel = new JPanel();
			panel.setLayout(null);
			panel.setPreferredSize( new Dimension(1000, 700) );
			rs1.next();
			numberOfRecords = rs1.getInt("total");
			button = new JButton[numberOfRecords];;
			intro = new JLabel(numberOfRecords+" Data Found !!");
			intro.setBounds(50,0,900,50);
			intro.setFont(new Font("Times New Roman", Font.BOLD, 20));
			panel.add(intro);
			
			y_axis = 55;
			int i =0;
			while(rs.next()){	
				button[i] = new JButton(rs.getString("title"));
				jLinks.add(rs.getString("link"));
				button[i].setBounds(50,y_axis,900,60);
				button[i].setBackground(Color.WHITE);
				button[i].setFont(new Font("Times New Roman", Font.BOLD, 18));
				button[i].addActionListener(this);
				panel.add(button[i]);
				y_axis+=65;
				i++;
			}
			pane = new JScrollPane(panel);
			pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
       		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			this.add(pane);
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}
	 public void actionPerformed(ActionEvent ae){
		String elementText = ae.getActionCommand();
		for(int i=0;i<numberOfRecords;i++){
			if(elementText.equals(button[i].getText())){
				String fullQuery = "SELECT * FROM `description` WHERE Link='"+jLinks.get(i)+"';";
				try{
					Connection con=null;
        			Statement st = null;
 					ResultSet rs = null;
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("driver loaded");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder","root","");
					System.out.println("connection done");
					st = con.createStatement();
					System.out.println("statement created");
					rs = st.executeQuery(fullQuery);
					System.out.println("results received");

					PostUI p = new PostUI(rs);
					p.setVisible(true);
					this.setVisible(false);
				}catch(Exception ex){
					System.out.println("Exception : " +ex.getMessage());
				}
			}
		}
	 }

}