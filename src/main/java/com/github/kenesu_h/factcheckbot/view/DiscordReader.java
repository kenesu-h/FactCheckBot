package com.github.kenesu_h.factcheckbot.view;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import java.util.function.Consumer;

/**
 * An interface representing a reader of the bot's Discord messages. This reader serves as a way to
 * assign message listeners for the model's client, which then relays user input to a controller via
 * its callback.
 */
public interface DiscordReader {

  /**
   * Initializes this reader by creating a message listener only for messages not coming from other
   * bots.
   */
  void initialize();

  /**
   * Sets the Discord client of this reader to the provided client.
   *
   * @param client the client
   */
  void setClient(GatewayDiscordClient client);

  /**
   * Sets the callback of this reader to the provided callback function
   *
   * @param callback the callback
   */
  void setMsgCallback(Consumer<Message> callback);
}
