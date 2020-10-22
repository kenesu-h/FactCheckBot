package com.github.kenesu_h.factcheckbot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link FactCheckSearch} interface as defined by the interface itself.
 */
public class FactCheckSearchImpl implements FactCheckSearch {

  private String query;
  private List<FactCheckClaim> claims;

  /**
   * Constructs a {@link FactCheckClaimImpl} without a query, but a list of claims.
   *
   * @param claims the claims
   */
  public FactCheckSearchImpl(List<FactCheckClaim> claims) {
    this("", claims);
  }

  /**
   * Constructs a {@link FactCheckClaimImpl} with the provided query and claims.
   *
   * @param query the query
   * @param claims the claims
   */
  public FactCheckSearchImpl(String query, List<FactCheckClaim> claims) {
    this.query = query;
    this.claims = claims;
  }

  @Override
  public void setApiKey(String apiKey) {
    throw new IllegalArgumentException("Cannot set an API key for a local search.");
  }

  @Override
  public void setQuery(String query) {
    this.query = query;
  }

  @Override
  public List<FactCheckClaim> execute() {
    // TODO: Optimize the search algorithm eventually, the Google fact search is the main method for
    //  searches anyway.
    List<FactCheckClaim> results = new ArrayList<>();
    for (FactCheckClaim claim : this.claims) {
      if (claim.getTitle().equals(this.query)) {
        results.add(claim);
      }
    }
    return results;
  }
}
