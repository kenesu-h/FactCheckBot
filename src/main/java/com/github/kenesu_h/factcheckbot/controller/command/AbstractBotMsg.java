package com.github.kenesu_h.factcheckbot.controller.command;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.view.BotView;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * An abstract class implementing the {@link BotCommand} interface that represents a message to be
 * sent. This message must be built with the buildMsg method.
 */
public abstract class AbstractBotMsg implements BotCommand {

  private final MessageChannel channel;

  /**
   * Constructs an {@link AbstractBotMsg} to be sent to the provided channel.
   *
   * @param channel the channel
   */
  protected AbstractBotMsg(MessageChannel channel) {
    this.channel = channel;
  }

  /**
   * Builds the message to be sent.
   *
   * @return the message
   */
  protected abstract String buildMsg();

  @Override
  public void execute(BotModel model, BotView view) {
    this.channel.createMessage(buildMsg()).block();
  }
}
