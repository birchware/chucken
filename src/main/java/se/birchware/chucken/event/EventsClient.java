package se.birchware.chucken.event;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventsClient
{
  @SubscribeEvent  // Client
  public void onChatReceived(ClientChatReceivedEvent event) {
    //
  }
}
