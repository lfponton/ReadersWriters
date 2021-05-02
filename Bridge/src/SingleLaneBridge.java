public class SingleLaneBridge
{
  private int waitingFromLeft, waitingFromRight;
  private int crossingFromLeft, crossingFromRight;

  public synchronized void enterFromLeft() {
    waitingFromLeft++;
    while (crossingFromRight > 0 || waitingFromRight > waitingFromLeft)
    {
      try
      {
        if (crossingFromRight > 0)
        {
          System.out.println("LEFT Can't cross bridge. Incoming from RIGHT");
        }
        else if (waitingFromRight > waitingFromLeft)
        {
          System.out.println("LEFT Can't cross bridge, RIGHT QUEUE IS LARGER");
        }
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingFromLeft--;
    crossingFromLeft++;
  }

  public synchronized void exitToTheRight() {
    crossingFromLeft--;
    if (crossingFromLeft == 0)
    {
      notifyAll();
    }
  }

  public synchronized void enterFromRight()
  {
    waitingFromRight++;
    while (crossingFromLeft > 0 || waitingFromLeft > waitingFromRight)
    {
      try
      {
        if (crossingFromLeft > 0)
        {
          System.out.println("RIGHT Can't cross bridge. Incoming from LEFT");
        }
        else if (waitingFromLeft > waitingFromRight)
        {
          System.out.println("RIGHT Can't cross bridge, LEFT QUEUE IS LARGER");
        }
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingFromRight--;
    crossingFromRight++;
  }

  public synchronized void exitToTheLeft()
  {
    crossingFromRight--;
    if (crossingFromRight == 0)
    {
      notifyAll();
    }
  }

  public synchronized void crossBridge(String name) {
    System.out.println(name + " crosses bridge");
  }
}
