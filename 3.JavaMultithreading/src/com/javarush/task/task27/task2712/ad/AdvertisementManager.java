package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().size() == 0) throw new NoVideoAvailableException();
        List<Advertisement> video = new ArrayList<>();
        for (Object video1 : storage.list()) {
            Advertisement adv = (Advertisement) video1;
            video.add(adv);
        }

        Collections.sort(video, (Comparator.comparingInt(Advertisement::getDuration)));
        Collections.sort(video, ((o1, o2) -> (int) (o1.getAmountPerOneDisplaying() - o2.getAmountPerOneDisplaying())));
        Collections.reverse(video);

        int freetime = timeSeconds;

        List<Advertisement> videoPlayed = new ArrayList<>();
        int totalDuration = 0;
        int amount = 0;
        for (Advertisement advertisement : video) {
            if (advertisement.getDuration() <= freetime && advertisement.getAmountPerOneDisplaying() > 0 && advertisement.getHits() > 0) {
                ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", advertisement.getName(), advertisement.getAmountPerOneDisplaying(),
                        1000 * advertisement.getAmountPerOneDisplaying() / advertisement.getDuration()));
                videoPlayed.add(advertisement);
                amount += advertisement.getAmountPerOneDisplaying();
                totalDuration += advertisement.getDuration();
                freetime = freetime - advertisement.getDuration();
                advertisement.revalidate();
            }
        }
        StatisticManager statisticManager = StatisticManager.getInstance();
        statisticManager.register(new VideoSelectedEventDataRow(videoPlayed, amount, totalDuration));
    }
}
