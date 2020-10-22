package com.github.kenesu_h.factcheckbot;

import com.github.kenesu_h.factcheckbot.controller.BotController;
import com.github.kenesu_h.factcheckbot.controller.BotControllerImpl;
import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.model.BotModelImpl;
import com.github.kenesu_h.factcheckbot.model.GoogleFactCheckSearchAdapter;
import com.github.kenesu_h.factcheckbot.view.BotCLIView;
import com.github.kenesu_h.factcheckbot.view.BotView;
import com.github.kenesu_h.factcheckbot.view.DiscordReader;
import com.github.kenesu_h.factcheckbot.view.DiscordReaderImpl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.factchecktools.v1alpha1.FactCheckTools;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * The primary class for the bot. This class constructs the tools needed for Google's fact checking
 * as well as the model, controller, and view from a config file if one exists. If it doesn't exist,
 * the user is prompted to create a new config and provide their own Discord bot token and Google
 * API key.
 */
public class FactCheckBot {

  private static String prefix;
  private static String discordToken;
  private static String googleApiKey;

  private static final String APPLICATION_NAME = "FactCheckBot";
  private static final HttpTransport httpTransport = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final FactCheckTools tools =
      new FactCheckTools.Builder(httpTransport, JSON_FACTORY, null)
          .setApplicationName(APPLICATION_NAME)
          .build();

  private static BotModel model;
  private static BotView view;
  private static final DiscordReader reader = new DiscordReaderImpl();
  private static BotController controller;

  // Constructs the Google fact checking tools as well as model, controller, and view, then
  // initializes them.
  public static void main(String[] args) {
    view = new BotCLIView(new InputStreamReader(System.in), System.out);
    readProperties();

    model = new BotModelImpl(discordToken, new GoogleFactCheckSearchAdapter(tools), googleApiKey);
    controller = new BotControllerImpl(prefix, model, view, reader);
    controller.initialize();
  }

  // Reads existing configurations from a config file, but prompts the user to create one if it
  // doesn't exist.
  // Below function based off: https://stackoverflow.com/a/27506370
  private static void readProperties() {
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream("config.properties");
      prop.load(input);
      prefix = prop.getProperty("prefix");
      discordToken = prop.getProperty("discordToken");
      googleApiKey = prop.getProperty("googleApiKey");
    } catch (FileNotFoundException fnfe) {
      view.promptNewConfig(prop);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (input != null) {
          input.close();
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
}
