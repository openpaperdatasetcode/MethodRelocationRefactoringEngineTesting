package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
final class SourceClass<T extends Number & CharSequence> {public class InnerClass {// Static DoStatement with 1 super.field access (pos: inner class)static {class Nested {void execute(SubTarget<?> target) {do {System.out.println(target.superField); // super.field access} while (target.count < 3);}}}
// Public generic method (3 type params) in while looppublic <U, V extends U, W> Object processGeneric(TargetClass<T> target, U u, V v, W w) {return target.data.toString() + u + v + w;}}
protected int process(TargetClass<T> target) {// Local inner class 1class TargetHandler {int handle(TargetClass<T> t) {return t.data.intValue();}}
// Local inner class 2class Validator {boolean check(T value) {return value != null;}}Validator validator = new Validator();
// Constructor invocationTargetClass<T> newTarget = new TargetClass<>(target.data);
// Super constructor invocationclass SubTarget extends TargetClass {
public String superField;
SubTarget(U data) {super(data);this.superField = "inherited";}
@Overridepublic String abstractMethod() {return super.abstractMethod() + "_sub"; // super.methodName()}}SubTarget<T> subTarget = new SubTarget<>(target.data);
// Expression statementtarget.count++;
// Private BooleanLiteral (2 occurrences)private boolean flag1 = true;private boolean flag2 = false;if (flag1 && !flag2) {target.data = (T) Integer.valueOf(100);}
// Variable call - access target's fieldint total = target.count;
// Access instance fieldtotal += target.data.intValue();
// With bounds (T is bounded by Number & CharSequence)total += target.data.length();
// While loop with inner class's generic methodint i = 0;InnerClass inner = new InnerClass();while (i < 3) {inner.processGeneric(target, i, i + 1, "iter" + i);i++;}
// Call target's public abstract method in collection operationsList<String> results = new ArrayList<>();results.add(target.abstractMethod());results.add(subTarget.abstractMethod());
// Used by reflectiontry {Method method = TargetClass.class.getMethod("abstractMethod");method.invoke(target);} catch (Exception e) {// no new exception}
return new TargetHandler().handle(newTarget);}}
private class TargetClass<T> {public T data;public int count;
public TargetClass(T data) {this.data = data;this.count = 0;}
// Public abstract method with super.methodName()public abstract String abstractMethod();}