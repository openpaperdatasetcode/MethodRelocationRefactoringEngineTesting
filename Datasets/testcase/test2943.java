import java.util.ArrayList;import java.util.List;
public abstract class SourceClass implements Runnable {public static class NestedStaticClass {}
public void outerMethod(TargetClass target) {class LocalInnerClass {public List<String> methodToMove(TargetClass param) {List<String> list = new ArrayList<>();paramHelper(param);Runnable r = param::toString;return list;}
private void paramHelper(TargetClass p) {}}}}
abstract class TargetClass {class LocalInnerInTarget {}}