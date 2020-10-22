package com.github.kenesu_h.factcheckbot.controller.command;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.view.BotView;
import com.github.kenesu_h.factcheckbot.view.DiscordReader;
import discord4j.core.object.entity.Message;
import java.util.function.Consumer;

/**
 * A class implementing the {@link BotCommand} interface that represents a command to log the
 * Discord client in and begin reading messages from Discord.
 */
public class Login implements BotCommand {

  private final Consumer<Message> callback;
  private final DiscordReader reader;

  /**
   * Constructs a {@link Login} command where the reader is initialized and its callback is set to
   * the provided callback function.
   *
   * @param callback the callback
   * @param reader the reader
   */
  public Login(Consumer<Message> callback, DiscordReader reader) {
    this.callback = callback;
    this.reader = reader;
  }

  @Override
  public void execute(BotModel model, BotView view) {
    model.login();
    this.reader.setClient(model.getClient());
    this.reader.setMsgCallback(this.callback);
    this.reader.initialize();
    view.appendln("Bot successfully logged in.");
  }
}
