public class TestStarvedReaders
{
  public static void main(String[] args)
  {
    WritersPreferenceStarveReaders sr = new WritersPreferenceStarveReaders();

    for (int i = 0; i < 5; i++)
    {
      Writer w = new Writer(sr, "W"+i);
      new Thread(w).start();
    }

    for (int i = 0; i < 5; i++)
    {
      Reader r = new Reader(sr, "R"+i);
      new Thread(r).start();
    }
  }
}
