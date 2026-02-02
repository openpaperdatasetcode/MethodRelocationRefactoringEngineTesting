import java.io.IOException; import java.util.ArrayList; import java.util.List;
// Parent interface for overriding (defines method to be overridden)
interface ParentOverridable {
// Method to be overridden (void return, synchronized access)
synchronized void updateData(Object targetParam) throws IOException;
}

// Target abstract interface (with static nested class)
abstract interface TargetInterface {
// Static nested class (target_feature)
static class TargetStaticNested {
// Shared resource for synchronized operations
public static final List<String> sharedList = new ArrayList<>();

// Method for variable call feature
public static void addToShared(String data) {
sharedList.add(data);
}
}

// Target inner record (target_inner_rec: target class for method move)
record TargetInnerRec(String recId, int recVersion) {}
}

// Source interface (no modifier, same package, with 2 member inner classes)
interface SourceInterface extends ParentOverridable, TargetInterface {
// 1st Member inner class (source_class feature)
class SourceInner1 {
private String innerData1;

public SourceInner1(String data) {
this.innerData1 = data;
}

// Method for variable call feature
public String getFormattedData() {
return "Inner1: " + innerData1;
}
}

// 2nd Member inner class (source_class feature)
class SourceInner2 {
private int innerCount = 0;

// Synchronized method for variable call
public synchronized int incrementCount() {
return ++innerCount;
}
}

// Overriding method (synchronized access, void return)
// Per_condition: contains TargetInnerRec parameter (target_inner_rec)
@Override
default synchronized void updateData(Object targetParam) throws IOException {
// Requires_try_catch: checked exception (IOException) declared, requires try-catch on call
if (!(targetParam instanceof TargetInnerRec)) {
throw new IOException("Invalid parameter type: must be TargetInnerRec");
}
TargetInnerRec targetRec = (TargetInnerRec) targetParam;

// Variable call: use source member inner classes
SourceInner1 inner1 = new SourceInner1(targetRec.recId());
SourceInner2 inner2 = new SourceInner2();
String formatted = inner1.getFormattedData();
int count = inner2.incrementCount();

// Synchronized statement (locks on target static nested class resource)
synchronized (TargetStaticNested.sharedList) {
TargetStaticNested.addToShared(formatted);
TargetStaticNested.addToShared("Version: " + targetRec.recVersion());
TargetStaticNested.addToShared("Count: " + count);
}

// Assert statement (validates shared list state)
assert TargetStaticNested.sharedList.size() >= 3 : "Shared list not populated correctly";

// Variable call: use target static nested class method
TargetStaticNested.addToShared("Update completed for: " + targetRec.recId());

// No new checked/unchecked exceptions thrown (feature: no_new_exception implied)
}

// Default method to trigger overriding method
default void triggerUpdate(TargetInnerRec targetRec) {
try {
updateData(targetRec); // Requires_try_catch: handles declared IOException
} catch (IOException e) {
System.err.println("Update failed: " + e.getMessage());
}
}
}

// Concrete implementation of SourceInterface (resolves abstract interface)
class SourceImpl implements SourceInterface {}

// Test entry point
class InterfaceMethodTest {
public static void main(String[] args) {
SourceInterface source = new SourceImpl();
TargetInterface.TargetInnerRec testRec = new TargetInterface.TargetInnerRec("rec-68107", 1);

// Trigger overriding method
source.triggerUpdate(testRec);

// Verify result
System.out.println("Shared list size: " + TargetInterface.TargetStaticNested.sharedList.size());
TargetInterface.TargetStaticNested.sharedList.forEach(System.out::println);
}
}