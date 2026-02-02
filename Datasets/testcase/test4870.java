import java.util.ArrayList; import java.util.List; import java.util.function.IntSupplier;
// Super interface for target to implement (target_feature: implements)
interface DataProcessor {
int processItem(String item);
}

// Subclass for recursion method feature (method_feature: sub_class)
class TargetSubClass implements DataProcessor {
private int count = 0;

// Recursive method (for method_feature: recursion)
public int recursiveCount(int depth) {
if (depth <= 0) return count;
count++;
return recursiveCount(depth - 1); // Recursive call
}

@Override
public int processItem(String item) {
return item.length();
}
}

// Target protected interface (implements super interface + member inner class)
protected interface TargetInterface extends DataProcessor {
// Member inner class (target_inner: target class for method move)
class TargetInner {
private List<String> innerList = new ArrayList<>();

// Method for variable call feature
public void addItem(String item) {
innerList.add(item);
}

public int getListSize() {
return innerList.size();
}
}

// Default method implementation (from DataProcessor)
@Override
default int processItem(String item) {
return item.hashCode();
}
}

// Source interface (no modifier, same package, with member/local inner classes)
interface SourceInterface {
// Member inner class (source_inner: method_position)
class SourceInner {
/**

Varargs method with generic type parameters (method types parameter is:generic)
Processes TargetInterface.TargetInner parameter and varargs elements
@param <T> Generic type for varargs elements (extends CharSequence)
@param targetInnerParam Target inner class instance (parameter of target)
@param varargs Varargs elements to process
*/
private <T extends CharSequence> void processVarargs(TargetInterface.TargetInner targetInnerParam, T... varargs) {
// Super constructor invocation (implicit for inner class: calls Object constructor)
super.toString();
// Uses_outer_this: access source interface's implicit outer instance (via static context fallback)
String outerCtx = SourceInterface.class.getSimpleName();
// Type declaration statement
TargetSubClass subClass = new TargetSubClass();
List<Integer> processedLengths = new ArrayList<>();
// Enhanced for statement (process varargs)
for (T elem : varargs) {
if (elem == null) continue;
String strElem = elem.toString();
targetInnerParam.addItem(strElem); // Variable call: target inner class method
processedLengths.add(strElem.length());
}
// Functional interfaces: 2 sub_class recursion method calls (instanceReference.methodName)
// 1st recursion call
IntSupplier recursionSupplier1 = () -> subClass.recursiveCount(3);
int count1 = recursionSupplier1.getAsInt();
// 2nd recursion call
IntSupplier recursionSupplier2 = () -> subClass.recursiveCount(2);
int count2 = recursionSupplier2.getAsInt();
// Variable call: use target inner class & subClass methods
int totalSize = targetInnerParam.getListSize() + count1 + count2;
System.out.println("Total processed (outer: " + outerCtx + "): " + totalSize);
// No_new_exception: no new checked/unchecked exceptions thrown
}

// Public method to trigger varargs method (expose private method)
public <T extends CharSequence> void triggerProcess(TargetInterface.TargetInner targetInner, T... varargs) {
processVarargs(targetInner, varargs);
}
}

// Method with local inner class (source_class feature)
default void useLocalInner() {
// Local inner class (source_class feature)
class SourceLocalInner {
public void initAndProcess() {
TargetInterface.TargetInner targetInner = new TargetInterface.TargetInner();
SourceInner sourceInner = new SourceInner();

// Variable call: trigger varargs method
sourceInner.triggerProcess(targetInner, "apple", "banana", "cherry");
System.out.println("Local inner target list size: " + targetInner.getListSize());
}
}
new SourceLocalInner().initAndProcess();
}

// Default method to create source inner instance
default SourceInner createSourceInner() {
return new SourceInner();
}
}

// Concrete implementation of TargetInterface (resolves protected interface)
class TargetImpl implements TargetInterface {}

// Test entry point
class InterfaceVarargsTest {
public static void main(String[] args) {
// 1. Trigger via source interface local inner class
SourceInterface source = new SourceInterface() {};
source.useLocalInner();

// 2. Direct trigger via source inner class
SourceInterface.SourceInner sourceInner = source.createSourceInner();
TargetInterface.TargetInner targetInner = new TargetInterface.TargetInner();
sourceInner.triggerProcess(targetInner, "dog", "cat", "bird");
System.out.println("Direct target list size: " + targetInner.getListSize());
}
}