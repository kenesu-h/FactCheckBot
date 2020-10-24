import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.kenesu_h.factcheckbot.model.FactCheckClaim;
import com.github.kenesu_h.factcheckbot.model.FactCheckClaimImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.junit.Test;

/**
 * A class representing a test suite for {@link FactCheckClaim}.
 */
public class FactCheckClaimTest {

  private FactCheckClaim nuke;

  // Initializes data for any variables above that are undefined.
  private void initData() {
    try {
      nuke = new FactCheckClaimImpl("Did Trump Ask Advisers About ‘Nuking’ Hurricanes?",
          "en", "Snopes", "Unproven",
          new Date(2019, 8, 27),
          "U.S. President Donald Trump has asked his advisers about the feasibility of "
              + "stopping hurricanes with nuclear bombs.",
          new URL("https://www.snopes.com/fact-check/trump-nuke-hurricanes/"));
    } catch (MalformedURLException mue) {
      fail("One or more URLs are malformed.");
    }
  }

  @Test
  public void getTitle() {
    initData();
    assertEquals("Did Trump Ask Advisers About ‘Nuking’ Hurricanes?", nuke.getTitle());
  }

  @Test
  public void getLanguageCode() {
    initData();
    assertEquals("en", nuke.getLanguageCode());
  }

  @Test
  public void getPublisher() {
    initData();
    assertEquals("Snopes", nuke.getPublisher());
  }

  @Test
  public void getTextualRating() {
    initData();
    assertEquals("Unproven", nuke.getTextualRating());
  }

  @Test
  public void getReviewDate() {
    initData();
    assertEquals(new Date(2019, 8, 27), nuke.getReviewDate());
  }

  @Test
  public void getDescription() {
    initData();
    assertEquals("U.S. President Donald Trump has asked his advisers about the feasibility "
        + "of stopping hurricanes with nuclear bombs.", nuke.getDescription());
  }

  @Test
  public void getUrl() {
    try {
      initData();
      assertEquals(new URL("https://www.snopes.com/fact-check/trump-nuke-hurricanes/"),
          nuke.getUrl());
    } catch (MalformedURLException mue) {
      fail("One or more URLs are malformed.");
    }
  }
}