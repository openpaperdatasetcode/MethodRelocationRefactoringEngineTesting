package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class SourceClass<T> {class MemberInner {protected void instanceMethod(TargetClass<String> target) {super();class LocalInner1 {}class LocalInner2 {}
variableCall();
// Access target fieldString fieldVal = target.targetField;
// Accessor methods in functional interfaces (2 instances)Supplier<List<String>> supplier1 = SourceClass::getList1;Supplier<List<String>> supplier2 = SourceClass::getList2;
// Raw type usageList rawList = new ArrayList();}
private void variableCall() {}}
public static List<String> getList1() {return new ArrayList<>();}
public static List<String> getList2() {return new ArrayList<>();}}
package target;
protected class TargetClass {
U targetField;
class MemberInner {}}