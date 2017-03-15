/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

import java.util.List;

public class TeamMetrics {

    private String teamName;
    private String teamLogo;
    private List<SpotifyMetric> metrics;

    public TeamMetrics(String teamName, String teamLogo, List<SpotifyMetric> metrics) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.metrics = metrics;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public List<SpotifyMetric> getMetrics() {
        return metrics;
    }
}
