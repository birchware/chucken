package se.birchware.chucken.proxy;

public class ClientProxy extends CommonProxy
{
  public void preInit() {
    super.preInit();
    se.birchware.chucken.init.StartupClient.preInitClientOnly();
  }

  public void init() {
    super.init();
    se.birchware.chucken.init.StartupClient.initClientOnly();
  }

  public void postInit() {
    super.postInit();
    se.birchware.chucken.init.StartupClient.postInitClientOnly();
  }
}
