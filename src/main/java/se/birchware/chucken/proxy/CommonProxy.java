package se.birchware.chucken.proxy;

public abstract class CommonProxy
{
  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit() {
    se.birchware.chucken.init.StartupCommon.preInitCommon();
  }

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init() {
    se.birchware.chucken.init.StartupCommon.initCommon();
  }

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit() {
    se.birchware.chucken.init.StartupCommon.postInitCommon();
  }
}
