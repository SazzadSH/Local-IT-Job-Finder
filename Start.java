import java.lang.*;
public class Start
{
	public static void main(String args[])
	{
		MainScrapperChakri obj = new MainScrapperChakri();
    	obj.scrapperLink("http://www.chakri.com/job?catId=25");
    	obj.scrapperPost(); 


		Login l = new Login();
		l.setVisible(true);
	}
}