import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	private JLabel nameLabel, passLabel;
	private JTextField userNameTF;
	private JPasswordField passPF;
	private JButton buttonLogin, buttonClose,createUser;
	private JPanel panel;
	private JRadioButton radioAdmin, radioUser;
	private ButtonGroup bg;
	private boolean flag;
	
	public Login()
	{
		super("Login Window");
		
		this.setSize(1000, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		nameLabel = new JLabel("User Name	: ");
		nameLabel.setBounds(400, 120, 150, 25);
		panel.add(nameLabel);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(400, 150, 150, 25);
		panel.add(userNameTF);
		
		passLabel = new JLabel("Password	: ");
		passLabel.setBounds(400, 180, 150, 25);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(400, 210, 150, 25);
		panel.add(passPF);
		
		buttonLogin = new JButton("Login");
		buttonLogin.setBounds(350,250,80,40);
		buttonLogin.addActionListener(this);
		panel.add(buttonLogin);
		
		buttonClose = new JButton("Close");
		buttonClose.setBounds(510,250,80,40);
		buttonClose.addActionListener(this);
		panel.add(buttonClose);

		createUser = new JButton("Create new account");
		createUser.setBounds(350,310,240,40);
		createUser.addActionListener(this);
		panel.add(createUser);
	
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonLogin.getText()))
		{
			System.out.println("hello");
			flag=true;
			check();
		}
		else if(buttonClicked.equals(buttonClose.getText()))
		{
			System.exit(0);
		}
		else if(buttonClicked.equals(createUser.getText()))
		{
			CreateNewUser m = new CreateNewUser();
			this.setVisible(false);
			m.setVisible(true);
		}
		else{}
	}
	
	public void check()
	{
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
                String userId = rs.getString("Name");
                String password = rs.getString("Password");
				String type = rs.getString("Type");
				String approval = rs.getString("Approval");
				
				
				if(userId.equals(userNameTF.getText()))
				{
					flag=false;
					if(password.equals(passPF.getText()))
					{
						if(type.equals("normal"))
						{
							
							if(approval.equals("1")){
							UserHome ush = new UserHome();
							this.setVisible(false);
							ush.setVisible(true);}
							else {JOptionPane.showMessageDialog(this,"Unapproved"); };
						}
						else if(type.equals("admin"))
						{
							AdminHome adh = new AdminHome();
							this.setVisible(false);
							adh.setVisible(true);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Invalid pass"); 
					}
				}			
			}
			if(flag){JOptionPane.showMessageDialog(this,"Invalid name"); }
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
    } 
}

				