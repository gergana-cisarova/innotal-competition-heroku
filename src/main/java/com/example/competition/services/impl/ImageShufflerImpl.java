package com.example.competition.services.impl;

import com.example.competition.services.ImageShuffler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageShufflerImpl implements ImageShuffler {
    @Override
    public void shuffle(List<String> images) {
        Collections.shuffle(images);
    }
}