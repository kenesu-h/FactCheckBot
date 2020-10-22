package com.github.kenesu_h.factcheckbot.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A command line interface implementation of the {@link BotView} interface. Like other CLIs, this
 * view accepts text input and relays them to a
 * {@link com.github.kenesu_h.factcheckbot.controller.BotController} via its callback.
 */
public class BotCLIView implements BotView {

  private final Scanner scanner;
  private final Appendable output;
  private Consumer<String> callback;

  /**
   * Constructs a {@link BotCLIView} with the provided input and output streams.
   *
   * @param input the input
   * @param output the output
   */
  public BotCLIView(Readable input, Appendable output) {
    this.scanner = new Scanner(input);
    this.output = output;
  }

  @Override
  public void initialize() {
    try {
      this.appendln("View initialized, awaiting login.");
      while (this.scanner.hasNextLine()) {
        this.callback.accept(this.scanner.nextLine());
      }
    } catch (IllegalStateException ise) {
      this.append("Bot is no longer accepting inputs.");
    }
  }

  @Override
  public void close() {
    this.scanner.close();
  }

  @Override
  public void setCallback(Consumer<String> callback) {
    this.callback = callback;
  }

  @Override
  public void append(String str) {
    try {
      DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      Date date = new Date();
      this.output.append(dateFormat.format(date) + ": " + str);
    } catch (IOException ioe) {
      throw new IllegalStateException(
          "Unable to append to output, received the following exception: " + ioe.getMessage());
    }
  }

  @Override
  public void appendln(String str) {
    this.append(str + "\n");
  }

  @Override
  public void promptNewConfig(Properties prop) {
    OutputStream output = null;
    try {
      this.appendln(
          "config.properties is missing. Would you like to create a fresh config file?");
      while (this.scanner.hasNextLine()) {
        String in = this.scanner.next().toLowerCase();
        switch (in) {
          case "yes":
            try {
              output = new FileOutputStream("config.properties");

              prop.setProperty("prefix", "^");
              prop.setProperty("discordToken", "");
              prop.setProperty("googleApiKey", "");
              prop.store(output, null);
              this.appendln(
                  "Config file created, please set config values to your Discord bot token and "
                      + "Google API key respectively, then relaunch the bot.");
            } catch (FileNotFoundException fnfe) {
              fnfe.printStackTrace();
            } catch (IOException ioe) {
              ioe.printStackTrace();
            } finally {
              try {
                if (output != null) {
                  output.close();
                }
              } catch (IOException ioe) {
                ioe.printStackTrace();
              }
            }
            break;
          case "no":
            this.appendln("Exiting, cannot launch bot without config.");
            break;
          default:
            this.appendln("Please enter either \"yes\" or \"no\".");
        }
      }
    } catch (IllegalStateException ise) {
      this.append("Output is no longer accepting inputs.");
    }
  }
}
