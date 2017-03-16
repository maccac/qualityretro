/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.service;

import static info.mcaroly.qualityretro.model.SpotifyMetricBuilder.spotifyMetric;
import static info.mcaroly.qualityretro.model.TeamMetricsBuilder.metricsForTeam;

import info.mcaroly.qualityretro.model.TeamMetrics;

public class MetricsService {
    public TeamMetrics getMetricsForTeam(String teamId) {
        return metricsForTeam(teamId)
                .withTeamName("Fluffy Bunnies")
                .withLogoUrl("http://mylogo/logo.gif")
                .withMetric(spotifyMetric("Delivering Value"))
                .withMetric(spotifyMetric("Easy to release"))
                .withMetric(spotifyMetric("Fun"))
                .withMetric(spotifyMetric("Health of codebase"))
                .withMetric(spotifyMetric("Learning"))
                .withMetric(spotifyMetric("Mission"))
                .withMetric(spotifyMetric("Empowerment"))
                .withMetric(spotifyMetric("Speed"))
                .withMetric(spotifyMetric("Suitable Process"))
                .withMetric(spotifyMetric("Support"))
                .withMetric(spotifyMetric("Teamwork"))
                .withMetric(spotifyMetric("Visibility"))
                .build();
    }
}
