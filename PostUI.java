import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class PostUI extends JFrame implements ActionListener
{
	
	private JLabel title,link,jobResponsibilities,jobRequirements,jobVacancies,jobSalary,jobDeadline,title_tag,link_tag,jobResponsibilities_tag,jobRequirements_tag,jobVacancies_tag,jobSalary_tag,jobDeadline_tag;
	private JButton back;
	private JPanel panel;
	private JTable jt;
	public PostUI(ResultSet rs)
	{
		super("Job Details");
		this.setSize(20000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		try{
			rs.next();
			String titles = rs.getString("title");
			System.out.println("dd"+"titles");
			String links = rs.getString("link");
			System.out.println("dd"+"links");
			String jobResponsibilitiess = rs.getString("JobResponsibilities");
			System.out.println("dd"+"jobResponsibilitiess");
			String jobRequirementss = rs.getString("JobRequirements");
			System.out.println("dd"+"jobRequirementss");
			String jobVacanciess = rs.getString("Vacancy");
			System.out.println("dd"+"jobVacanciess");
			String jobSalarys = rs.getString("Salary");
			System.out.println("dd"+"jobSalarys");
			String jobDeadlines = rs.getString("Deadline");
			System.out.println("dd"+"jobDeadlines");
			title = new JLabel(titles);
		title.setBounds(10, 10, 500, 50);
		title.setFont(new Font("Cambria", Font.BOLD, 25));
		panel.add(title);

		jobRequirements_tag = new JLabel("Job Requirements: ");
		jobRequirements_tag.setBounds(10, 70, 200, 200);
		jobRequirements_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(jobRequirements_tag);

		jobRequirements = new JLabel(jobRequirementss);
		jobRequirements.setBounds(250, 70, 500, 200);
		jobRequirements.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(jobRequirements);

		jobResponsibilities_tag = new JLabel("Job Responsibilities: ");
		jobResponsibilities_tag.setBounds(10, 270, 200, 200);
		jobResponsibilities_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(jobResponsibilities_tag);
		
		
		jobResponsibilities = new JLabel(jobResponsibilitiess);
		jobResponsibilities.setBounds(250, 270, 500, 200);
		jobResponsibilities.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(jobResponsibilities);

		jobVacancies_tag = new JLabel("Job Vacancies: ");
		jobVacancies_tag.setBounds(10, 470, 200, 20);
		jobVacancies_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(jobVacancies_tag);
		
		
		jobVacancies = new JLabel(jobVacanciess);
		jobVacancies.setBounds(250, 470, 500, 20);
		jobVacancies.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(jobVacancies);
		
		jobSalary_tag = new JLabel("Job Salary: ");
		jobSalary_tag.setBounds(10, 500, 200, 20);
		jobSalary_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(jobSalary_tag);
		
		
		jobSalary = new JLabel(jobSalarys);
		jobSalary.setBounds(250, 500, 500, 20);
		jobSalary.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(jobSalary);

		jobDeadline_tag = new JLabel("Deadline: ");
		jobDeadline_tag.setBounds(10, 530, 200, 20);
		jobDeadline_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(jobDeadline_tag);
		
		
		jobDeadline = new JLabel(jobDeadlines);
		jobDeadline.setBounds(250, 530, 500, 20);
		jobDeadline.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(jobDeadline);

		link_tag = new JLabel("Link: ");
		link_tag.setBounds(10, 560, 200, 20);
		link_tag.setFont(new Font("Cambria", Font.BOLD, 15));
		panel.add(link_tag);
		
		
		link = new JLabel(links);
		link.setBounds(250, 560, 500, 20);
		link.setFont(new Font("Cambria", Font.ITALIC, 15));
		panel.add(link);

		this.add(panel);
		}catch(Exception ex){
			System.out.println("Exception : " +ex.getMessage());
		}
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		
		
	}
}