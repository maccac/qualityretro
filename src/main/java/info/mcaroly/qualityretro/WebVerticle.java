package info.mcaroly.qualityretro;

import static info.mcaroly.qualityretro.utils.JsonUtil.json;

import info.mcaroly.qualityretro.service.MetricsService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class WebVerticle extends AbstractVerticle {

    private static final int PORT = 10080;

    private MetricsService metricsService;

    public WebVerticle() {
        this.metricsService = new MetricsService();
    }

    @Override
    public void start(Future<Void> fut) throws Exception {
        final Router router = Router.router(vertx);
        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setDirectoryListing(true);
        staticHandler.setIndexPage("index.html");

        router.get("/metrics/team")
                .produces("application/json")
                .handler(ctx -> {
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .setChunked(true)
                            .write(json(metricsService.getMetricsForTeam()))
                            .end();
                });

        router.route("/*")
                .handler(staticHandler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
    }
}
