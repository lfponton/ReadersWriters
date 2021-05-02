public class TestBad
{
  public static void main(String[] args)
  {
    ReadersPreferenceReadWrite sr = new ReadersPreferenceReadWrite();

    for (int i = 0; i < 10; i++)
    {
      Reader r = new Reader(sr, "R" + i);
      new Thread(r).start();
    }

    for (int i = 0; i < 4; i++)
    {
      Writer w = new Writer(sr, "W" + i);
      new Thread(w).start();
    }
  }
}
