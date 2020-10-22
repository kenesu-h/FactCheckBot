package com.github.kenesu_h.factcheckbot.controller.command;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.view.BotView;

/**
 * A class implementing the {@link BotCommand} interface that represents a command to log the
 * Discord client out and close the view..
 */
public class Logout implements BotCommand {

  /**
   * Constructs a {@link Logout} command.
   */
  public Logout() {}

  @Override
  public void execute(BotModel model, BotView view) {
    model.logout();
    view.close();
    view.appendln("Bot successfully logged out.");
  }
}
