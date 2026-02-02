import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List;
// Target class with anonymous inner class
public class TargetClass {
// Target inner record (target_inner_rec)
public record TargetInnerRec(String id, List<String> data) {}

// Anonymous inner class
private final Runnable anonymousAction = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous action executed");
}
};

public void triggerAnonymous() {
anonymousAction.run();
}
}

// Source class with static nested & member inner classes
public class SourceClass {
// Field of target class (per_condition: source contains target field)
private TargetClass targetField;
// Field of target inner record
private TargetClass.TargetInnerRec targetInnerRecField;

// Static nested class
public static class SourceStaticNested {
public static String processData(String input) {
return input.toUpperCase();
}
}

// Member inner class
public class SourceMemberInner {
public List<String> generateData(int count) {
List<String> data = new ArrayList<>();
for (int i = 0; i < count; i++) {
data.add("item-" + i);
}
return data;
}
}

// Instance method to be moved (return TargetClas Type, public access)
public TargetClass.TargetInnerRec createTargetInnerRec(int maxItems) {
// Type declaration statement
SourceMemberInner memberInner = new SourceMemberInner();
List<String> rawData = memberInner.generateData(5);
List<String> filteredData = new ArrayList<>();

// Variable call & break statement
for (String item : rawData) {
if (filteredData.size() >= maxItems) {
break; // Break statement
}
String processed = SourceStaticNested.processData(item); // Variable call (static nested method)
filteredData.add(processed);
}

// Constructor invocation (target inner record constructor)
TargetClass.TargetInnerRec newInnerRec = new TargetClass.TargetInnerRec("rec-" + System.currentTimeMillis(), filteredData);

// Throw statement (condition-based)
if (newInnerRec.data().isEmpty()) {
throw new IllegalArgumentException("TargetInnerRec data cannot be empty");
}

// Used by reflection (feature: used_by_reflection)
try {
Method reflectMethod = TargetClass.TargetInnerRec.class.getMethod("data");
Object reflectedData = reflectMethod.invoke(newInnerRec);
if (!(reflectedData instanceof List)) {
throw new IllegalStateException("Reflection failed: invalid data type");
}
} catch (Exception e) {
// No new exception (feature: no_new_exception)
e.printStackTrace();
}

targetInnerRecField = newInnerRec;
return newInnerRec;
}

// Setter for target field
public void setTargetField(TargetClass targetField) {
this.targetField = targetField;
}
}