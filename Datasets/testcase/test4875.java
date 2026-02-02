import java.util.List;
import java.util.Collection;
import java.util.function.Function;

// Interface for target enum to implement
interface DataConverter {
Object convert(String input);
}

// Subclass for call_method feature
class TargetSubClass extends TargetEnum<String> {
public TargetSubClass(String name, int ordinal, String data) {
super(name, ordinal, data);
}

// Protected static method (call_method: sub_class, protected, static)
protected static Object processItem(String item) {
return item.toUpperCase();
}
}

// Target public enum class (with type parameter, implements interface, static nested class)
public enum TargetEnum<T> implements DataConverter {
TARGET_A("data-a"),
TARGET_B("data-b"),
TARGET_C("data-c");

// Static nested class (target_feature)
public static class TargetStaticNested {
public static <E> List<E> createList(E... elements) {
List<E> list = new ArrayList<>();
for (E elem : elements) {
list.add(elem);
}
return list;
}
}

// Target inner record (target_inner_rec: target class for method move)
public record TargetInnerRec(String id, List<String> values) {}

protected T targetData;

TargetEnum(T data) {
this.targetData = data;
}

public T getTargetData() {
return targetData;
}

@Override
public Object convert(String input) {
return input + "_converted";
}
}

// Parent class for source enum (source_class feature: extends)
class EnumParent {
protected String parentField = "parent-base";

protected EnumParent() {}
}

// Source default enum class (same package, extends parent, with 2 local inner classes)
enum SourceEnum extends EnumParent {
SOURCE_X,
SOURCE_Y,
SOURCE_Z;

// Instance field (for access_instance_field feature)
private int sourceInstanceField = 100;

// Source inner class (source_inner: method_position)
public class SourceInner {
// Instance method to be moved (public access, return List<String>)
// Per_condition: contains TargetEnum.TargetInnerRec parameter
public List<String> process(TargetEnum.TargetInnerRec targetRecParam) {
// Super constructor invocation (implicitly calls Enum and EnumParent constructors)
super.parentField = "updated-parent";

// Type declaration statement
List<String> resultList = new ArrayList<>();
TargetEnum.TargetStaticNested.createList("init");

// Uses_outer_this: access source enum instance via qualified 'this'
resultList.add("Source enum: " + SourceEnum.this.name());
resultList.add("Parent field: " + SourceEnum.this.parentField);

// Access_instance_field: access source enum's instance field
resultList.add("Source instance: " + SourceEnum.this.sourceInstanceField);

// If statement
if (targetRecParam.values().isEmpty()) {
// Throw statement
throw new IllegalArgumentException("Target rec values cannot be empty");
}

// Enhanced for statement
for (String value : targetRecParam.values()) {
resultList.add("Value: " + value);
}

// Call_method: sub_class static method via method reference in collection operation
Function<String, Object> processor = TargetSubClass::processItem;
targetRecParam.values().forEach(item ->
resultList.add(processor.apply(item).toString())
);

// Assert statement
assert resultList.size() > targetRecParam.values().size() : "List not populated";

// Empty statement
;

// Variable call: use target parameter and its methods
resultList.add("Target rec id: " + targetRecParam.id());

// No_new_exception: no new checked/unchecked exceptions thrown
return resultList;
}
}

// Method with 1st local inner class (source_class feature)
public void useLocalInner1() {
class LocalInner1 {
void execute() {
SourceInner inner = new SourceInner();
TargetEnum.TargetInnerRec testRec = new TargetEnum.TargetInnerRec("test-1",
TargetEnum.TargetStaticNested.createList("a", "b"));
List<String> results = inner.process(testRec); // Variable call: invoke method to be moved
System.out.println("Local1 results: " + results.size());
}
}
new LocalInner1().execute();
}

// Method with 2nd local inner class (source_class feature)
public void useLocalInner2() {
class LocalInner2 {
List<String> getDefaultData() {
return TargetEnum.TargetStaticNested.createList("default-1", "default-2");
}
}
SourceInner inner = new SourceInner();
TargetEnum.TargetInnerRec rec = new TargetEnum.TargetInnerRec("test-2", new LocalInner2().getDefaultData());
inner.process(rec); // Variable call: invoke method to be moved
}
}