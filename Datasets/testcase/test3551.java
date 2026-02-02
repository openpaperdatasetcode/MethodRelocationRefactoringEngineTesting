package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface SourceInterface {}
class ParentTarget {}
public class SourceClass implements SourceInterface {private final TargetClass targetField = new TargetClass();
class MemberInner1 {}
class MemberInner2 {protected List<String> innerCallMethod(SourceClass outer) {return outer.new MemberInner1().innerMethod(targetField);}
private List<String> innerMethod(TargetClass target) {target.targetMethod();return new ArrayList<>();}}
public void moveMethod() {super.toString();assert targetField != null : "Target class instance not null";variableCall();targetField.innerClass.doTask();}
public void moveMethod(String param) {super.toString();assert targetField.field == 1 : "Target field validation";variableCall();targetField.innerClass.doTask();}
private void variableCall() {targetField.doTask();}
public List<String> callMethod() {Supplier<List<String>> supplier = () -> new MemberInner2().innerCallMethod(this);return supplier.get();}}
final class TargetClass extends ParentTarget {public int field = 1;
class TargetInner {public void doTask() {}}
private final TargetInner innerClass = new TargetInner();
public void doTask() {}
public void targetMethod() {class LocalInner {}new LocalInner();}
public void moveMethod() {}public void moveMethod(int param) {}}