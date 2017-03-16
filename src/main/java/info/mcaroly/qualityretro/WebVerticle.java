package info.mcaroly.qualityretro;

import info.mcaroly.qualityretro.handler.TeamMetricsHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class WebVerticle extends AbstractVerticle {

    private static final int PORT = 10080;

    @Override
    public void start(Future<Void> fut) throws Exception {
        TeamMetricsHandler teamMetricsHandler = new TeamMetricsHandler();

        final Router router = Router.router(vertx);
        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setDirectoryListing(true);
        staticHandler.setIndexPage("index.html");

        router.get("/metrics/:teamId/latest")
                .produces("application/json")
                .handler(teamMetricsHandler.handleTeamMetricsRequest("latest", vertx));

        router.get("/metrics/:teamId/previous")
                .produces("application/json")
                .handler(teamMetricsHandler.handleTeamMetricsRequest("previous", vertx));

        router.route("/*")
                .handler(staticHandler);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
    }

}
