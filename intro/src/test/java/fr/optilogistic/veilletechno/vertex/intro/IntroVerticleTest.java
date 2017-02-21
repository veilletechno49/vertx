//=============================================================================
// Veille technologique d'Optilogistic.
// Introduction à Vert.x 3
//=============================================================================
package fr.optilogistic.veilletechno.vertx.intro;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Classe de test du premier Verticle.
 */
@RunWith(VertxUnitRunner.class)
public class IntroVerticleTest {

  /**
   * La chaine attendue pour la comparaison.
    */
  private static final String CHAINE_ATTENDUE = "Bonjour de ma première application Vert.x 3";

  /**
   * Le moteur d'exécution de Vert.x 3.
   */
  private Vertx vertx;

  /**
   * Créé et déploie le verticle de façon asynchrone.
   * @param pContext le contexte d'exécution des tests.
   */
  @Before
  public void setUp(final TestContext pContext) {
    vertx = Vertx.vertx();
    vertx.deployVerticle(IntroVerticle.class.getName(),
        pContext.asyncAssertSuccess());
  }

  /**
   * Termine le verticle de façon asynchrone.
   * @param pContext le contexte d'exécution des tests.
   */
  @After
  public void tearDown(final TestContext pContext) {
    vertx.close(pContext.asyncAssertSuccess());
  }

  /**
   * Méthode principale de test.
   * @param pContext le contexte d'exécution des tests.
   */
  @Test
  public void testMyApplication(final TestContext pContext) {
    final Async async = pContext.async();

    vertx.createHttpClient().getNow(8080, "localhost", "/",
     response -> {
      response.handler(body -> {
        pContext.assertTrue(body.toString().contains(CHAINE_ATTENDUE));
        async.complete();
      });
    });
  }
}