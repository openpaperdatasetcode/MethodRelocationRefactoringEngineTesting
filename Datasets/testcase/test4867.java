import java.io.IOException; import java.util.ArrayList; import java.util.List; import java.util.concurrent.locks.ReentrantLock;
// Super class for target to extend (target_feature: extends)
class TargetSuperClass {
protected List<String> superDataList = new ArrayList<>();

// Accessor method for super.methodName() call
protected List<String> getSuperDataList() {
return superDataList;
}

// Method to add data (used by accessor)
protected void addSuperData(String data) {
superDataList.add(data);
}
}

// Others class for accessor method feature (method_feature: others_class)
class OthersClass {
private String othersField;

public OthersClass(String field) {
this.othersField = field;
}

// Accessor (getter) method
public String getOthersField() {
return othersField;
}

// Accessor (setter) method
public void setOthersField(String field) {
this.othersField = field;
}
}

// Interface for source to implement (source_class feature: implements)
interface DataInitializer {
int initDataCount();
}

// Target abstract normal class (extends TargetSuperClass + member inner class)
abstract class TargetClass extends TargetSuperClass {
// Target inner record (target_inner_rec: target class for method move)
public record TargetInnerRec(String recId, int recCount) {}

// Member inner class (target_feature: member inner class)
public class TargetInner {
private TargetInnerRec innerRec;

public TargetInner(TargetInnerRec rec) {
this.innerRec = rec;
}

// Accessor method for variable call
public TargetInnerRec getInnerRec() {
return innerRec;
}
}

// Abstract method (required for abstract class)
public abstract void processRec(TargetInnerRec rec);
}

// Source public normal class (implements interface + anonymous inner/static nested classes)
public class SourceClass implements DataInitializer {
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Static method for variable call
public static int validateCount(int count) {
return count < 0 ? 0 : count;
}
}

// Lock for synchronized statement
private final ReentrantLock lock = new ReentrantLock();
// Instance field for base type return
private int baseResult;

// 1st Anonymous inner class (source_class feature)
private OthersClass anonOthers = new OthersClass("initial-anon-data") {
@Override
public String getOthersField() {
// Super keywords: call parent (OthersClass) accessor
return super.getOthersField() + "_anon-overridden";
}
};

// Constructor to be moved (public access, return base type int)
// Per_condition: contains TargetClass.TargetInner parameter (holds target_inner_rec)
public int SourceClass(TargetClass.TargetInner targetInnerParam) throws IOException {
// Super constructor invocation (implicit: calls Object constructor)
super();

// Type declaration statement
OthersClass others = new OthersClass("test-others-data");
TargetClass.TargetInnerRec targetRec = targetInnerParam.getInnerRec();
int validCount = SourceStaticNested.validateCount(targetRec.recCount());

// Exception handling statements: requires_try_catch (IOException)
try {
// Accessor method call (1 others_class accessor, super.methodName())
others.setOthersField(anonOthers.getOthersField()); // others_class accessor (setter)
String othersData = others.getOthersField(); // others_class accessor (getter)
TargetClass targetSuper = new TargetClass() { // Extend target super class
@Override
public void processRec(TargetInnerRec rec) {
super.addSuperData(rec.recId()); // super.methodName(): call TargetSuperClass method
}
};
targetSuper.processRec(targetRec);
List<String> superList = targetSuper.getSuperDataList(); // super accessor (getter)

// Synchronized statement
lock.lock();
try {
superList.add(othersData);
baseResult = superList.size() + validCount;
} finally {
lock.unlock();
}

// Expression statement: update instance field
baseResult += 5;

} catch (NullPointerException e) {
// Rethrow checked exception: requires_try_catch
throw new IOException("Null data in target inner param", e);
}

// Variable call: use target inner param method
String recId = targetInnerParam.getInnerRec().recId();
baseResult += recId.length();

// Return base type
return baseResult;
}

// Implement DataInitializer interface method
@Override
public int initDataCount() {
return baseResult;
}

// Method to trigger constructor (expose logic)
public int createBaseResult(TargetClass.TargetInner targetInner) throws IOException {
return new SourceClass(targetInner); // Call constructor to be moved
}
}

// Test entry point
class ConstructorMoveTest {
public static void main(String[] args) {
// Initialize target inner record and inner class
TargetClass.TargetInnerRec testRec = new TargetClass.TargetInnerRec("rec-41779", 3);
TargetClass target = new TargetClass() {
@Override
public void processRec(TargetInnerRec rec) {
super.addSuperData(rec.recId() + "_processed");
}
};
TargetClass.TargetInner targetInner = target.new TargetInner(testRec);

// Initialize source
SourceClass source = new SourceClass();

// Trigger constructor (handle requires_try_catch)
try {
int baseResult = source.createBaseResult(targetInner);
System.out.println("Constructor base type result: " + baseResult);
System.out.println("Interface init count: " + source.initDataCount());
} catch (IOException e) {
System.err.println("Constructor failed: " + e.getMessage());
}
}
}