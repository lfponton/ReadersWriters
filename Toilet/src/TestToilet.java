public class TestToilet
{
  public static void main(String[] args)
  {
    PublicToilet publicToilet = new ToiletBuilding(5);

    for (int i = 0; i < 10; i++)
    {
      Person p = new Person(publicToilet, "Person" + i);
      new Thread(p).start();
    }

    Cleaner c = new Cleaner(publicToilet, "Cleaner");
    new Thread(c).start();
  }
}
