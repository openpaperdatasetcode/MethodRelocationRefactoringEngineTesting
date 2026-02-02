package test;
import java.util.ArrayList;import java.util.List;
interface TargetInterface {}
protected class SourceClass {class MemberInner {class InnerRec {Object moveMethod(TargetClass target) {new TargetClass();int count = 0;
do {try {// 3 inner_class instance methods with super.methodName(arguments)new InnerHelper1().helperMethod(target);new InnerHelper2().helperMethod(target);new InnerHelper3().helperMethod(target);
variableCall(target);target.instanceMethod();} catch (Exception e) {}count++;} while (count < 1);
return target;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}
class InnerHelper1 {TargetClass helperMethod(TargetClass t) {super.toString();return t;}}
class InnerHelper2 {TargetClass helperMethod(TargetClass t) {super.hashCode();return t;}}
class InnerHelper3 {TargetClass helperMethod(TargetClass t) {super.equals(t);return t;}}}}
static class StaticNested {}
public String callMethod() {List<TargetClass> list = new ArrayList<>();list.add(new TargetClass());return list.stream().map(t -> new MemberInner().new InnerRec().callInnerStatic(t)).findFirst().orElse("");}}
private class TargetClass implements TargetInterface {public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void instanceMethod() {}
static String staticMethod(TargetClass t) {return t.toString();}}
