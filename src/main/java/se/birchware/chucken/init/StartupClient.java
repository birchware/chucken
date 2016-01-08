package se.birchware.chucken.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClient
{
  public static void preInitClientOnly() {
    //Logg.info("preInitClientOnly");
  }

  public static void initClientOnly() {
    final int DEFAULT_ITEM_SUBTYPE = 0;

    //    EventsClient handler = new EventsClient();
    //    MinecraftForge.EVENT_BUS.register(handler);
    //    FMLCommonHandler.instance().bus().register(handler);

    // *** BLOCKS
    // This is currently necessary in order to make your block render properly when it is an item (i.e. in the inventory
    //   or in your hand or thrown on the ground).
    // Minecraft knows to look for the item model based on the GameRegistry.registerBlock.  However the registration of
    //  the model for each item is normally done by RenderItem.registerItems(), and this is not currently aware
    //   of any extra items you have created.  Hence you have to do it manually.  This will probably change in future.
    // It must be done in the init phase, not preinit, and must be done on client only.
    Item itemBlockChuck = GameRegistry.findItem("chucken","block_chuck");

    RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    renderItem.getItemModelMesher().register(itemBlockChuck,DEFAULT_ITEM_SUBTYPE,new ModelResourceLocation("chucken:block_chuck","inventory"));


  }

  public static void postInitClientOnly() {
    //Logg.info("postIinitClientOnly");
  }
}
