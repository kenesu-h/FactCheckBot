import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.model.BotModelImpl;
import com.github.kenesu_h.factcheckbot.model.GoogleFactCheckSearchAdapter;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.factchecktools.v1alpha1.FactCheckTools;
import org.junit.Test;

/**
 * A class representing a test suite for {@link BotModel}. Most of the methods cannot be tested
 * due to reliance on Discord4J classes, which don't officially offer mocks or methods for testing.
 */
public class BotModelTest {

  private final String APPLICATION_NAME = "FactCheckBot";
  private final HttpTransport httpTransport = new NetHttpTransport();
  private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private final FactCheckTools tools =
      new FactCheckTools.Builder(httpTransport, JSON_FACTORY, null)
          .setApplicationName(APPLICATION_NAME)
          .build();

  private final BotModel model =
      new BotModelImpl("", new GoogleFactCheckSearchAdapter(tools), "");

  @Test
  public void getClient() {
    assertNull(model.getClient());
  }

  // TODO: The rest of the methods cannot be tested as they require the bot to log in order perform
  //  a search query. If there's a framework for testing this, create tests accordingly.
}