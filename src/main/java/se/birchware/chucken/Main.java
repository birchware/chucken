package se.birchware.chucken;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.birchware.chucken.block.TileChuck;
import se.birchware.chucken.proxy.CommonProxy;
import se.birchware.chucken.util.ChunkManager;
import se.birchware.chucken.util.Ref;

@Mod(modid = Ref.MODID,name = Ref.MODNAME,version = Ref.VERSION)
public class Main
{
  @Mod.Instance(Ref.MODID)
  public static Main instance;

  @SidedProxy(clientSide = Ref.CLIENT_PROXY,serverSide = Ref.SERVER_PROXY)
  public static CommonProxy proxy;

  public static EntityPlayer player;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit();
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.init();
    GameRegistry.registerTileEntity(TileChuck.class,"tile_chuck");
    //ForgeChunkManager.setForcedChunkLoadingCallback(this,ChunkManager.instance);
    ForgeChunkManager.setForcedChunkLoadingCallback(this,new ChunkManager());
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.postInit();
  }

}
