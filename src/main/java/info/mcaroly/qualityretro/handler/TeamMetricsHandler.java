/*
 * Copyright (c) 2003 - 2017 Tyro Payments Limited.
 * Lv1, 155 Clarence St, Sydney NSW 2000.
 * All rights reserved.
 */
package info.mcaroly.qualityretro.handler;

import static info.mcaroly.qualityretro.utils.JsonUtil.jsonObject;

import info.mcaroly.qualityretro.TeamMetricsVerticle;
import info.mcaroly.qualityretro.message.GetTeamMetricsMessage;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class TeamMetricsHandler {
    public Handler<RoutingContext> handleTeamMetricsRequest(String version, Vertx vertx) {
        return ctx -> {
            JsonObject message = getTeamMetricsMessage(ctx, version);
            vertx.eventBus().<String>send("team-metrics", message, result -> {
                if (result.succeeded()) {
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .setChunked(true)
                            .write(result.result().body())
                            .setStatusCode(HttpResponseStatus.OK.code())
                            .end();
                } else {
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .setChunked(true)
                            .setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
                            .write(getErrorMessage(result))
                            .end();
                }
            });
        };
    }

    private JsonObject getTeamMetricsMessage(RoutingContext ctx, String version) {
        return jsonObject(new GetTeamMetricsMessage(ctx.request().getParam("teamId"), version));
    }

    private String getErrorMessage(AsyncResult<Message<String>> result) {
        ReplyException replyException = (ReplyException) result.cause();
        switch(replyException.failureCode()) {
            case TeamMetricsVerticle.IO_ERROR : return "Failed to load metrics for team";
            case TeamMetricsVerticle.JSON_ERROR: return "Invalid content in file";
            default:
                return replyException.getMessage();
        }
    }

}
