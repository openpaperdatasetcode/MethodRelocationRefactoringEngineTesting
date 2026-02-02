import java.util.List;import java.util.ArrayList;
// Source class: generic, public modifier, same package, two member inner classespublic class SourceClass<T extends Number> {// Field referencing target class (per_condition: source contains target field)TargetClass<String> targetField;
// First member inner class (source class feature)class FirstMemberInnerClass {int value = 10;}
// Second member inner class (source class feature)class SecondMemberInnerClass {String text = "test";}
// Abstract method to refactor: instance, Object return, protected access, method_position=source// with_bounds (T extends Number), variable call, no_new_exceptionprotected abstract Object methodToMove();
// Method containing call_method (others_class, final, overloading, obj.m1().m2().m3(), List<String> return)public void sourceMethod() {// Call to others_class final method with overloading and chained calls (obj.m1().m2().m3())List<String> result = OthersClass.getInstance().m1(10).m2("test").m3();// Variable call (uses targetField)targetField.processLocalInner(result);}}
// Target class: generic, protected modifier, local inner class (target_feature)protected class TargetClass {
// Method with local inner class (target_feature)
public void processLocalInner(List<String> data) {
// Local inner class
class LocalInnerClass {
void handleData(List<String> input) {
// No new exception (complies with method feature)
System.out.println(input.size());
}
}
new LocalInnerClass().handleData(data);
}
}
// Others class for call_method feature: final modifier, overloading, chained calls (obj.m1().m2().m3())final class OthersClass {private OthersClass() {}
// Static method to get instance (for chained call start)public static OthersClass getInstance() {return new OthersClass();}
// Overloaded m1 method (overloading feature)public OthersClass m1(int num) {return this;}
// Overloaded m1 method (overloading feature)public OthersClass m1(String str) {return this;}
// m2 method for chained callpublic OthersClass m2(String text) {return this;}
// m3 method (final in chain) returning List<String> (return_type)public List<String> m3() {return new ArrayList<>();}}