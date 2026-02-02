package test;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// Source: final normal class with permits (via sealed parent), anonymous inner, member inner
final class SourceClass extends SealedParentClass {
private String sourceInstanceField = "source_instance_data"; // For access_instance_field

// Member inner class
class SourceMemberInner {
private String innerValue;

public SourceMemberInner(String value) {
this.innerValue = value;
}

public String getInnerValue() {
return innerValue;
}
}

// Varargs method to be refactored
private List<String> varargsMethod(TargetClass.TargetInnerRec targetInnerRec, String... args) {
List<String> result = new ArrayList<>();

// Variable call (use target parameter)
result.add(targetInnerRec.getRecValue());
// Access instance field
result.add(this.sourceInstanceField);

// Labeled statement
loopLabel:
for (String arg : args) {
if (arg.isEmpty()) {
break loopLabel;
}
result.add(arg);
}

// Lambda expression: () -> System.out.println(this.value)
Supplier<Void> targetLambda = () -> {
System.out.println(this.sourceInstanceField); // this.value (source instance field)
System.out.println(targetInnerRec.getRecValue()); // Target inner value
return null;
};
targetLambda.get();

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
result.add("anonymous_inner_executed: " + targetInnerRec.getRecValue());
}
};
anonTask.run();

// Override violation (method with same signature as Object but incorrect return type)
result.add(toString(targetInnerRec));

return result;
}

// Override violation: Object.toString() returns String, this accepts parameter (signature mismatch)
private String toString(TargetClass.TargetInnerRec target) {
return "SourceClass[target=" + target.getRecValue() + "]";
}
}

// Sealed parent class for SourceClass's "permits" feature
sealed class SealedParentClass permits SourceClass {}

// Target: public normal class with no target features
public class TargetClass {
// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private String recValue;

public TargetInnerRec(String value) {
this.recValue = value;
}

public String getRecValue() {
return recValue;
}

public void setRecValue(String value) {
this.recValue = value;
}
}
}