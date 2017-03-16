/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro;

import static info.mcaroly.qualityretro.utils.JsonUtil.json;

import java.util.List;
import java.util.stream.Collectors;

import info.mcaroly.qualityretro.message.GetTeamMetricsMessage;
import info.mcaroly.qualityretro.model.TeamMetrics;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;

public class TeamMetricsVerticle extends AbstractVerticle {

    public static final int IO_ERROR = 1;
    public static final int JSON_ERROR = 2;
    public static final int NOT_FOUND = 3;

    public Handler<Message<JsonObject>> teamMetricsHandler() {
        FileSystem fs = vertx.fileSystem();
        return msg -> {
            GetTeamMetricsMessage message = msg.body().mapTo(GetTeamMetricsMessage.class);

            List<String> data = fs.readDirBlocking("data", message.getTeamId() + "{1}.*");
            data.sort(String::compareTo);

            data.forEach(System.out::println);
            if (data.isEmpty()) {
                msg.fail(NOT_FOUND, "Not found");
                return;
            }


            String fileToRead;
            if ("latest".equals(message.getVersion())) {
                fileToRead = data.get(data.size() - 1);
            } else if (data.size() > 1){
                fileToRead = data.get(data.size() - 2);
            } else {
                msg.fail(NOT_FOUND, "Not found");
                return;
            }

            System.out.println(fileToRead);

            fs.readFile(fileToRead, result -> {
                if (result.succeeded()) {
                    try {
                        String json = json(parseAsTeamMetrics(result));
                        System.out.println(json);
                        msg.reply(json);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        msg.fail(JSON_ERROR, e.toString());
                    }
                } else {
                    String cause = result.cause().toString();
                    System.out.println(cause);
                    msg.fail(IO_ERROR, cause);
                }
            });
        };
    }

    private TeamMetrics parseAsTeamMetrics(AsyncResult<Buffer> result) {
        return result.result().toJsonObject().mapTo(TeamMetrics.class);
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus().<JsonObject>consumer("team-metrics").handler(teamMetricsHandler());
    }
}
