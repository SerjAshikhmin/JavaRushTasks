package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Map<Date, Double> profitByDays = StatisticManager.getInstance().getProfitByDays();
        Double totalSum = new Double(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Double> entry : profitByDays.entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %.2f", dateFormat.format(entry.getKey()), entry.getValue()));
            totalSum += entry.getValue();
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalSum));
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> cookingTimeByDays = StatisticManager.getInstance().getCookingTimeByDays();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Map<String, Integer>> oneDay : cookingTimeByDays.entrySet()) {
            ConsoleHelper.writeMessage(dateFormat.format(oneDay.getKey()));
            for (Map.Entry<String, Integer> cook : oneDay.getValue().entrySet()) {
                if (cook.getValue() > 0)
                    ConsoleHelper.writeMessage(String.format("%s - %d min", cook.getKey(), cook.getValue()));
            }
        }
    }

    public void printActiveVideoSet() {
        Map<String, Integer> activeVideoSet = StatisticAdvertisementManager.getInstance().getActiveVideoSet();
        for (Map.Entry<String, Integer> entry : activeVideoSet.entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
    }

    public void printArchivedVideoSet() {
        List<String> archivedVideoSet = StatisticAdvertisementManager.getInstance().getArchivedVideoSet();
        for (String archivedVideo : archivedVideoSet) {
            ConsoleHelper.writeMessage(archivedVideo);
        }
    }
}
