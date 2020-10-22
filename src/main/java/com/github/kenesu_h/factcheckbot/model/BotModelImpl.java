package com.github.kenesu_h.factcheckbot.model;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import java.util.List;

/**
 * An implementation of the {@link BotModel} interface as defined by the interface itself.
 */
public class BotModelImpl implements BotModel {

  private GatewayDiscordClient client;
  private final String token;
  private final FactCheckSearch search;

  /**
   * Constructs a {@link BotModelImpl}, setting the Discord client's token, search client, and its
   * API key respectively.
   *
   * @param token the Discord client token
   * @param search the search client
   * @param apiKey the search client API key
   */
  public BotModelImpl(String token, FactCheckSearch search, String apiKey) {
    this.token = token;
    this.search = search;
    this.search.setApiKey(apiKey);
  }

  @Override
  public GatewayDiscordClient getClient() {
    return this.client;
  }

  @Override
  public void login() {
    this.client = DiscordClientBuilder.create(this.token)
        .build()
        .login()
        .block();
  }

  @Override
  public void logout() {
    this.client.logout().block();
  }

  @Override
  public List<FactCheckClaim> search(String query) {
    this.search.setQuery(query);
    return this.search.execute();
  }
}
