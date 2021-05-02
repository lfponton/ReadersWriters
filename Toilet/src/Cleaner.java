public class Cleaner implements Runnable
{
  PublicToilet publicToilet;
  String name;

  public Cleaner(PublicToilet publicToilet, String name)
  {
    this.publicToilet = publicToilet;
    this.name = name;
  }

  @Override public void run()
  {
    System.out.println(name + ": Wants to clean toilet");
    publicToilet.startCleaning();
    try
    {
      System.out.println("Cleaner is cleaning...");
      Thread.sleep(500);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    publicToilet.endCleaning();
    System.out.println(name + ": Finished cleaning toilet");

    try
    {
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
