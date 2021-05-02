public class Person implements Runnable
{
  private PublicToilet publicToilet;
  private String name;

  public Person(PublicToilet publicToilet, String name)
  {
    this.publicToilet = publicToilet;
    this.name = name;
  }

  @Override public void run()
  {
    while (true)
    {
      System.out.println(name + ": Needs to use the toilet");
      publicToilet.stepIntoCabin();
      try
      {
        System.out.println(name + ": Using the toilet");
        Thread.sleep(100);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      System.out.println(name + ": Leaving the cabin");
      publicToilet.leaveCabin();
      try
      {
        int time = (int) (System.currentTimeMillis()%1200);
        System.out.println(name + " is going about his business for "
            + time + " milliseconds");
        Thread.sleep(time);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
