import java.util.Random;

public class TestWriter
{
  public static void main(String[] args)
  {
    WritersPreferenceToSharedInt rp = new WritersPreferenceToSharedInt();
    Random random = new Random();
    for (int i = 0; i < 3; i++)
    {
      new Thread(()-> {
        while (true)
        {
          rp.acquireWrite();
          rp.incrementInt();
          System.out.println("Writer incremented");
          rp.releaseWrite();
          /*
          try
          {
            Thread.sleep(100 + random.nextInt(50));
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }

           */
        }
      }).start();
    }
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      new Thread(()-> {
        while (true)
        {
          rp.acquireRead();
          int result = rp.readInt();
          System.out.println("reader " + finalI + ": " + result);
          rp.releaseRead();
          /*

          try
          {
            Thread.sleep(random.nextInt(50));
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }

           */
        }
      }).start();
    }
  }
}
