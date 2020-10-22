package com.github.kenesu_h.factcheckbot.controller.command;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.view.BotView;

/**
 * An interface representing a command for a
 * {@link com.github.kenesu_h.factcheckbot.controller.BotController} that can be executed on a
 * {@link BotModel} and {@link BotView}.
 */
public interface BotCommand {

  /**
   * Executes this command on the provided model and view.
   *
   * @param model the model
   * @param view the view
   */
  void execute(BotModel model, BotView view);
}
