/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

public class SpotifyMetricBuilder {
    private int yellowIndicators;
    private int redIndicators;
    private int greenIndicators;
    private String image;
    private String title;
    private Trend trend;

    private SpotifyMetricBuilder(String title) {
        this.title = title;
    }

    public static final SpotifyMetricBuilder spotifyMetric(String title) {
        return new SpotifyMetricBuilder(title)
                .withGreenIndicators(randomValue())
                .withRedIndicators(randomValue())
                .withYellowIndicators(randomValue())
                .withTrend(randomTrend());
    }

    private static Trend randomTrend() {
        return Trend.values ()[(int) (Math.random() * Trend.values().length)];
    }

    private static int randomValue() {
        return (int) (Math.random() * 10);
    }

    public SpotifyMetricBuilder withYellowIndicators(int yellowIndicators) {
        this.yellowIndicators = yellowIndicators;
        return this;
    }

    public SpotifyMetricBuilder withRedIndicators(int redIndicators) {
        this.redIndicators = redIndicators;
        return this;
    }

    public SpotifyMetricBuilder withGreenIndicators(int greenIndicators) {
        this.greenIndicators = greenIndicators;
        return this;
    }

    public SpotifyMetricBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public SpotifyMetricBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public SpotifyMetricBuilder withTrend(Trend trend) {
        this.trend = trend;
        return this;
    }

    public SpotifyMetric build() {
        return new SpotifyMetric(title, image, redIndicators, yellowIndicators, greenIndicators, trend);
    }
}
