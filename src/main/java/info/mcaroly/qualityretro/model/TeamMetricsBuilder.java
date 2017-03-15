/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

import java.util.ArrayList;
import java.util.List;

public class TeamMetricsBuilder {

    private String teamName;
    private String logo;
    private List<SpotifyMetric> metrics;

    public static TeamMetricsBuilder metricsForTeam(String teamName) {
        return new TeamMetricsBuilder(teamName);
    }

    private TeamMetricsBuilder(String teamName) {
        this.teamName = teamName;
        this.metrics = new ArrayList<>();
    }

    public TeamMetricsBuilder withLogoUrl(String url) {
        this.logo = url;
        return this;
    }

    public TeamMetricsBuilder withMetric(SpotifyMetricBuilder spotifyMetricBuilder) {
        metrics.add(spotifyMetricBuilder.build());
        return this;
    }

    public TeamMetrics build() {
        return new TeamMetrics(teamName, logo, metrics);
    }

}
