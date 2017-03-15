package info.mcaroly.qualityretro;

import info.mcaroly.qualityretro.model.TeamMetrics;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by mcaroly on 12/3/17.
 */
public class WebVerticle extends AbstractVerticle {

    private static final int PORT = 10080;

    @Override
    public void start(Future<Void> fut) throws Exception {
        final Router router = Router.router(vertx);
        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setDirectoryListing(true);

        router.route("/static/*")
                .handler(staticHandler);

        router.get("/")
                .produces("application/json")
                .handler(ctx -> {
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .setChunked(true)
                            .write(payload())
                            .end();
                });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(PORT);
    }

    private String payload() {
        return ""; //;
    }
}
