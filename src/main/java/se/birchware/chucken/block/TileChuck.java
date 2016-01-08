package se.birchware.chucken.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import se.birchware.chucken.util.ChunkManager;
import se.birchware.chucken.util.Logg;

import java.util.Collection;

public class TileChuck extends TileEntity
{
  private boolean loaded = false;
  private final int radie = 2;

  public EntityPlayer player;
  public int xCoord;
  public int yCoord;
  public int zCoord;

  public TileChuck() {
    Logg.info("***TileChuck()");
  }

/*
  public void setPlayer(EntityPlayer entity_player) {
    Logg.info("***TileChuck::setPlayer " + entity_player.getDisplayName().getUnformattedText());
    this.player = entity_player;
  }

  public EntityPlayer getPlayer() {
    Logg.info("***TileChuck::getPlayer");
    //Logg.info("***TileChuck::getPlayer " + this.player.getDisplayName().getUnformattedText());
    return this.player;
  }
*/

  @Override
  public void onLoad() {
    BlockPos blockpos = getPos();
    xCoord = blockpos.getX();
    yCoord = blockpos.getY();
    zCoord = blockpos.getZ();
    Logg.info("***TileChuck::onLoad - pos = " + xCoord + " "  + yCoord + " "  + zCoord);
    load();
  }

  @Override
  public void onChunkUnload() {
    Logg.info("***TileChuck::onChunkUnload");
    unload();
  }

  private void load() {
    if (loaded) {
      Logg.info("***TileChuck::load - was/is loaded");
      return;
    }
    Logg.info("***TileChuck::load - was not loaded");
    loaded = true;
    ChunkManager.instance.loadChunks(this);
  }

  private void unload() {
    if (loaded) {
      Logg.info("***TileChuck::unload - was loaded");
      loaded = false;
      ChunkManager.instance.unloadChunks(this);
    }
    else {
      Logg.info("***TileChuck::unload - was/is not loaded");
    }
  }
  public Collection<ChunkCoordIntPair> getChunksToLoad() {
    Logg.info("***TileChuck::getChunksToLoad");
    return ChunkManager.getChunkSquare(xCoord,zCoord,this.radie);
  }

/*
  public void onBlockPlacedBy(EntityLivingBase entity,ItemStack stack) {
    if (entity instanceof EntityPlayer) {
      player = ((EntityPlayer)entity);
      Logg.info("***TileChuck::onBlockPlacedBy " + player.getName());
    }
  }
*/


/*
  public void breakBlock() {
    if (!worldObj.isRemote)
      this.unload();
  }
*/

}
