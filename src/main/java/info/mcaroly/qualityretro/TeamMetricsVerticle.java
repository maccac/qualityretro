/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro;

import java.util.List;
import java.util.Optional;

import info.mcaroly.qualityretro.message.GetTeamMetricsMessage;
import info.mcaroly.qualityretro.model.TeamMetrics;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;

public class TeamMetricsVerticle extends AbstractVerticle {

    public static final int IO_ERROR = 1;
    public static final int JSON_ERROR = 2;
    public static final int NOT_FOUND = 3;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus().<JsonObject>consumer("team-metrics").handler(teamMetricsHandler());
    }

    private Handler<Message<JsonObject>> teamMetricsHandler() {
        return msg -> {
            FileSystem fs = vertx.fileSystem();
            GetTeamMetricsMessage message = convertToMsg(msg);
            List<String> data = getTeamMetricHistoryAsc(fs, message.getTeamId());
            Optional<Integer> index = getIndexToSelectForVersion(data, message.getVersion());
            if (!index.isPresent()) {
                msg.fail(NOT_FOUND, "Not found");
                return;
            }

            String fileToRead = data.get(index.get());
            fs.readFile(fileToRead, result -> {
                if (result.succeeded()) {
                    fileSucceededToLoad(msg, result);
                } else {
                    fileFailedToLoad(msg, result);
                }
            });
        };
    }

    private void fileFailedToLoad(Message<JsonObject> msg, AsyncResult<Buffer> result) {
        String cause = result.cause().toString();
        msg.fail(IO_ERROR, cause);
    }

    private void fileSucceededToLoad(Message<JsonObject> msg, AsyncResult<Buffer> result) {
        try {
            parseAsTeamMetrics(result.result());
            msg.reply(result.result().toString());
        } catch (IllegalArgumentException e) {
            msg.fail(JSON_ERROR, e.toString());
        }
    }

    private Optional<Integer> getIndexToSelectForVersion(List<String> data, String version) {
         if ("latest".equals(version) && data.size() >= 1) {
            return Optional.of(data.size() - 1);
        } else if (data.size() > 1) {
            return Optional.of(data.size() - 2);
        }
        return Optional.empty();
    }

    private GetTeamMetricsMessage convertToMsg(Message<JsonObject> msg) {
        return msg.body().mapTo(GetTeamMetricsMessage.class);
    }

    private List<String> getTeamMetricHistoryAsc(FileSystem fs, String teamId) {
        List<String> data = fs.readDirBlocking("data", teamId + "{1}.*");
        data.sort(String::compareTo);
        return data;
    }

    private TeamMetrics parseAsTeamMetrics(Buffer result) {
        return result.toJsonObject().mapTo(TeamMetrics.class);
    }
}
