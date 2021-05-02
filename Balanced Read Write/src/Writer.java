public class Writer implements Runnable
{
  private BalancedReadWrite shareResourceController;
  private String name;

  public Writer(BalancedReadWrite shareResourceController, String name)
  {
    this.shareResourceController = shareResourceController;
    this.name = name;
  }

  @Override public void run()
  {
    while (true) {
      System.out.println(name + " wants write access");
      shareResourceController.acquireWrite();
      shareResourceController.write();
      System.out.println(name + " changed the variable to " + shareResourceController.read());
      shareResourceController.releaseWrite();
      System.out.println(name + " released write access");
    }
  }
}
