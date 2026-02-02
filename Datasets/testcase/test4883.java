import java.lang.annotation.ElementType; import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.lang.annotation.Target; import java.util.ArrayList; import java.util.List;
// Custom annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RecursiveRefactorTest {
String purpose() default "Recursive method for Move refactoring validation";
}

// Target abstract record class (with static nested class)
abstract record TargetRecord(String targetId, int targetDepth) {
// Static nested class (target_feature)
public static class TargetStaticNested {
private String nestedContent;

public TargetStaticNested(String content) {
this.nestedContent = content;
}

// Method for variable call & depends_on_inner_class features
public String getFormattedContent() {
return "[" + nestedContent + "]";
}
}

// Abstract method (required for abstract record)
public abstract List<String> collectTargetData();
}

// Source public record class (same package, with anonymous & static nested classes)
public record SourceRecord(String sourceName, TargetRecord targetField) { // per_condition: source contains target field
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Method for variable call feature
public static List<String> initList(int size) {
List<String> list = new ArrayList<>();
for (int i = 0; i < size; i++) {
list.add("init-item-" + i);
}
return list;
}
}

// Anonymous inner class (source_class feature)
private final Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous: Processing " + sourceName);
}
};

// Recursive method (private access, return List<String>)
@RecursiveRefactorTest(purpose = "Recursive data collection method")
private List<String> recursiveCollect(int currentDepth) {
// Variable call: invoke anonymous inner class
sourceAnonymous.run();

// Variable call: use source static nested class method
List<String> collected = SourceStaticNested.initList(2);

// 1 ClassInstanceCreation (default modifier, exp: ClassInstanceCreation feature)
TargetRecord.TargetStaticNested targetInner = new TargetRecord.TargetStaticNested(sourceName + "-depth-" + currentDepth);

// Depends_on_inner_class: rely on target static nested class method
collected.add(targetInner.getFormattedContent());

// Raw_type: use raw List type (no generic parameter)
List rawList = new ArrayList();
rawList.add(targetField.targetId());
collected.addAll(rawList); // Unchecked conversion (raw type usage)

// Variable call: use target field's data
collected.add("Target ID: " + targetField.targetId());

// Recursive base case
if (currentDepth >= targetField.targetDepth()) {
// No_new_exception: no new checked/unchecked exceptions thrown
return collected;
}

// Recursive call
List<String> childCollected = recursiveCollect(currentDepth + 1);
collected.addAll(childCollected);

return collected;
}

// Public method to trigger recursion
public List<String> startCollection() {
return recursiveCollect(0);
}
}