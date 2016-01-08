package se.birchware.chucken.util;

import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import se.birchware.chucken.block.TileChuck;

import java.lang.ref.WeakReference;
import java.util.Random;

public class WorldLocation
{
  private static final Random rand = new Random();

  public int xCoord = 0;
  public int yCoord = 0;
  public int zCoord = 0;
  public int dimensionID = 0;

  private boolean isRemote = false;

  private WeakReference<World> clientWorld;

  public WorldLocation(World world,int x,int y,int z) {
    this(world.provider.getDimensionId(),x,y,z);
    Logg.info("***WorldLocation() world xyz");
    if (world.isRemote) {
      isRemote = true;
      clientWorld = new WeakReference(world);
    }
  }

  public WorldLocation(int dim,int x,int y,int z) {
    Logg.info("***WorldLocation() dim xyz");
    xCoord = x;
    yCoord = y;
    zCoord = z;
    dimensionID = dim;
  }

  public WorldLocation(TileChuck te) {
    this(te.getWorld(), te.xCoord, te.yCoord, te.zCoord);
    Logg.info("***WorldLocation() tile");
  }

  public World getWorld() {
    Logg.info("***WorldLocation::getWorld");
    return isRemote && clientWorld.get() != null ? clientWorld.get() : DimensionManager.getWorld(dimensionID);
  }

}
