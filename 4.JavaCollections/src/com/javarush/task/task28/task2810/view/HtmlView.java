package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        Document document = Jsoup.parse(file, "UTF-8", "http://hh.ua");
        return document;
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();
            Element template = document.getElementsByClass("template").get(0).clone();
            template.removeAttr("style");

            document.select("tr[class=vacancy]").remove();
            template.removeClass("template");
            template.attr("class", "vacancy");

            for (Vacancy vacancy : vacancies) {
                Element billet = template.clone();
                billet.getElementsByClass("city").get(0).appendText(vacancy.getCity());
                billet.getElementsByClass("companyName").get(0).appendText(vacancy.getCompanyName());
                billet.getElementsByClass("salary").get(0).appendText(vacancy.getSalary());
                Element href = billet.getElementsByAttribute("href").get(0);
                href.attr("href", vacancy.getUrl());
                href.appendText(vacancy.getTitle());
                document.getElementsByClass("template").get(0).before(billet.outerHtml());
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return document.html();
    }

    private void updateFile(String content) {
        File file = new File(filePath);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
