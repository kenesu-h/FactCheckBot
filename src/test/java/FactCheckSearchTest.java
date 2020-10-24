import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.kenesu_h.factcheckbot.model.FactCheckClaim;
import com.github.kenesu_h.factcheckbot.model.FactCheckClaimImpl;
import com.github.kenesu_h.factcheckbot.model.FactCheckSearch;
import com.github.kenesu_h.factcheckbot.model.FactCheckSearchImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 * A class representing a test suite for {@link FactCheckSearch}.
 */
public class FactCheckSearchTest {

  private FactCheckClaim nuke;
  private List<FactCheckClaim> localClaims = new ArrayList<>();
  private FactCheckSearch localSearch = new FactCheckSearchImpl(localClaims);

  // Initializes data for any variables above that are undefined.
  private void initData() {
    try {
      nuke = new FactCheckClaimImpl("Did Trump Ask Advisers About ‘Nuking’ Hurricanes?",
          "en", "Snopes", "Unproven",
          new Date(2019, 8, 27),
          "U.S. President Donald Trump has asked his advisers about the feasibility of "
              + "stopping hurricanes with nuclear bombs.",
          new URL("https://www.snopes.com/fact-check/trump-nuke-hurricanes/"));
      localClaims.add(nuke);
    } catch (MalformedURLException mue) {
      fail("One or more URLs are malformed.");
    }
  }

  @Test
  public void setApiKeyLocalSearch() {
    try {
      initData();
      localSearch.setApiKey("some-api-key");
    } catch (IllegalArgumentException iae) {
      assertEquals("Cannot set an API key for a local search.", iae.getMessage());
    }
  }

  @Test
  public void setQueryThenExecute() {
    initData();
    assertEquals(new ArrayList<FactCheckClaim>(), localSearch.execute());
    localSearch.setQuery("Did Trump Ask Advisers About ‘Nuking’ Hurricanes?");
    assertEquals(new ArrayList<>(Arrays.asList(nuke)), localSearch.execute());
  }
}