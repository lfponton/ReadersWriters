public class Car implements Runnable
{
  private boolean fromLeft;
  private String name;
  private SingleLaneBridge bridge;

  public Car(boolean fromLeft, String name, SingleLaneBridge bridge)
  {
    this.fromLeft = fromLeft;
    this.name = name;
    this.bridge = bridge;
  }

  @Override public void run()
  {
    while (true) {
      if (fromLeft) {
        System.out.println(name + " wants to cross the bridge from the LEFT");
        bridge.enterFromLeft();
        bridge.crossBridge(name);
        bridge.exitToTheRight();
        System.out.println(name + " exists bridge");
      }
      else {
        System.out.println(name + " wants to cross the bridge from the RIGHT");
        bridge.enterFromRight();
        bridge.crossBridge(name);
        bridge.exitToTheLeft();
        System.out.println(name + " exists bridge");
      }
      fromLeft = !fromLeft;
    }

  }
}
