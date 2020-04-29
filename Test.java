import java.lang.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;

class Test
{
    public static void main(String[] args)
    {
        //Document doc = Jsoup.connect("https://www.careerjet.com.bd/jobs-information-technology-telecoms.html").userAgent("Mozilla/56.0.0").get();
        //Elements links = doc.select("a.title-company");

        //System.out.println(links);

        // try
        // {
        //     Document doc = Jsoup.connect("https://www.careerjet.com.bd/jobs-information-technology-telecoms.html").userAgent("Mozilla/56.0.0").get();
        //     Elements links = doc.select("a.title-company");

        //     ArrayList jobLinks = new ArrayList();

        //     for(Element link : links)
        //     {
        //         jobLinks.add(link.text());
        //     }

        //     doc = Jsoup.connect("https://www.careerjet.com.bd/wsearch/jobs?c=information-technology-telecoms&l=Bangladesh&lid=122411&b=21&cid=52").userAgent("Mozilla/56.0.0").get();
        //     links = doc.select("a.title-company");

        //     for (Element link : links)
        //     {
        //         jobLinks.add(link.text());
        //     }

            

        //     Elements next = doc.select(".browse > a:nth-child(8)");
        //     String next_check = next.select("a[href]").toString();
        //     String pageLink;
        //     while(!next_check.isEmpty())
        //     {
        //         pageLink = next.attr("abs:href");
        //         doc = Jsoup.connect(pageLink).userAgent("Mozilla/56.0.0").get();
        //         links = doc.select("a.title-company");

        //         for (Element link : links)
        //         {
        //             jobLinks.add(link.text());
        //         }

        //         next = doc.select(".browse > a:nth-child(8)");
        //         next_check = next.select("a[href]").toString();
        //     }

        //     int len = jobLinks.size();

        //     for (int i = 0; i < len; i++)
        //     {
        //         System.out.println(jobLinks.get(i).toString());
        //     }
        // }
        // catch(Exception ex)
        // {
        //     System.out.println("Careerjet Scrapping error: " + ex);
        // }

        /*try
        {
            Document doc = Jsoup.connect("https://www.careerjet.com.bd/jobview/909ef958f092dec95a3fd45c46832358.html").userAgent("Mozilla/56.0.0").get();
            String jobResp = doc.select("b:contains(Job Responsibilities)").first().nextElementSibling().toString();
            jobResp = jobResp.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "").replaceAll("</li>", "\n");

            String jobReq = doc.select("b:contains(Educational Requirements)").first().nextElementSibling().toString();
            jobReq += doc.select("b:contains(Experience Requirements)").first().nextElementSibling().toString();
            jobReq += doc.select("b:contains(Additional Requirements)").first().nextElementSibling().toString();;
            jobReq = jobReq.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "").replaceAll("</li>", "\n");

            String salary = doc.select("span:contains(per month)").first().text();
            String deadline = "Unspecified";

            System.out.println(salary);
        }
        catch(Exception ex)
        {

        }*/


        //CareerjetScrapper obj = new CareerjetScrapper();
        //obj.linkScrapper();
        //obj.postScrapper();


        ArrayList jobLinks = new ArrayList();
        ArrayList jobTitle = new ArrayList();


        try {
            Document doc = Jsoup.connect("https://www.careerjet.com.bd/jobs-information-technology-telecoms.html")
                    .userAgent("Mozilla/56.0.0").get();
            Elements links = doc.select("a.title-company");

            for (Element link : links) {
                jobTitle.add(link.text());
                jobLinks.add(link.attr("abs:href"));
            }

            doc = Jsoup.connect(
                    "https://www.careerjet.com.bd/wsearch/jobs?c=information-technology-telecoms&l=Bangladesh&lid=122411&b=21&cid=52")
                    .userAgent("Mozilla/56.0.0").get();
            links = doc.select("a.title-company");

            for (Element link : links) {
                jobTitle.add(link.text());
                jobLinks.add(link.attr("abs:href"));
            }

            Elements next = doc.select(".browse > a:nth-child(8)");
            String next_check = next.select("a[href]").toString();
            String pageLink;

            while (!next_check.isEmpty()) {
                pageLink = next.attr("abs:href");
                doc = Jsoup.connect(pageLink).userAgent("Mozilla/56.0.0").get();
                links = doc.select("a.title-company");

                for (Element link : links) {
                    jobTitle.add(link.text());
                    jobLinks.add(link.attr("abs:href"));
                }

                next = doc.select(".browse > a:nth-child(8)");
                next_check = next.select("a[href]").toString();
            }
        } catch (Exception ex) {
            System.out.println("Scrapping Error: " + ex);
        }


        Document doc;
        String jobResp = "", jobReq = "", salary ="", deadline, vacancy;

        int len = jobLinks.size();

        for (int i = 0; i < len; i++)
        {

            try
            {
                doc = Jsoup.connect(jobLinks.get(i).toString()).userAgent("Mozilla/56.0.0").get();
            } catch (Exception ex)
            {
                System.out.println("Exception at fetching: " + ex);
                break;
            }

            jobResp = "";
            jobReq = "";

            try {
                jobResp = doc.select("b:contains(Job Responsibilities)").first().nextElementSibling().toString();
                jobResp = jobResp.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "")
                        .replaceAll("</li>", "\n");
            } catch (Exception ex) {
                jobResp = doc.select("div.advertise_compact").text();
            }

            try {
                jobReq = doc.select("b:contains(Educational Requirements)").first().nextElementSibling().toString();
                jobReq += doc.select("b:contains(Experience Requirements)").first().nextElementSibling().toString();
                jobReq += doc.select("b:contains(Additional Requirements)").first().nextElementSibling().toString();
                jobReq = jobReq.replaceAll("<ul>", "").replaceAll("</ul>", "").replaceAll("<li>", "")
                        .replaceAll("</li>", "\n");
            } catch (Exception ex) {
                jobReq = "Check out the job source!";
            }

            try {
                salary = doc.select("span:contains(per month)").first().text();
            } catch (Exception ex) {
                salary = "Negotiable";
            }

            System.out.println(i + " done!");
             
        }
        
    }
}

/// html/body/table/tbody/tr[1]/td/div[3]/div[3]/div/div[68]/p/a[3]
// .browse > a:nth-child(7)
//.browse > a:nth-child(8)
// .browse > a:nth-child(8)
// .browse > a:nth-child(7)