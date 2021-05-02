public class TestBridge
{
  public static void main(String[] args)
  {
    SingleLaneBridge bridge = new SingleLaneBridge();

    for (int i = 0; i < 20; i++) {
      new Thread(new Car(i % 2 == 0, "Car" + i, bridge)).start();
    }
  }
}
