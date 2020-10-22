package com.github.kenesu_h.factcheckbot.controller;

import discord4j.core.object.entity.Message;

/**
 * An interface representing a controller for the bot. The controller handles user input from a
 * {@link com.github.kenesu_h.factcheckbot.view.BotView} such as a command line and any incoming
 * Discord messages relayed by a {@link com.github.kenesu_h.factcheckbot.view.DiscordReader}.
 */
public interface BotController {

  /**
   * Initializes the controller, initializing the associated view and setting the its callback to
   * the controller's accept method.
   */
  void initialize();

  /**
   * Accepts a text-based command and executes a corresponding command if one exists.
   *
   * @param command the command
   */
  void accept(String command);

  /**
   * Accepts a Discord message and executes a corresponding command if one exists.
   *
   * @param msg the Discord message
   */
  void acceptMsg(Message msg);
}
