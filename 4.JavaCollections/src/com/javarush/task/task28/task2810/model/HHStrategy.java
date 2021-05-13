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

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
            int pageNumber = 0;
            Document document = getDocument(searchString, pageNumber);
            Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            List<Vacancy> vacancies = new ArrayList<>();
            while (elements.size() != 0) {
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    String siteName = "http://hh.ua";
                    vacancy.setSiteName(siteName);

                    Element titleElement = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").get(0);
                    vacancy.setUrl(titleElement.attr("href"));

                    String title = titleElement.ownText();
                    vacancy.setTitle(title);

                    String salary;
                    Elements salaryElement = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");
                    if (salaryElement.size() == 0) {
                        salary = "";
                    } else {
                        salary = salaryElement.get(0).ownText();
                    }
                    vacancy.setSalary(salary);

                    String city = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").get(0).ownText();
                    vacancy.setCity(city);

                    String companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").get(0).ownText();
                    vacancy.setCompanyName(companyName);

                    vacancies.add(vacancy);
                }
                pageNumber++;
                document = getDocument(searchString, pageNumber);
                elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            }
            return vacancies;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36")
                .referrer("")
                .get();
        return document;
    }

}
