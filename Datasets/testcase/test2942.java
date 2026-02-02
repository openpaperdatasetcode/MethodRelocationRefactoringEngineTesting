import java.lang.reflect.Method;import java.util.List;
class SourceClass<T> {static class NestedStatic1 {}static class NestedStatic2 {}
@Overridepublic final Object methodToMove(TargetClass<?> target) {NestedStatic1 ns1 = new NestedStatic1();TargetClass.InnerClass inner = target.new InnerClass();String str = inner.getValue();try {Method method = TargetClass.class.getMethod("methodToMove");} catch (NoSuchMethodException e) {}return null;}
protected String callMethod(List<T> list) {for (T item : list) {Object result = methodToMove(new TargetClass<>());}return "";}}
class TargetClass {
class InnerClass {
String getValue() { return ""; }
}
@Overridepublic Object methodToMove() {return null;}}