public class ToiletBuilding implements PublicToilet
{
  private int cabins;
  private int people;
  private boolean cleaning;

  public ToiletBuilding(int cabins)
  {
    this.cabins = cabins;
  }
  private boolean emptyCabin() {
    return !cleaning && people < cabins;
  }

  private boolean cleaningAllowed() {
    return !cleaning && people <= 0;
  }

  @Override public synchronized void stepIntoCabin()
  {
    while (!emptyCabin())
    {
      try
      {
        System.out.println("There are MORE PEOPLE THAN EMPTY CABINS");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    people++;
  }

  @Override public synchronized void leaveCabin()
  {
    people--;
    notifyAll();
  }

  @Override public synchronized void startCleaning()
  {
    while (!cleaningAllowed())
    {
      try
      {
        System.out.println("Cabins are IN USE");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    cleaning = true;
    System.out.println("Cabins are BEING CLEANED");
  }

  @Override public synchronized void endCleaning()
  {
    cleaning = false;
    System.out.println("Cabins are CLEAN");
    notifyAll();
  }
}
