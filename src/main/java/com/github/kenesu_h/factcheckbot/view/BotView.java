package com.github.kenesu_h.factcheckbot.view;

import java.util.Properties;
import java.util.function.Consumer;

/**
 * An interface representing a view for the bot, which serves as a text or UI-based way to command
 * the bot to perform actions such as logging in and out.
 */
public interface BotView {

  /**
   * Initializes the view in order to begin accepting inputs.
   *
   * @throws IllegalStateException if the view's input stream is closed
   */
  void initialize();

  /**
   * Closes the view's input stream.
   */
  void close();

  /**
   * Sets the callback of this view to the provided callback function.
   *
   * @param callback the callback
   */
  void setCallback(Consumer<String> callback);

  /**
   * Appends a string to this view's output.
   *
   * @param str the string
   * @throws IllegalStateException if the view's output cannot be appended to
   */
  void append(String str);

  /**
   * Appends a string to this view's output with a new line at the end.
   *
   * @param str the string
   * @throws IllegalStateException if the view's output cannot be appended to
   */
  void appendln(String str);

  /**
   * Opens a prompt in the view asking the user to create a config file if one doesn't already
   * exist.
   *
   * @param prop the properties instance
   */
  void promptNewConfig(Properties prop);
}
