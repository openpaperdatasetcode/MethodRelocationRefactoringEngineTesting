import java.util.ArrayList; import java.util.List;
// Target normal class (default modifier, with static nested class)
class TargetClass {
// Static nested class (target_feature)
public static class TargetStaticNested {
private String nestedData;

public TargetStaticNested(String data) {
this.nestedData = data;
}

// Method for variable call feature
public String getFormattedData() {
return "TargetNested: " + nestedData;
}
}

private int targetValue;

public TargetClass(int value) {
this.targetValue = value;
}

public int getTargetValue() {
return targetValue;
}
}

// Source private normal class (same package, with type parameter & inner classes)
private class SourceClass<T> {
// Static nested class (source_class feature)
public static class SourceStaticNested {
public static <E> List<E> createList(E... elements) {
List<E> list = new ArrayList<>();
for (E elem : elements) {
list.add(elem);
}
return list;
}
}

// Member inner class (source_class feature)
public class SourceMemberInner {
private T innerData;

public SourceMemberInner(T data) {
this.innerData = data;
}

public String getInnerInfo() {
return "InnerData: " + innerData.toString();
}
}

// Source inner record (source_inner_rec: method_position)
public record SourceInnerRec(String recId, TargetClass targetParam) { // per_condition: contains target parameter
// Instance method to be moved (public access, return Object)
public Object processData(T value) {
// Super constructor invocation (record implicitly calls Record super constructor)
// Explicit reference to superclass method to satisfy feature
String recSuperName = super.toString();

// Variable call: use source static nested class
List<T> dataList = SourceStaticNested.createList(value);
// Variable call: use source member inner class
SourceMemberInner inner = new SourceClass<T>().new SourceMemberInner(value);
dataList.add((T) inner.getInnerInfo());

// Do statement
int count = 0;
String nestedResult = "";
do {
// Variable call: use target static nested class
TargetClass.TargetStaticNested targetNested = new TargetClass.TargetStaticNested(recId + "-" + count);
nestedResult = targetNested.getFormattedData();
dataList.add((T) nestedResult);
count++;
} while (count < targetParam.getTargetValue());

// For loop with break & continue statements
for (int i = 0; i < dataList.size(); i++) {
Object elem = dataList.get(i);
if (elem == null) {
continue; // Continue statement: skip null elements
}
if (elem.toString().contains("TargetNested")) {
break; // Break statement: stop at first target nested element
}
}

// No_new_exception: no new checked/unchecked exceptions thrown
return dataList;
}
}

// Method to instantiate source inner record
public SourceInnerRec createSourceInnerRec(String recId, TargetClass target) {
return new SourceInnerRec(recId, target);
}
}