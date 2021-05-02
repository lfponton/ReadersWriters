public class ReadersPreferenceReadWrite
{
  private int activeReaders, activeWriters, waitingReaders;
  private int sharedData;

  public ReadersPreferenceReadWrite() {
    activeReaders = activeWriters = waitingReaders = 0;
    sharedData = 0;
  }

  public synchronized void acquireRead() {
    waitingReaders++;
    while (activeWriters > 0) {
      try
      {
        System.out.println("Could not acquire read: waits");
        System.out.println("Active writers = " + activeWriters);
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
    System.out.println("Active readers = " + activeReaders);
  }

  public synchronized void acquireWrite() {
    while (activeReaders > 0 || activeWriters > 0 || waitingReaders > 0)
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
    activeWriters++;
  }

  public synchronized void releaseWrite() {
    activeWriters--;
    notifyAll();
  }

  public void write()
  {
    sharedData++;
  }

  private int read()
  {
    return sharedData;
  }

  public int safeRead() {
    System.out.println("Reader wants read access");
    acquireRead();
    int i = read();
    System.out.println("Read " + i);
    releaseRead();
    System.out.println( "Released read access");
    return i;
  }
}
