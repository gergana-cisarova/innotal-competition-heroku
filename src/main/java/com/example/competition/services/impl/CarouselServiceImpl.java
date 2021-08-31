package com.example.competition.services.impl;

import com.example.competition.services.CarouselService;
import com.example.competition.services.ImageShuffler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    private final ImageShuffler imageShuffler;
    private List<String> images = new ArrayList<>();

    public CarouselServiceImpl(@Value("${carousel.images}") List<String> images, ImageShuffler imageShuffler) {
        this.imageShuffler = imageShuffler;
        this.images.addAll(images);
    }

    @PostConstruct
    public void afterInitialize() {
        if (images.size() < 3) {
            throw new IllegalArgumentException("Configured less than three images");
        }
    }

    @Override
    public String firstImage() {
        return images.get(0);
    }

    @Override
    public String secondImage() {
        return images.get(1);
    }

    @Override
    public String thirdImage() {
        return images.get(2);
    }

    @Scheduled(cron =  "*/10 * * * * *")
    public void refresh() {
//        Collections.shuffle(images);
        imageShuffler.shuffle(images);

    }
}
