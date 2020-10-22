package com.github.kenesu_h.factcheckbot.view;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import java.util.function.Consumer;

public class DiscordReaderImpl implements DiscordReader {

  private GatewayDiscordClient client;
  private Consumer<Message> msgCallback;

  public DiscordReaderImpl() {}

  @Override
  public void initialize() {
    this.client.getEventDispatcher().on(MessageCreateEvent.class)
        .map(MessageCreateEvent::getMessage)
        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
        .subscribe(message -> this.msgCallback.accept(message));
  }

  @Override
  public void setClient(GatewayDiscordClient client) {
    this.client = client;
  }

  @Override
  public void setMsgCallback(Consumer<Message> callback) {
    this.msgCallback = callback;
  }
}
