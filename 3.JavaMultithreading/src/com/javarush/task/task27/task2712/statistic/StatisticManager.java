package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager statisticManager;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        if (statisticManager == null)
            statisticManager = new StatisticManager();
        return statisticManager;
    }

    public class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType event: EventType.values()) {
                storage.put(event, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public Map<Date, Double> getProfitByDays() {
        Map<Date, Double> profitByDays = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> videoEvents = new ArrayList<>();

        for (Map.Entry<EventType, List<EventDataRow>> entry : statisticStorage.getStorage().entrySet()) {
            for (EventDataRow event : entry.getValue()) {
                if (event.getType() == EventType.SELECTED_VIDEOS) {
                    videoEvents.add(event);
                }
            }
        }

        for (EventDataRow videoEvent : videoEvents) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(videoEvent.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            long amount = ((VideoSelectedEventDataRow) videoEvent).getAmount();
            Double profitByDay = (double)amount / 100;
            if (!profitByDays.containsKey(date)) {
                profitByDays.put(date, profitByDay);
            } else {
                Double tempProfit = profitByDays.get(date);
                profitByDays.put(date, profitByDay + tempProfit);
            }
        }
        return profitByDays;
    }

    public Map<Date, Map<String, Integer>> getCookingTimeByDays() {
        Map<Date, Map<String, Integer>> cookingTimeByDays = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> cookedOrderEvents = new ArrayList<>();

        for (Map.Entry<EventType, List<EventDataRow>> entry : statisticStorage.getStorage().entrySet()) {
            for (EventDataRow event : entry.getValue()) {
                if (event.getType() == EventType.COOKED_ORDER) {
                    cookedOrderEvents.add(event);
                }
            }
        }

        for (EventDataRow cookedEvent : cookedOrderEvents) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cookedEvent.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();

            Integer cookingTime = new Integer((int) Math.ceil(cookedEvent.getTime() / 60));
            String cookName = ((CookedOrderEventDataRow) cookedEvent).getCookName();

            if (!cookingTimeByDays.containsKey(date)) {
                Map<String, Integer> cooksInOneDay = new TreeMap<>();
                cooksInOneDay.put(cookName, cookingTime);
                cookingTimeByDays.put(date, cooksInOneDay);
            } else {
                Map<String, Integer> cooksInOneDay = cookingTimeByDays.get(date);
                if (!cooksInOneDay.containsKey(cookName)) {
                    cooksInOneDay.put(cookName, cookingTime);
                } else {
                    Integer tempCookTime = cooksInOneDay.get(cookName);
                    cooksInOneDay.put(cookName, tempCookTime + cookingTime);
                }
                cookingTimeByDays.put(date, cooksInOneDay);
            }
        }

        return cookingTimeByDays;
    }
}
