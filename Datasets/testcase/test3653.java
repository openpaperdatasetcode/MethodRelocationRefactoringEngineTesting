package test;
public final class SourceClass<T> {private T sourceField;
public final class InnerSource {public final class NestedInner {public final int instanceMethod(TargetClass.InnerTarget param) {int result = 0;try {class LocalInner {private void check() {assert param.value == 1 : "Value mismatch";assert TargetClass.this.publicField > 0 : "Field check";}}new LocalInner().check();
if (param.getCount() > 0) {result = param.getCount();SourceClass.this.sourceField = (T) Integer.valueOf(result);} else {; // Empty statement}param.update();} catch (Exception e) {result = -1;}return result;}}}
public static class StaticNested {public static int helper(TargetClass.InnerTarget target) {
return target.getCount();
}
}
}
public class TargetClass {public int publicField = 5;
public class InnerTarget {int value;
public InnerTarget() {Runnable r = new Runnable() {public void run() {value = 1;}};r.run();}
public int getCount() {return value;}
public void update() {value++;}}}