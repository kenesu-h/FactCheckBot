package com.github.kenesu_h.factcheckbot.model;

import java.net.URL;
import java.util.Date;

/**
 * An implementation of the {@link FactCheckClaim} interface as defined by the interface itself.
 */
public class FactCheckClaimImpl implements FactCheckClaim {

  private final String title;
  private final String languageCode;
  private final String publisher;
  private final String textualRating;
  private final Date reviewDate;
  private final String description;
  private final URL url;

  /**
   * Constructs a {@link FactCheckClaimImpl} with the provided title, language code, publisher,
   * textual rating, review date, description, and url.
   *
   * @param title the title
   * @param languageCode the language code
   * @param publisher the publisher
   * @param textualRating the textual rating
   * @param reviewDate the review date
   * @param description the description
   * @param url the URL
   */
  public FactCheckClaimImpl(String title, String languageCode, String publisher,
      String textualRating, Date reviewDate, String description, URL url) {
    this.title = title;
    this.languageCode = languageCode;
    this.publisher = publisher;
    this.textualRating = textualRating;
    this.reviewDate = reviewDate;
    this.description = description;
    this.url = url;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public String getLanguageCode() {
    return this.languageCode;
  }

  @Override
  public String getPublisher() {
    return this.publisher;
  }

  @Override
  public String getTextualRating() {
    return this.textualRating;
  }

  @Override
  public Date getReviewDate() {
    return this.reviewDate;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public URL getUrl() {
    return this.url;
  }
}
