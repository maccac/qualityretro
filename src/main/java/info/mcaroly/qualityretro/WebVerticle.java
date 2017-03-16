package info.mcaroly.qualityretro;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class WebVerticle extends AbstractVerticle {

    private static final int PORT = 10080;

    @Override
    public void start(Future<Void> fut) throws Exception {
        final Router router = Router.router(vertx);
        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setDirectoryListing(true);
        staticHandler.setIndexPage("index.html");

        router.get("/metrics/:teamId")
                .produces("application/json")
                .handler(ctx -> {
                    String teamId = ctx.request().getParam("teamId");
                    vertx.eventBus().<String>send("team-metrics", teamId, result -> {
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
                });

        router.route("/*")
                .handler(staticHandler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
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
