package com.github.kenesu_h.factcheckbot.model;

import com.google.api.services.factchecktools.v1alpha1.model
    .GoogleFactcheckingFactchecktoolsV1alpha1Claim;
import com.google.api.services.factchecktools.v1alpha1.model
    .GoogleFactcheckingFactchecktoolsV1alpha1ClaimReview;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class adapting Google's claim format to the {@link FactCheckClaim} interface.
 */
public class GoogleFactCheckClaimAdapter implements FactCheckClaim {

  private final GoogleFactcheckingFactchecktoolsV1alpha1Claim claim;
  private final GoogleFactcheckingFactchecktoolsV1alpha1ClaimReview review;

  /**
   * Constructs a {@link GoogleFactCheckClaimAdapter} from a provided Google claim.
   *
   * @param claim the Google claim
   */
  public GoogleFactCheckClaimAdapter(GoogleFactcheckingFactchecktoolsV1alpha1Claim claim) {
    this.claim = claim;
    this.review = claim.getClaimReview().get(0);
  }

  @Override
  public String getTitle() {
    return this.review.getTitle();
  }

  public String getDescription() {
    return this.claim.getText();
  }

  @Override
  public String getLanguageCode() {
    return this.review.getLanguageCode();
  }

  @Override
  public String getPublisher() {
    return this.review.getPublisher().getName();
  }

  @Override
  public String getTextualRating() {
    return this.review.getTextualRating();
  }

  @Override
  public Date getReviewDate() {
    try {
      // Below code is from: https://stackoverflow.com/a/50058068
      String pattern = "yyyy-MM-dd'T'HH:mm:ssX";
      DateFormat df = new SimpleDateFormat(pattern);
      return df.parse(this.review.getReviewDate());
    } catch (ParseException pe) {
      throw new IllegalArgumentException(
          "Unable to parse date of Google claim format, received the following exception: "
              + pe.getMessage());
    }
  }

  @Override
  public URL getUrl() {
    try {
      return new URL(this.review.getUrl());
    } catch (MalformedURLException mue) {
      throw new IllegalArgumentException(
          "Unable to parse URL of Google claim format, received the following exception: "
              + mue.getMessage());
    }
  }
}
