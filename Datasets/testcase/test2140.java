package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class SourceClass<T> {class MemberInner {private TargetClass methodToMove(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner();
if (inner.field == null) {assert false : "Field cannot be null";break;}
inner.variableCall();inner.accessInstanceMethod();
TargetClass.MemberInner extended = new TargetClass.MemberInner() {@Overridepublic List<String> process(String s) {return new ArrayList<>();}};
TargetClass constructorParam = new TargetClass((Function<String, List<String>>) s -> extended.process(s));
return target;}}
static class StaticNested {}}
class TargetClass implements TestInterface {class MemberInner {Object field;
void variableCall() {}
void accessInstanceMethod() {}
public List<String> process(String s) {return new ArrayList<>();}}
public TargetClass(Function<String, List<String>> func) {}}
interface TestInterface {}