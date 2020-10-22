package com.github.kenesu_h.factcheckbot.model;

import java.util.List;

/**
 * An interface representing a client for a fact check search. An example of this would be Google's
 * Fact Check Explorer.
 */
public interface FactCheckSearch {

  /**
   * Sets the API key of this search client
   *
   * @param apiKey the API key
   * @throws IllegalArgumentException if the search client doesn't need an API key
   */
  void setApiKey(String apiKey);

  /**
   * Sets the query of this search client
   *
   * @param query the query
   */
  void setQuery(String query);

  /**
   * Executes the search with the current query and returns the results as a list of claims.
   *
   * @return the results
   */
  List<FactCheckClaim> execute();
}
