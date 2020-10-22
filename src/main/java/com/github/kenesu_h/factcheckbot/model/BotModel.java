package com.github.kenesu_h.factcheckbot.model;

import discord4j.core.GatewayDiscordClient;
import java.util.List;

/**
 * An interface representing a model for the bot, which contains the Discord and fact checking
 * clients. The Discord client of the model is assigned to a
 * {@link com.github.kenesu_h.factcheckbot.view.DiscordReader} in order to create message listeners,
 * where as the fact checking client is used for search queries.
 */
public interface BotModel {

  /**
   * Returns the Discord client associated with this model.
   *
   * @return the Discord client
   */
  GatewayDiscordClient getClient();

  /**
   * Logs the bot into Discord.
   */
  void login();

  /**
   * Logs the bot out of Discord.
   */
  void logout();

  /**
   * Searches using the fact checking client and returns the list of associated claims.
   *
   * @param query the query
   * @return the list of claims
   */
  List<FactCheckClaim> search(String query);
}
