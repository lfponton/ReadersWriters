public class TestBalanced
{
  public static void main(String[] args)
  {
    BalancedReadWrite sr = new BalancedReadWrite();

    for (int i = 0; i < 10; i++)
    {
      Writer w = new Writer(sr, "W"+i);
      new Thread(w).start();
    }

    for (int i = 0; i < 10; i++)
    {
      Reader r = new Reader(sr, "R"+i);
      new Thread(r).start();
    }
  }
}
