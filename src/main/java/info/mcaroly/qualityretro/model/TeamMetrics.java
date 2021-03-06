/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.model;

import java.util.List;

public class TeamMetrics {

    private String teamId;
    private String teamName;
    private String teamLogo;
    private String date;
    private List<SpotifyMetric> metrics;

    public TeamMetrics() {
    }

    public TeamMetrics(String teamId, String teamName, String teamLogo, String date, List<SpotifyMetric> metrics) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.metrics = metrics;
    }

    public String getTeamId() {
        return teamId;
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

    public String getDate() {
        return date;
    }
}
