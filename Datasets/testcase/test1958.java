package test;
import java.io.IOException;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
class SourceClass {private String outerPrivate = "private_value";
class MemberInner {String getValue() {return outerPrivate;}}
static class StaticNested {private String processNested() {return "nested_processed";}
private String processNested(int value) {return "nested_processed_" + value;}}
private void method(TargetClass... targets) throws IOException {if (targets == null || targets.length == 0) {throw new IllegalArgumentException("Targets cannot be null or empty");}
// Constructor invocationTargetClass newTarget = new TargetClass();// Super constructor invocationSubTarget subTarget = new SubTarget("extended");
// Empty statement;
// Reflection usagetry {Constructor<TargetClass> constructor = TargetClass.class.getConstructor(String.class);TargetClass reflectedTarget = constructor.newInstance("reflected");
Method method = TargetClass.class.getMethod("addItem", String.class);method.invoke(reflectedTarget, outerPrivate);} catch (Exception e) {throw new IOException("Reflection error", e);}
// Exception handling with method chain call (overloading)try {String result = new StaticNested().processNested().concat(new StaticNested().processNested(1)).toUpperCase();targets[0].items.add(result);} catch (NullPointerException e) {// Handle exception}
// Variable call to target's fieldfor (TargetClass target : targets) {target.items.add(outerPrivate); // Access outer privateif (target.items.size() > 5) {return; // Return statement}}
// Override violation (SubTarget doesn't properly override abstract method)subTarget.action();}}
protected class TargetClass {protected java.util.List<String> items = new java.util.ArrayList<>();
TargetClass() {}
TargetClass(String initial) {items.add(initial);}
void addItem(String item) {// Local inner classclass ItemValidator {boolean isValid(String s) {return s != null && !s.isEmpty();}}if (new ItemValidator().isValid(item)) {items.add(item);}}
abstract void action();}
class SubTarget extends TargetClass {SubTarget(String initial) {super(initial); // Super constructor invocation}
// Override violation: missing @Override and incorrect signaturevoid action(int param) {items.add("sub_action");}}