/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.message;

public class GetTeamMetricsMessage {
    private String teamId;
    private String version;

    public GetTeamMetricsMessage() {
    }

    public GetTeamMetricsMessage(String teamId, String version) {
        this.teamId = teamId;
        this.version = version;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getVersion() {
        return version;
    }
}
