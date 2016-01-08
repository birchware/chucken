package se.birchware.chucken.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.birchware.chucken.Main;
import se.birchware.chucken.block.TileChuck;

import java.util.*;

public class ChunkManager implements ForgeChunkManager.LoadingCallback
{
  public static final ChunkManager instance = new ChunkManager();

  private final HashMap<WorldLocation,ForgeChunkManager.Ticket> tickets = new HashMap();

  //private ChunkManager() {
  public ChunkManager() {
    Logg.info("***ChunkManager()");
    //ForgeChunkManager.setForcedChunkLoadingCallback(Main.instance,this);
    MinecraftForge.EVENT_BUS.register(this);
  }

  @SubscribeEvent
  public void unloadWorld(WorldEvent.Unload evt) {
    Logg.info("***ChunkManager::unloadWorld");
    Iterator<WorldLocation> it = tickets.keySet().iterator();
    while (it.hasNext()) {
      WorldLocation loc = it.next();
      if (loc.dimensionID == evt.world.provider.getDimensionId())
        it.remove();
    }
  }

  /*
  Called back when tickets are loaded from the world to allow the mod to decide if it wants the ticket still,
  and prioritise overflow based on the ticket count.
  WARNING: You cannot force chunks in this callback, it is strictly for allowing the mod to be more selective
  in which tickets it wishes to preserve in an overflow situation
  */
  @Override
  public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets,World world) {
    Logg.info("***ChunkManager::ticketsLoaded");
    for (ForgeChunkManager.Ticket ticket : tickets) {
      NBTTagCompound nbt = ticket.getModData();
      int x = nbt.getInteger("tileX");
      int y = nbt.getInteger("tileY");
      int z = nbt.getInteger("tileZ");
      TileEntity tile = world.getTileEntity(new BlockPos(x,y,z));
      if (tile instanceof TileChuck) {
        TileChuck tile_chuck = (TileChuck)tile;
        WorldLocation loc = new WorldLocation((TileChuck)tile);
        this.forceTicketChunks(ticket,tile_chuck.getChunksToLoad());
        this.cacheTicket(loc, ticket);
      }
      else {
        ForgeChunkManager.releaseTicket(ticket);
      }
    }
  }

  private void cacheTicket(WorldLocation loc, ForgeChunkManager.Ticket ticket) {
    tickets.put(loc, ticket);
  }

  public void unloadChunks(TileEntity te) {
    Logg.info("***ChunkManager::unloadChunks tile");
    this.unloadChunks(new WorldLocation((TileChuck)te));
  }

  public void unloadChunks(World world,int x,int y,int z) {
    Logg.info("***ChunkManager::unloadChunks xyz");
    this.unloadChunks(new WorldLocation(world,x,y,z));
  }

  public void unloadChunks(WorldLocation loc) {
    Logg.info("***ChunkManager::unloadChunks loc");
    ForgeChunkManager.Ticket ticket = tickets.remove(loc);
    //ReikaJavaLibrary.pConsole("Unloading "+ticket+" with "+ticket.getChunkList());
    ForgeChunkManager.releaseTicket(ticket);
  }

  public ForgeChunkManager.Ticket loadChunks(WorldLocation loc,Collection<ChunkCoordIntPair> chunks) {
    Logg.info("***ChunkManager::loadChunks loc");
    ForgeChunkManager.Ticket ticket = tickets.get(loc);
    if (ticket == null) {
      ticket = this.getNewTicket(loc);
      this.cacheTicket(loc, ticket);
    }
    this.forceTicketChunks(ticket, chunks);
    return ticket;
  }

  //public void loadChunks(ChunkLoadingTile te) {
  public void loadChunks(TileChuck te) {
    //Logg.info("***ChunkManager::loadChunks tile " + te.getPlayer());
    Logg.info("***ChunkManager::loadChunks tile");
    WorldLocation loc = new WorldLocation((TileChuck)te);
    this.loadChunks(loc,te.getChunksToLoad());
  }

  private ForgeChunkManager.Ticket getNewTicket(WorldLocation loc) {
    Logg.info("***ChunkManager::getNewTicket");
    ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(Main.instance,loc.getWorld(),ForgeChunkManager.Type.NORMAL);
    //ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(Main.instance,loc.getWorld(),ForgeChunkManager.Type.ENTITY);

    //if (ticket != null) ticket.bindEntity(Main.player);

    //Logg.info("***ChunkManager::getNewTicket moddata " + ticket.getModData().toString());

    //Logg.info("***ChunkManager XXX " + MinecraftServer.getServer().getDisplayName().getUnformattedText());


/*
    String b = ticket.isPlayerTicket() ? "true" : "false";
    String c = ticket.getPlayerName() == null ? "null" : ticket.getPlayerName();
    Logg.info("***ChunkManager::getNewTicket " + b + " " + c);
*/



    NBTTagCompound nbt = ticket.getModData();
    nbt.setInteger("tileX",loc.xCoord);
    nbt.setInteger("tileY",loc.yCoord);
    nbt.setInteger("tileZ",loc.zCoord);
    return ticket;
  }

  private void forceTicketChunks(ForgeChunkManager.Ticket ticket,Collection<ChunkCoordIntPair> chunks) {
    Logg.info("***ChunkManager::forceTicketChunks");
    ImmutableSet<ChunkCoordIntPair> ticketChunks = ticket.getChunkList();
    //ReikaJavaLibrary.pConsole("Parsing ticket "+ticket+", world="+ticket.world+", mod="+ticket.getModId()+", chunks="+chunks);
    for (ChunkCoordIntPair coord : ticketChunks) {
      if (!chunks.contains(coord)) {
        //ReikaJavaLibrary.pConsole("Unforcing chunk "+coord.chunkXPos+", "+coord.chunkZPos);
        ForgeChunkManager.unforceChunk(ticket, coord);
      }
    }
    for (ChunkCoordIntPair coord : chunks) {
      if (!ticketChunks.contains(coord)) {
        //ReikaJavaLibrary.pConsole("Forcing chunk "+coord.chunkXPos+", "+coord.chunkZPos);
        ForgeChunkManager.forceChunk(ticket, coord);
      }
    }
  }

  public static Collection<ChunkCoordIntPair> getChunkSquare(int x,int z,int radie) {
    Logg.info("***ChunkManager::getChunkSquare XZ " + x + " " + z + " radie: " + radie);
    int x2 = x >> 4;
    int z2 = z >> 4;
    Collection<ChunkCoordIntPair> chunkList = new ArrayList();
    //for (int i = -radie; i <= radie; i++) {
    //  for (int j = -radie; j <= radie; j++) {
    for (int i = 1-radie; i < radie; i++) {
      for (int j = 1-radie; j < radie; j++) {
        //Logg.info("***ChunkManager::getChunkSquare ij " + i + " " + j);
        chunkList.add(new ChunkCoordIntPair(x2 + i,z2 + j));
      }
    }
    return chunkList;
  }

  @Override
  public String toString() {
    Logg.info("***ChunkManager::toString");
    return tickets.toString();
  }
}
