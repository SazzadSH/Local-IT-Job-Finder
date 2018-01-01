import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class CreateNewUser extends JFrame implements ActionListener
{
	private JLabel labelWelcome;
	private JTextField name, email, password;
	private JRadioButton type1,type2;
	private ButtonGroup bg;
	private JPanel panel;
	private JButton button;
	String s;
	public CreateNewUser()
	{
		super("Admin Home Window");
		
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);

		name = new JTextField("Enter Your Name. . .");
		name.setBounds(300,130,400,50);
		panel.add(name);
		
		email = new JTextField("Enter Your email. . .");
		email.setBounds(300,190,400,50);
		panel.add(email);

		password = new JTextField("Enter Your Password. . .");
		password.setBounds(300,250,400,50);
		panel.add(password);

		type1 = new JRadioButton("Normal user");
		type1.setBounds(300,310,150,50);
		panel.add(type1);
		
		type2 = new JRadioButton("Admin user");
		type2.setBounds(550,310,150,50);
		panel.add(type2);

		bg = new ButtonGroup(); 
		bg.add(type1);bg.add(type2);
		
		button = new JButton("C O M P L E T E");
		button.setBounds(300,390,400,60);
		button.addActionListener(this);
		panel.add(button);
		
		this.add(panel);
		}

		public void actionPerformed(ActionEvent ae)
		{
			String elementText = ae.getActionCommand();
			if(type1.isSelected())
			{
				s=type1.getText();
			}
			else if(type2.isSelected())
			{
				s=type2.getText();
			}
			
			if(elementText.equals(button.getText()))
			{
				insertIntoDB();

				Login obj = new Login();
				this.setVisible(false);
				obj.setVisible(true);
			}
			
		}

		public void insertIntoDB()
    {
        

        String query = "INSERT INTO user VALUES ('0','"+s+"','"+name.getText()+"','"+password.getText()+"','"+email.getText()+"');";
        
        System.out.println(query);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder", "root", "");
            Statement stm = con.createStatement();
            stm.execute(query);
            stm.close();
            con.close();
                    
        }
        catch(Exception ex)
        {
            System.out.println("Exception : " +ex.getMessage());
        }
    }
		
	}