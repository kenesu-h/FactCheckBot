package com.github.kenesu_h.factcheckbot.model;

import com.google.api.services.factchecktools.v1alpha1.FactCheckTools;
import com.google.api.services.factchecktools.v1alpha1.FactCheckTools.Claims.Search;
import com.google.api.services.factchecktools.v1alpha1.model
    .GoogleFactcheckingFactchecktoolsV1alpha1Claim;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class adapting Google's search client to the {@link FactCheckSearch} interface.
 */
public class GoogleFactCheckSearchAdapter implements FactCheckSearch {

  private final Search search;

  /**
   * Constructs a {@link GoogleFactCheckSearchAdapter} from an instance of Google's fact checking
   * tools.
   *
   * @param tools the instance of Google's fact checking tools
   */
  public GoogleFactCheckSearchAdapter(FactCheckTools tools) {
    try {
      this.search = tools.claims().search();
    } catch (IOException ioe) {
      throw new IllegalStateException(
          "Unable to initialize the Google search client, received the following exception: "
              + ioe.getMessage());
    }
  }

  @Override
  public void setApiKey(String apiKey) {
    this.search.setKey(apiKey);
  }

  @Override
  public void setQuery(String query) {
    this.search.setQuery(query);
  }

  @Override
  public List<FactCheckClaim> execute() {
    try{
      List<GoogleFactcheckingFactchecktoolsV1alpha1Claim> results =
          this.search.execute().getClaims();
      List<FactCheckClaim> adapted = new ArrayList<>();
      for (GoogleFactcheckingFactchecktoolsV1alpha1Claim claim : results) {
        adapted.add(new GoogleFactCheckClaimAdapter(claim));
      }
      return adapted;
    } catch (IOException ioe) {
      throw new IllegalStateException(
          "Unable to execute the Google search, received the following exception: "
              + ioe.getMessage());
    }
  }
}
