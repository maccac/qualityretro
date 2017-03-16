/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

import java.util.ArrayList;
import java.util.List;

public class TeamMetricsBuilder {

    private String teamId;
    private String teamName;
    private String logo;
    private List<SpotifyMetric> metrics;

    public static TeamMetricsBuilder metricsForTeam(String teamId) {
        return new TeamMetricsBuilder(teamId);
    }

    private TeamMetricsBuilder(String teamId) {
        this.teamId = teamId;
        this.metrics = new ArrayList<>();
    }

    public TeamMetricsBuilder withTeamName(String name) {
        this.teamName = name;
        return this;
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
        return new TeamMetrics(teamId, teamName, logo, metrics);
    }

}
