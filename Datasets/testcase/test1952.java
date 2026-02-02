package test;
import other.OtherPackageClass;
protected class SourceClass {static class StaticNested {// Static code block with varargs method callstatic {TargetClass target = new TargetClass();int result = target.new Inner().process(2, "a", "b");}}
class Inner {class InnerRec {public Object method(TargetClass target) {// AssertStatement with ClassName.field=1 in different packageassert OtherPackageClass.checkValue(TargetClass.STATIC_FIELD, 1) : "Field mismatch";
// Expression statement (variable call)target.innerField = "processed";
// Synchronized statementsynchronized (target.new Inner()) {target.inner.count++;}
// If statementif (target.inner.count > 5) {return target.inner.getResult();}
// Access target's inner class methodtarget.new Inner().update("value");
return target.innerField;}}}
// Anonymous inner classRunnable anonymous = new Runnable() {public void run() {new Inner().new InnerRec().method(new TargetClass());}};}
/**
Private target class with static nested class
Contains static field and inner class/
private class TargetClass {
/*
Static field with value 1
*/
static final int STATIC_FIELD = 1;
String innerField;
Inner inner = new Inner();
/**
Static nested class for utility operations
*/
static class Nested {
static void log(String message) {
System.out.println(message);
}
}
class Inner {int count = 0;
/**
Varargs method processing multiple arguments
@param num base number
@param args string arguments
@return processed result
*/
public int process(int num, String... args) {
count += num + args.length;
return count;
}
void update(String value) {innerField = value;Nested.log("Updated: " + value);}
Object getResult() {return count;}}}
package other;
import test.TargetClass;
public class OtherPackageClass {public static boolean checkValue(int field, int expected) {return field == expected;}}