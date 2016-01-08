package se.birchware.chucken.event;

//  https://dl.dropboxusercontent.com/s/h777x7ugherqs0w/forgeevents.html
//  http://www.minecraftforge.net/wiki/Event_Reference

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import se.birchware.chucken.Main;
import se.birchware.chucken.util.Logg;

public class EventsCommon
{
  /*
  @SubscribeEvent
  public void onEntityDrop(LivingDropsEvent event)
  {
    Random random = new Random();
    int dropped = random.nextInt(2) + 1; //DO NOT CHANGE THIS

    if (event.entityLiving instanceof EntityCow) {
      event.entityLiving.entityDropItem(new ItemStack(Items.diamond_shovel),dropped);
      event.setCanceled(true);
    }
  }
  */

  @SubscribeEvent  // Server
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    Logg.info("Chucken EventsCommon::onPlayerLoggedIn: " + event.player.getDisplayName().getUnformattedText() + " loggade in.");
    if (Main.player == null) {
      Main.player = event.player;
      Logg.info("Chucken EventsCommon::onPlayerLoggedIn SET USER");
    }
  }

}
