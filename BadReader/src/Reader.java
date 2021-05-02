public class Reader implements Runnable
{
  private ReadersPreferenceReadWrite sharedResourceController;
  private String name;

  public Reader(ReadersPreferenceReadWrite sharedResourceController,
      String name)
  {
    this.sharedResourceController = sharedResourceController;
    this.name = name;
  }

  @Override public void run()
  {
    while (true) {
      sharedResourceController.safeRead();
    }
  }
}
