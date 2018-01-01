import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class AdminHome extends JFrame implements ActionListener
{
	private JLabel label;
	private JButton buttonLogout;
	private JPanel panel;
	private String userId;

	private JRadioButton app,del;
	
	public AdminHome()
	{
		super("Admin Home Window");
		
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		//panel.setLayout(null);
		
		String query = "SELECT `Approval`, `Type`, `Name`, `Password`, `Email` FROM `user`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
					
			while(rs.next())
			{
				String approval = rs.getString("Approval");
				String type = rs.getString("Type");
				String name = rs.getString("Name");
				String password = rs.getString("Password");
				String email = rs.getString("Email");
				String s="Type: "+type+" Name: "+name+" Password: "+password+" Email: "+email;
				if(approval.equals("0"))
				{
					label = new JLabel(s);
					panel.add(label);
					app = new JRadioButton("Approve");
					app.addActionListener(this);
					panel.add(app);
				}

				if(approval.equals("1"))
				{
					label = new JLabel(s);
					panel.add(label);
					del = new JRadioButton("Delete");
					del.addActionListener(this);
					panel.add(del);
				}
			}
		}
			       catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }

		
		
		
		
		
		this.add(panel);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonLogout.getText()))
		{
			/*Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);*/
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		AdminHome obj = new AdminHome();
	}
}