//=============================================================================
// Veille technologique d'Optilogistic.
// Introduction à Vert.x 3
//=============================================================================
package fr.optilogistic.veilletechno.vertx.intro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Premier Verticle. Implémente un serveur HTPP très basic.
 */
public class IntroVerticle extends AbstractVerticle {

  @Override
  public void start(final Future<Void> pFut) {
    vertx
        .createHttpServer()
        .requestHandler(r -> {
          r.response().end("<h1>Bonjour de ma première application Vert.x 3 </h1>");
        })
        .listen(8080, result -> {
          if (result.succeeded()) {
            pFut.complete();
          } else {
            pFut.fail(result.cause());
          }
        });
  }
}