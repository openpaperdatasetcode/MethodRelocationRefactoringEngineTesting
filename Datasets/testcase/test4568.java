package same;
import java.util.List;
public class Source {static class StaticNested1 {}static class StaticNested2 {}
private int instanceMethod(Target targetParam) throws Exception {Target newTarget = new Target();int[] nums = {1, 2, 3};for (int i = 0; i < nums.length; i++) {}for (int num : nums) {}super();int var = targetParam.getValue();targetParam.instanceMethod();return var;}
private class InnerClass<T> {String call() {return this.instanceMethod(new Target());}}}
class Target {private int value;
public Target() {}
public int getValue() {return value;}
public void instanceMethod() {}}