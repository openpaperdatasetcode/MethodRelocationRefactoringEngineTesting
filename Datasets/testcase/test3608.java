package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
interface MyInterface {}
class ParentClass {protected void parentMethod() {}}
class SourceClass extends ParentClass implements MyInterface {class MemberInner {void innerMethod() {}}
private List<String> instanceMethod(FinalTargetClass<String> target) throws SQLException {List<String> result = new ArrayList<>();SourceClass outer = this;
class LocalInner {private void nestedMethod() {do {outer.new MemberInner().innerMethod();} while (false);}}new LocalInner().nestedMethod();
Object obj1 = target;Object obj2 = new Object();if (obj1 instanceof FinalTargetClass) result.add("match1");if (obj2 instanceof FinalTargetClass) result.add("match2");
super.parentMethod();variableCall(target);
return result;}
private void variableCall(FinalTargetClass<String> target) {target.process();}}
final class FinalTargetClass<T> {T typeParamField;
FinalTargetClass() {new Runnable() {public void run() {typeParamField = (T) "init";}}.run();}
void process() {}}