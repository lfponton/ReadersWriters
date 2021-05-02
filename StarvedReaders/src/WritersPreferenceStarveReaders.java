public class WritersPreferenceStarveReaders
{
  private int activeReaders, waitingWriters;
  private boolean activeWriters;
  private int sharedData;


  public synchronized void acquireRead() {
    while (activeWriters || waitingWriters > 0) {
      try
      {
        System.out.println("Could not acquire read: waits");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    activeReaders++;
  }

  public synchronized void releaseRead() {
    activeReaders--;
    if (activeReaders == 0) {
      notifyAll();
    }
  }

  public synchronized void acquireWrite() {
    waitingWriters++;
    while (activeWriters || activeReaders > 0)
    {
      try
      {
        System.out.println("Could not acquire write: waits");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingWriters--;
    activeWriters = true;
  }

  public synchronized void releaseWrite() {
    activeWriters = false;
    notifyAll();
  }

  public void write()
  {
    sharedData++;
  }

  public int read()
  {
    return sharedData;
  }
}
