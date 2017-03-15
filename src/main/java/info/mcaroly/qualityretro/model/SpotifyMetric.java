/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

public class SpotifyMetric {

    private String title;
    private String image;
    private String type;
    private int redIndicators;
    private int yellowIndicators;
    private int greenIndicators;
    private Trend trend;

    public SpotifyMetric(String title, String image, int redIndicators, int yellowIndicators, int greenIndicators, Trend trend) {
        this.title = title;
        this.type = "spotify";
        this.image = image;
        this.redIndicators = redIndicators;
        this.yellowIndicators = yellowIndicators;
        this.greenIndicators = greenIndicators;
        this.trend = trend;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public int getRedIndicators() {
        return redIndicators;
    }

    public int getYellowIndicators() {
        return yellowIndicators;
    }

    public int getGreenIndicators() {
        return greenIndicators;
    }

    public Trend getTrend() {
        return trend;
    }
}
