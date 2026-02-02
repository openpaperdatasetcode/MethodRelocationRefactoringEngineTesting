import java.util.ArrayList; import java.util.List; import java.util.function.Function;
// Super type interface for abstract method reference
interface SuperType<T> {
TargetClass processMethod(T input);
}

// Others class for call_method feature
class OthersClass {
// Private normal method (call_method: others_class, private)
private Object extractData(TargetClass target) {
return target.getTargetValue() * 2;
}
}

// Target protected normal class (no target_features)
protected class TargetClass {
private int targetValue;

public TargetClass(int value) {
this.targetValue = value;
}

public int getTargetValue() {
return targetValue;
}

public void setTargetValue(int value) {
this.targetValue = value;
}
}

// Source public normal class (same package, with member/anonymous inner classes)
public class SourceClass {
// Member inner class (source_inner: method_position)
public class SourceInner<T extends Number> { // with_bounds: type parameter bounded by Number
private T innerData;

// Super constructor invocation (implicit for inner class, explicit reference)
public SourceInner(T data) {
super(); // Explicit super constructor call for inner class
this.innerData = data;
}

// Instance method to be moved (default access, return base type int)
// Per_condition: contains TargetClass parameter
int calculate(TargetClass targetParam) {
// Variable call: use inner class field
double base = innerData.doubleValue();

// If/else conditions with 1 source abstract method call (superTypeReference.methodName)
SuperType<Double> abstractProcessor = new SuperType<Double>() {
@Override
public TargetClass processMethod(Double input) {
targetParam.setTargetValue((int) (input + base));
return targetParam;
}
};

TargetClass processedTarget;
if (base > 10.0) {
processedTarget = abstractProcessor.processMethod(20.5); // SuperTypeReference.methodName
} else {
processedTarget = abstractProcessor.processMethod(10.5); // SuperTypeReference.methodName
}

// Call_method: others_class method via method reference in for loop
OthersClass others = new OthersClass();
Function<TargetClass, Object> dataExtractor = others::extractData; // instanceReference::methodName
List<Object> results = new ArrayList<>();

for (int i = 0; i < 3; i++) { // pos: for loop
results.add(dataExtractor.apply(processedTarget));
}

// Variable call: use target parameter method
int result = processedTarget.getTargetValue() + results.size();

// No_new_exception: no new checked/unchecked exceptions thrown
return result;
}
}

// Anonymous inner class (source_class feature)
private Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
SourceInner<Integer> inner = new SourceInner<>(5);
TargetClass target = new TargetClass(10);
int calcResult = inner.calculate(target); // Variable call: invoke inner class method
System.out.println("Anonymous inner result: " + calcResult);
}
};

// Method to trigger anonymous inner class
public void startProcessing() {
sourceAnonymous.run();
}
}