package se.birchware.chucken.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.birchware.chucken.block.BlockChuck;
import se.birchware.chucken.event.EventsCommon;
import se.birchware.chucken.util.Ref;

public class StartupCommon
{
  //  These hold the unique instances of your blocks and items.
  public static BlockChuck blockChuck;

  public static void preInitCommon() {
    // *** BLOCKS
    // each instance of your block should have a name that is unique within your mod.  use lower case.
    //blockCPU = (BlockCPU)(new BlockCPU().setUnlocalizedName("block_cpu"));
    blockChuck = new BlockChuck(); GameRegistry.registerBlock(blockChuck,Ref.BLOCK_CHUCK);
  }

  public static void initCommon() {
    //Logg.info("initClommon");
    EventsCommon handler = new EventsCommon();
    MinecraftForge.EVENT_BUS.register(handler);
    FMLCommonHandler.instance().bus().register(handler);
    GameRegistry.addRecipe(new ItemStack(blockChuck,1),new Object[]{"   "," O ","   ",'O',Blocks.obsidian});
  }

  public static void postInitCommon() {
    //Logg.info("postIinitCommon");
  }
}
