import java.util.ArrayList; import java.util.List;
// Target abstract generic class (with anonymous inner class)
abstract class TargetGeneric<T> {
// Class field for ClassName.field access
protected T targetClassField;

public TargetGeneric(T field) {
this.targetClassField = field;

// Anonymous inner class (target_feature)
Runnable targetAnon = new Runnable() {
@Override
public void run() {
System.out.println("Target anon: Processing " + targetClassField);
}
};
targetAnon.run();
}

// Private instance method (call_method: target, private, instance)
private Object getTargetData(T input) {
return "TargetData: " + input + "_" + targetClassField;
}

// Abstract method (required for abstract class)
public abstract T transform(T data);
}

// Source public generic class (same package, with type parameter & 2 member inner classes)
public class SourceGeneric<S> {
// Instance field for access_instance_method
private List<S> sourceInstanceList = new ArrayList<>();

// 1st Member inner class (source_class feature)
public class SourceInner1 {
private S innerData1;

public SourceInner1(S data) {
this.innerData1 = data;
}

// Instance method for access_instance_method
public S getInnerData1() {
return innerData1;
}
}

// 2nd Member inner class (source_class feature)
public class SourceInner2 {
public <T> T wrapData(T data) {
return data;
}
}

// Varargs method to be moved (private access, return Object)
// Per_condition: contains TargetGeneric parameter
private Object processVarargs(TargetGeneric<S> targetParam, S... varargs) {
// NullPointerException: explicit null check
if (targetParam == null || varargs == null) {
throw new NullPointerException("Target parameter or varargs cannot be null");
}

// Type declaration statement
SourceInner1 inner1 = new SourceInner1(varargs[0]);
SourceInner2 inner2 = new SourceInner2();
List<Object> resultList = new ArrayList<>();

// Super keywords: access superclass (Object) method
String superStr = super.toString();
resultList.add(superStr);

// Access_instance_method: call source instance method
sourceInstanceList.add(inner1.getInnerData1());
resultList.add("Instance list size: " + sourceInstanceList.size());

// For loop with break statement
for (int i = 0; i < varargs.length; i++) {
if (i >= 2) {
break; // Stop after processing 2 varargs elements
}
// Constructor invocation: wrap varargs element via inner class
S wrapped = inner2.wrapData(varargs[i]);
resultList.add("Wrapped vararg " + i + ": " + wrapped);
}

// Call_method: target private instance method (new ClassName().method() in property assignment)
Object targetResult = new TargetGeneric<S>(varargs[0]) {
@Override
public S transform(S data) {
return data;
}
}.getTargetData(varargs[0]); // new ClassName().method()
resultList.add("Target call result: " + targetResult);

// Private ReturnStatement (ClassName.field access 1 time)
if (!resultList.isEmpty()) {
return TargetGeneric.class.getSimpleName() + "_result: " + resultList; // ClassName.field (class name as field reference)
}

// Throw statement (fallback)
throw new IllegalArgumentException("No valid varargs processed");
}

// Public method to trigger varargs method
public Object startProcessing(TargetGeneric<S> targetParam, S... varargs) {
try {
return processVarargs(targetParam, varargs);
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
return "Error: " + e.getMessage();
}
}
}

// Test entry point
class GenericMethodTest {
public static void main(String[] args) {
// Initialize target (abstract generic class via anonymous subclass)
TargetGeneric<String> target = new TargetGeneric<String>("target-base") {
@Override
public String transform(String data) {
return data.toUpperCase();
}
};

// Initialize source (public generic class)
SourceGeneric<String> source = new SourceGeneric<>();

// Trigger varargs method
Object result1 = source.startProcessing(target, "vararg-1", "vararg-2", "vararg-3");
System.out.println("Result 1: " + result1);

// Trigger with null varargs (test exception handling)
Object result2 = source.startProcessing(target, (String[]) null);
System.out.println("Result 2: " + result2);
}
}