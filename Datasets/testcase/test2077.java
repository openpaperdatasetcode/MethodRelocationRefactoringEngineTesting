package source;
import target.TargetClass;import otherpackage.OtherClass;
protected class SourceClass {class Member MemberInner {}
void createLocalInner() {class LocalInner {}}
protected Object methodToMove(TargetClass targetParam) { OtherClass other = new OtherClass(); TargetClass result;
if (targetParam != null) {result = other.getFirst().getSecond().getThird();} else {result = null;}
final TargetClass.StaticNested nested1 = new TargetClass.StaticNested();final TargetClass.StaticNested nested2 = new TargetClass.StaticNested();final TargetClass.StaticNested nested3 = new TargetClass.StaticNested();
targetParam.variableCall();methodToMove(targetParam, "overload");
return new TargetClass(targetParam.new InnerClass()::staticMethod);}
protected Object methodToMove(TargetClass<?> targetParam, String arg) {return null;}}
package target;
public non-sealed class TargetClass<T> {static class StaticNested {}
class InnerClass {static Object staticMethod() {return new Object();}}
public TargetClass(Object obj) {}
void variableCall() {}}
package otherpackage;
import target.TargetClass;
public class OtherClass {First getFirst() { return new First(); }
class First {Second getSecond() { return new Second(); }
class Second {TargetClass<?> getThird() { return new TargetClass<>(null); }}}}