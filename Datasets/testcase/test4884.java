import java.util.ArrayList; import java.util.List;
/**

Target private normal class with javadoc and anonymous inner class
(target_inner_rec: contains inner record for method target)
*/
private class TargetClass {
// Anonymous inner class (target_feature)
private final Runnable targetAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous action: " + targetProtectedField);
}
};
// Protected field for access_outer_protected (referenced via source outer)
protected String targetProtectedField = "target-protected-data";
// Target inner record (target_inner_rec: target class for method move)
public record TargetInnerRec(String recId, List<String> recData) {}
// Accessor method for target inner record
public TargetInnerRec getTargetInnerRec() {
return new TargetInnerRec("target-rec-1", new ArrayList<>());
}
}

// Source normal class (default modifier, same package, with anonymous & static nested classes)
class SourceClass {
// Protected outer field for access_outer_protected
protected int sourceOuterInt = 10;

// Anonymous inner class (source_class feature)
private final Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous action: " + sourceOuterInt);
}
};

// Static nested class (source_class feature)
public static class SourceStaticNested {
private String nestedData;

public SourceStaticNested(String data) {
this.nestedData = data;
}

// Accessor method (getter) for source feature
public String getNestedData() {
return nestedData;
}

// Chainable accessor methods for obj.m1().m2().m3()
public SourceStaticNested setNestedData(String data) {
this.nestedData = data;
return this;
}

public int getNestedLength() {
return nestedData.length();
}
}

// Source inner record (source_inner_rec: method_position)
public record SourceInnerRec(String sourceRecId, TargetClass.TargetInnerRec targetInnerRecParam) {
// Recursive method to be moved (final access, return TargetClas Type)
// Per_condition: contains TargetClass.TargetInnerRec parameter (targetInnerRecParam)
public final TargetClass.TargetInnerRec recursiveBuild(int depth) {
// Uses_outer_this: access source outer class instance via qualified 'this'
int outerVal = SourceClass.this.sourceOuterInt;

// Variable call: invoke source anonymous inner class
SourceClass.this.sourceAnonymous.run();

// Do-while loop containing accessor method chain (obj.m1().m2().m3())
SourceStaticNested nested = new SourceStaticNested("init-data");
int chainResult;
do {
// 1 source accessor method chain (obj.m1().m2().m3())
chainResult = nested.setNestedData("depth-" + depth) // m1: accessor (setter)
.getNestedData() // m2: accessor (getter)
.length(); // m3: implicit accessor (String.length())
} while (chainResult < 5);

// Continue statement in loop
List<String> tempData = new ArrayList<>();
for (int i = 0; i < depth; i++) {
if (i % 2 == 0) {
continue; // Skip even indices
}
tempData.add("item-" + i + "-" + outerVal);
}

// Access_outer_protected: access target class's protected field via source outer
String targetProtected = SourceClass.this.getTargetProtectedField();
tempData.add(targetProtected);

// Variable call: use target inner record parameter
TargetClass.TargetInnerRec current = this.targetInnerRecParam;
List<String> newData = new ArrayList<>(current.recData());
newData.addAll(tempData);

// Recursive base case
if (depth <= 0) {
// No_new_exception: no new checked/unchecked exceptions thrown
return new TargetClass.TargetInnerRec(current.recId(), newData);
}

// Recursive call
return recursiveBuild(depth - 1);
}
}

// Helper method to access target's protected field (for access_outer_protected)
private TargetClass targetField = new TargetClass();
public String getTargetProtectedField() {
return targetField.targetProtectedField;
}

// Method to instantiate source inner record and trigger recursive method
public TargetClass.TargetInnerRec startRecursiveBuild() {
TargetClass.TargetInnerRec initialTargetRec = targetField.getTargetInnerRec();
SourceInnerRec sourceRec = new SourceInnerRec("source-rec-1", initialTargetRec);
return sourceRec.recursiveBuild(3);
}
}