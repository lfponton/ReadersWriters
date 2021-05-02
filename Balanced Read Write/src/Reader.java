public class Reader implements Runnable
{
  private BalancedReadWrite sharedResourceController;
  private String name;

  public Reader(BalancedReadWrite sharedResourceController,
      String name)
  {
    this.sharedResourceController = sharedResourceController;
    this.name = name;
  }

  @Override public void run()
  {
    while (true) {
      System.out.println(name + " wants read access");
      sharedResourceController.acquireRead();
      int i = sharedResourceController.read();
      System.out.println(name + " read " + i);
      sharedResourceController.releaseRead();
      System.out.println(name + " released read access");
    }
  }
}
