package com.github.kenesu_h.factcheckbot.model;

import java.net.URL;
import java.util.Date;

/**
 * An interface representing a fact check claim with a title, language code (the language of the
 * claim), publisher, textual rating (how factual it's rated), review date, and URL.
 */
public interface FactCheckClaim {

  /**
   * Returns the title of the claim.
   *
   * @return the title
   */
  String getTitle();

  /**
   * Returns the language code of the claim.
   *
   * @return the language code
   */
  String getLanguageCode();

  /**
   * Returns the publisher of the claim.
   *
   * @return the publisher
   */
  String getPublisher();

  /**
   * Returns the textual rating of the claim.
   *
   * @return the textual rating
   */
  String getTextualRating();

  /**
   * Returns the review date of the claim.
   *
   * @return the review date
   */
  Date getReviewDate();

  /**
   * Returns the URL of the claim.
   *
   * @return the URL
   */
  URL getUrl();
}
