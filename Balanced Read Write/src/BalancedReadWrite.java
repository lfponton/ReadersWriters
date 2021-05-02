public class BalancedReadWrite
{
  private int activeReaders;
  private int activeWriters;
  private int waitingReaders;
  private int waitingWriters;

  private int sharedData;

  public BalancedReadWrite() {
    sharedData = 0;
  }

  public synchronized void acquireRead() {
    waitingReaders++;
    while (activeWriters > 0 || waitingWriters > waitingReaders)
    {
      try
      {
        if (activeWriters > 0) {
          System.out.println("READER Could not acquire read because of ACTIVE WRITERS");
        } else if (waitingWriters > waitingReaders)
        {
          System.out.println("READER Could not acquire read because of WAITING WRITERS");
        }
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingReaders--;
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
    while (activeReaders > 0 || activeWriters > 0
        || waitingWriters < waitingReaders) {
      try
      {
        if (activeReaders > 0) {
          System.out.println("WRITER Could not acquire read because of ACTIVE READERS");
        } else if (waitingWriters < waitingReaders)
        {
          System.out.println("WRITER Could not acquire read because of WAITING READERS");
        }
        else if (activeWriters > 0)
        {
          System.out.println("WRITER Could not acquire read because of ACTIVE WRITERS");
        }
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingWriters--;
    activeWriters++;
  }

  public synchronized void releaseWrite()
  {
    activeWriters--;
    notifyAll();
  }

  public void write() {
    sharedData++;
  }

  public int read() {
    return sharedData;
  }
}
