package com.github.kenesu_h.factcheckbot.controller;

import com.github.kenesu_h.factcheckbot.controller.command.BotCommand;
import com.github.kenesu_h.factcheckbot.controller.command.FactCheck;
import com.github.kenesu_h.factcheckbot.controller.command.Login;
import com.github.kenesu_h.factcheckbot.controller.command.Logout;
import com.github.kenesu_h.factcheckbot.controller.command.Say;
import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.view.BotView;
import com.github.kenesu_h.factcheckbot.view.DiscordReader;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * An implementation of the {@link BotController} interface as defined by the interface itself.
 */
public class BotControllerImpl implements BotController {

  private final String prefix;
  private final BotModel model;
  private final BotView view;
  private final DiscordReader reader;

  /**
   * Constructs a {@link BotControllerImpl} that accepts commands starting with the provided prefix,
   * and is associated with the model, view, and Discord reader.
   *
   * @param prefix the prefix
   * @param model the model
   * @param view the view
   * @param reader the Discord reader
   */
  public BotControllerImpl(String prefix, BotModel model, BotView view, DiscordReader reader) {
    this.prefix = prefix;
    this.model = model;
    this.view = view;
    this.reader = reader;
  }

  @Override
  public void initialize() {
    this.view.setCallback(this::accept);
    this.view.initialize();
  }

  @Override
  public void accept(String input) {
    try {
      Scanner s = new Scanner(input);
      BotCommand cmd;

      while (s.hasNext()) {
        String in = s.next().toLowerCase();

        switch (in) {
          case "login":
            cmd = new Login(this::acceptMsg, this.reader);
            break;
          case "logout":
            cmd = new Logout();
            break;
          default:
            throw new IllegalArgumentException("Unsupported command \"" + in + "\" provided");
        }
        cmd.execute(this.model, this.view);
      }
    } catch (IllegalArgumentException iae) {
      view.appendln(iae.getMessage());
    } catch (NoSuchElementException nsee) {
      view.appendln("Not enough arguments were given.");
    } catch (IllegalStateException ise) {
      view.appendln("Bot is no longer accepting inputs.");
    }
  }

  @Override
  public void acceptMsg(Message msg) {
    MessageChannel channel = msg.getChannel().block();
    try {
      if (msg.getContent().startsWith(this.prefix)) {
        Scanner s = new Scanner(msg.getContent().substring(1));
        BotCommand cmd = null;

        while (s.hasNext()) {
          String in = s.next().toLowerCase();

          switch (in) {
            case "fact-check":
              StringBuilder rest = new StringBuilder();
              while (s.hasNext()) {
                rest.append(s.next()).append(" ");
              }
              cmd = new FactCheck(channel, rest.toString());
              break;
            default:
              // Ignore any invalid commands
              break;
          }
          if (cmd != null) {
            cmd.execute(this.model, this.view);
          }
        }
      }
    } catch (IllegalArgumentException iae) {
      new Say(channel, iae.getMessage()).execute(this.model, this.view);
    } catch (NoSuchElementException nsee) {
      new Say(channel, "Not enough arguments were given.")
          .execute(this.model, this.view);
    } catch (IllegalStateException ise) {
      new Say(channel, "Not enough arguments were given.")
          .execute(this.model, this.view);
    }
  }
}
