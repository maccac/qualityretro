package info.mcaroly.qualityretro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;

/**
 * Created by mcaroly on 12/3/17.
 */
public class WebVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        final Router router = Router.router(vertx);
        final ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create();

        StaticHandler staticHandler = StaticHandler.create();
        staticHandler.setDirectoryListing(true);

        router.route("/static/*")
                .handler(staticHandler);

        router.get("/index.htm")
                .handler(ctx -> {
                    ctx.put("welcome", "Hi there!");
                    engine.render(
                            ctx,
                            "webroot/templates/index.html",
                            handleIndexRequest(ctx));
                });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private Handler<AsyncResult<Buffer>> handleIndexRequest(RoutingContext ctx) {
        return res -> {
            if (res.succeeded()) {
                ctx.response().end(res.result());
            } else {
                ctx.fail(res.cause());
            }
        };
    }
}
