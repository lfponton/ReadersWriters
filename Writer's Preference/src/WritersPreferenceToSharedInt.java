public class WritersPreferenceToSharedInt
{
  private boolean activeWriter;
  private int waitingWriters;
  private int activeReaders;

  private int sharedInt;

  public synchronized void acquireRead() {
    while (activeWriter || waitingWriters >  0) {
      try
      {
        wait();
      }
      catch (InterruptedException ignored) { }
    }
    activeReaders++;
  }

  public synchronized void releaseRead() {
    activeReaders--;
    if (activeReaders == 0) {
      notifyAll();
    }
  }

  public synchronized void acquireWrite()
  {
    waitingWriters++;
    while (activeWriter || activeReaders > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException ignored) { }
    }
    waitingWriters--;
    activeWriter = true;
  }

  public synchronized void releaseWrite() {
    activeWriter = false;
    notifyAll();
  }

  public int read() {
    return sharedInt;
  }

  public void write()
  {
    sharedInt++;
  }
}
