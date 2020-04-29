import java.lang.*;
import java.util.*;
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;
import java.sql.*;

public class CareerjetScrapper
{  
     ArrayList jobLinks = new ArrayList();
     ArrayList jobTitle = new ArrayList();

     public void linkScrapper()
     {
         try
         {
             Document doc = Jsoup.connect("https://www.careerjet.com.bd/jobs-information-technology-telecoms.html").userAgent("Mozilla/56.0.0").get();
             Elements links = doc.select("a.title-company");

             for(Element link : links)
             {
                 jobTitle.add(link.text());
                 jobLinks.add(link.attr("abs:href"));
             }

             doc = Jsoup.connect("https://www.careerjet.com.bd/wsearch/jobs?c=information-technology-telecoms&l=Bangladesh&lid=122411&b=21&cid=52").userAgent("Mozilla/56.0.0").get();
             links = doc.select("a.title-company");

             for (Element link : links)
             {
                 jobTitle.add(link.text());
                 jobLinks.add(link.attr("abs:href"));
             }

             Elements next = doc.select(".browse > a:nth-child(8)");
             String next_check = next.select("a[href]").toString();
             String pageLink;

             while (!next_check.isEmpty())
             {
                 pageLink = next.attr("abs:href");
                 doc = Jsoup.connect(pageLink).userAgent("Mozilla/56.0.0").get();
                 links = doc.select("a.title-company");

                 for (Element link : links)
                 {
                     jobTitle.add(link.text());
                     jobLinks.add(link.attr("abs:href"));
                 }

                 next = doc.select(".browse > a:nth-child(8)");
                 next_check = next.select("a[href]").toString();
             }
         }
         catch(Exception ex)
         {
             System.out.println("Scrapping Error: " + ex);
         }
     }

     public void postScrapper()
     {
         Document doc;
         String jobResp, jobReq, salary, deadline, vacancy;

         int len = jobLinks.size();

         for(int i = 0; i < len; i++)
         {
             try
             {
                 doc = Jsoup.connect(jobLinks.get(i).toString()).userAgent("Mozilla/56.0.0").get();
             }
             catch(Exception ex)
             {
                 System.out.println("Exception: " + ex);
                 break;
             }

             System.out.println("pointA done!");

             jobResp = "";
             jobReq = "";

             try
             {
                 jobResp = doc.select("b:contains(Job Responsibilities)").first().nextElementSibling().toString();
                 jobResp = jobResp.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "")
                         .replaceAll("</li>", "\n");
             }
             catch(Exception ex)
             {
                 jobResp = doc.select("div.advertise_compact").text();
             }

             System.out.println("pointB done!");

             try
             {
                jobReq = doc.select("b:contains(Educational Requirements)").first().nextElementSibling().toString();
                jobReq += doc.select("b:contains(Experience Requirements)").first().nextElementSibling().toString();
                jobReq += doc.select("b:contains(Additional Requirements)").first().nextElementSibling().toString();
                jobReq = jobReq.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "").replaceAll("</li>",
                     "\n");
             }
             catch(Exception ex)
             {
                 jobReq = "Check out the job source!";
             }

             System.out.println("pointC done!");

             try
             {
                salary = doc.select("span:contains(per month)").first().text();
             }
             catch(Exception ex)
             {
                 salary = "Negotiable";
             }

             System.out.println("pointD done!");


             System.out.println(i + " done!");
             insertIntoDB(jobTitle.get(i).toString(), jobResp, jobReq, salary, jobLinks.get(i).toString());

             System.out.println("Db function passed!");
         }
     }

     public void insertIntoDB(String jobTitle, String jobResp, String jobReq, String salary, String jobLink)
     {
         /*String vacancy = "Unspecified", deadline = "Unspecified";
         String query = "INSERT INTO description VALUES ('" + jobTitle + "','" + jobLink + "','" + salary + "','"
                 + jobReq + "','" + jobResp + "','" + vacancy + "','" + deadline + "');";

         try 
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder", "root",
                     "");
             Statement stm = con.createStatement();
             stm.execute(query);
             stm.close();
             con.close();

         }
         catch (Exception ex)
         {
             System.out.println("Exception : " + ex);
         }*/

         System.out.println("into db function");

         String vacancy = "Unspecified";
         String deadline = "Unspecified";

         try {
             Connection con = null;
             Statement st = null;
             ResultSet rs = null;

             String query = "INSERT INTO description VALUES ('" + jobTitle + "','" + jobLink + "','" + salary + "','"
                     + jobReq + "','" + jobResp + "','" + vacancy + "','" + deadline + "');";
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local it job finder", "root", "");
             st = con.createStatement();
             int rs1 = st.executeUpdate(query);

             System.out.println("DB done!");

         }
         catch (Exception e)
         {
             System.out.println("DB Error: " + e);
         }
     }
} 