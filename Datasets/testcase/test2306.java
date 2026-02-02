package same.pkg;
import java.util.List;import java.util.ArrayList;
protected sealed class Source permits SubSource {static class StaticNested {}
private List<String> instanceMethod(Target<Integer> targetParam) {// try statementtry {Target.Inner inner = targetParam.new Inner();} finally {}
// variable callList<String> varCall = targetParam.getList();
// overload_exist (overloaded method call)process(1);process("string");
// with_bounds (type with bounds)Class<? extends Number> boundedType = targetParam.getValue().getClass();
return new ArrayList<>();}
private void process(int i) {}private void process(String s) {}
void methodWithLocal() {class LocalInner {}}}
final class SubSource extends Source {}
protected class Target<T extends Number> {class Inner {}
List<String> getList() {return new ArrayList<>();}
T getValue() {return null;}}