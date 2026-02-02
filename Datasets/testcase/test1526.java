package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
protected class Target {public String instanceField = "target_field";
public void process() {// Local inner class in targetclass LocalHandler {List<String> handleData(String... data) {List<String> result = new ArrayList<>();for (String s : data) {result.add(s);}return result;}}}
public List<String> baseMethod() {return List.of("base");}}
class SubTarget extends Target {// Overriding target method@Overridepublic List<String> baseMethod() {return super.baseMethod(); // super.methodName()}}
class Source<T extends CharSequence> {class MemberInner {void useTargetInner(Target target, T data) {// Access target local inner class (depends_on_inner_class)target.process();}}
// Overloading methods (return base type)protected int handle(Target target) {// With bounds (T extends CharSequence)T boundData = (T) "bounded_data";
// Local inner class in sourceclass LocalProcessor {int countItems(List<String> list) {return list.size();}}
// Expression statementMemberInner inner = new MemberInner();inner.useTargetInner(target, boundData);
// Variable callString fieldValue = target.instanceField;
// Access instance fieldSystem.out.println(target.instanceField);
// Array initialization with call_methodList<String>[] dataArrays = new List[]{new SubTarget().baseMethod(),new SubTarget().baseMethod()};
// IOException handlingtry {if (fieldValue.isEmpty()) {throw new IOException("Instance field is empty");}} catch (IOException e) {// No new exceptionSystem.err.println(e.getMessage());}
return new LocalProcessor().countItems(dataArrays[0]);}
// Overloading method (second variant)protected int handle(Target target, String extra) {return handle(target) + extra.length();}}