package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
public class SourceClass<T> {static class StaticNested {}
{new Runnable() {};}
public static int staticField;
final void varargsMethod(TargetClass<?>... targets) {super.toString();variableCall();
// Instance method from others_class in instance code blocks{Object obj = SuperType.instance.method(targets[0]);}
// Access target fieldfor (TargetClass<?> target : targets) {int fieldVal = target.targetField;}
// Depends on static fieldint val = staticField;
// Used by reflectiontry {Method method = SourceClass.class.getMethod("varargsMethod", TargetClass[].class);} catch (NoSuchMethodException e) {}
// Call recursive method in exception handlingtry {int result = recursiveMethod(5);} catch (Exception e) {int result = recursiveMethod(0);}}
private void variableCall() {}
public int recursiveMethod(int n) {if (n <= 0) {return super.hashCode();}return recursiveMethod(n - 1); // Recursion}}
class SuperType {public static OthersClass instance = new OthersClass();}
class OthersClass {Object method(TargetClass<?> target) {return new Object();}}
public class TargetClass {
int targetField;
void someMethod() {class LocalInner {}}}