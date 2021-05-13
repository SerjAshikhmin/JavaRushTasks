package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager statisticAdvertisementManager;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {

    }

    public static StatisticAdvertisementManager getInstance() {
        if (statisticAdvertisementManager == null)
            statisticAdvertisementManager = new StatisticAdvertisementManager();
        return statisticAdvertisementManager;
    }

    public Map<String, Integer> getActiveVideoSet() {
        Map<String, Integer> activeVideoSet = new TreeMap<>();
        List<Advertisement> videos = advertisementStorage.list();
        for (Advertisement advertisement : videos) {
            if (advertisement.getHits() > 0) {
                activeVideoSet.put(advertisement.getName(), advertisement.getHits());
            }
        }

        return activeVideoSet;
    }

    public List<String> getArchivedVideoSet() {
        List<String> archivedVideoSet = new ArrayList<>();
        List<Advertisement> videos = advertisementStorage.list();
        for (Advertisement advertisement : videos) {
            if (advertisement.getHits() == 0) {
                archivedVideoSet.add(advertisement.getName());
            }
        }
        Collections.sort(archivedVideoSet, String::compareToIgnoreCase);
        return archivedVideoSet;
    }
}
