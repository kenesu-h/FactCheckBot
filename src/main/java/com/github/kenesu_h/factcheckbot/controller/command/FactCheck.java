package com.github.kenesu_h.factcheckbot.controller.command;

import com.github.kenesu_h.factcheckbot.model.BotModel;
import com.github.kenesu_h.factcheckbot.model.FactCheckClaim;
import com.github.kenesu_h.factcheckbot.view.BotView;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import java.util.List;

/**
 * A class implementing the {@link BotCommand} interface that represents a command to make a fact
 * check query. A Discord embed is sent for every result in the top 3.
 */
public class FactCheck implements BotCommand {

  private final MessageChannel channel;
  private final String query;

  /**
   * Constructs a {@link FactCheck} command to make the provided search query and send the results
   * to the target channel.
   *
   * @param channel the channel
   * @param query the query
   */
  public FactCheck(MessageChannel channel, String query) {
    this.channel = channel;
    this.query = query;
  }

  @Override
  public void execute(BotModel model, BotView view) {
    int maxResults = 3;
    List<FactCheckClaim> results = model.search(this.query);
    this.channel.createMessage("Returning the top " + maxResults + " results:").block();
    int toLoop = Math.min(maxResults, results.size());
    if (results != null) {
      for (int i = 0; i < toLoop; i++) {
        FactCheckClaim claim = results.get(i);

        this.channel.createEmbed(spec ->
            spec.setColor(Color.RED)
                .setTitle(claim.getTitle())
                .setUrl(claim.getUrl().toString())
                .addField("Source", claim.getPublisher(), true)
                .addField("Rating", claim.getTextualRating(), true)
        ).block();
      }
      this.channel.createMessage("Results provided by Google. " + results.size() + " total results.").block();
    } else {
      this.channel.createMessage("No results found, sorry.").block();
    }
  }
}
