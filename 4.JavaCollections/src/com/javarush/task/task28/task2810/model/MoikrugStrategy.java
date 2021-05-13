package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
            int pageNumber = 0;
            Document document = getDocument(searchString, pageNumber);
            Elements elements = document.getElementsByAttributeValue("class", "job");
            elements.addAll(document.getElementsByAttributeValue("class", "job marked"));
            List<Vacancy> vacancies = new ArrayList<>();
            while (elements.size() != 0) {
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    String siteName = "https://moikrug.ru";
                    vacancy.setSiteName(siteName);

                    Element titleElement = element.getElementsByAttributeValue("class", "title").get(0);
                    vacancy.setUrl(siteName + titleElement.getElementsByAttribute("href").get(0).attr("href"));

                    vacancy.setTitle(element.getElementsByClass("title").first().text());
                    /*String title = titleElement.text();
                    vacancy.setTitle(title);*/

                    vacancy.setSalary(element.getElementsByClass("salary").first().text());
                    /*String salary;
                    Elements salaryElement = element.getElementsByAttributeValue("class", "count");
                    if (salaryElement.size() == 0) {
                        salary = "";
                    } else {
                        salary = salaryElement.get(0).html();
                    }
                    vacancy.setSalary(salary);*/

                    Element city = element.getElementsByClass("location").first();
                    vacancy.setCity(city == null ? "" : city.text());
                    /*String city = element.getElementsByAttributeValue("class", "location").get(0).text();
                    vacancy.setCity(city);*/

                    vacancy.setCompanyName(element.getElementsByClass("company_name").first().text());
                    /*String companyName = element.getElementsByAttributeValue("class", "company_name").get(0).getElementsByAttribute("href").get(0).text();
                    vacancy.setCompanyName(companyName);*/

                    vacancies.add(vacancy);
                }
                pageNumber++;
                document = getDocument(searchString, pageNumber);
                elements = document.getElementsByAttributeValue("class", "job");
                elements.addAll(document.getElementsByAttributeValue("class", "job marked"));
            }
            return vacancies;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
        //Document document = Jsoup.connect("http://javarush.ru/testdata/big28data2.html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36")
                .referrer("")
                .get();
        return document;
    }
}
