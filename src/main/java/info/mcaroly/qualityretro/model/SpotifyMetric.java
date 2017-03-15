/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

public class SpotifyMetric {

    private String title;
    private String image;
    private int redIndicators;
    private int yellowIndicators;
    private int greenIndicators;
    private String trend;

    public SpotifyMetric(String title, String image, int redIndicators, int yellowIndicators, int greenIndicators, String trend) {
        this.title = title;
        this.image = image;
        this.redIndicators = redIndicators;
        this.yellowIndicators = yellowIndicators;
        this.greenIndicators = greenIndicators;
        this.trend = trend;
    }
}
