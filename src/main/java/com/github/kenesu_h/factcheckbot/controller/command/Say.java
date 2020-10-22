package com.github.kenesu_h.factcheckbot.controller.command;

import discord4j.core.object.entity.channel.MessageChannel;

/**
 * A class implementing the {@link BotCommand} interface that represents a command for the bot to
 * send a message.
 */
public class Say extends AbstractBotMsg {

  private final String message;

  /**
   * Constructs a {@link Say} command to send the provided message to the target channel.
   *
   * @param channel the channel
   * @param message the message
   */
  public Say(MessageChannel channel, String message) {
    super(channel);
    this.message = message;
  }

  @Override
  protected String buildMsg() {
    return this.message;
  }
}
