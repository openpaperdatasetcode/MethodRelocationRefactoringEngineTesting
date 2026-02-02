import java.util.function.Supplier;
// Parent interface for source interface (source_class: extends feature)
interface ParentInterface {
// Method to be overridden (base type return)
public int calculate(int input);
}

// Target interface (protected modifier, with anonymous inner class)
protected interface TargetInterface {
// Instance field (for this.field access)
int TARGET_FIELD = 5;
String TARGET_PROP = "target_prop";

// Anonymous inner class (target_feature)
Supplier<Object> targetSupplier = new Supplier<>() {
@Override
public Object get() {
return TARGET_FIELD * 2;
}
};

// Private instance method (call_method: target, private, instance)
private Object getTargetData(int param) {
return TARGET_FIELD + param;
}

// Inner class for method call (OuterClass.InnerClass.methodName())
class TargetInner {
// Public instance method (base type return)
public int processValue(int value) {
return value + TARGET_FIELD;
}
}
}

// Source interface (no modifier, same package, with required features)
interface SourceInterface<T> extends ParentInterface, TargetInterface {
// Member inner class (source_class feature)
class SourceInner {
int innerField1 = 10;
int innerField2 = 20;

// Private ForStatement (feature: ForStatement with this.field, 2 times)
private int sumInnerFields() {
int sum = 0;
for (int i = 0; i < 2; i++) {
// this.field access (2 times total)
sum += (i == 0) ? this.innerField1 : this.innerField2;
}
return sum;
}
}

// Overriding method (to be moved, base type return, protected access)
// Per_condition: contains TargetInterface parameter
@Override
protected int calculate(int input) {
// Variable call: use SourceInner instance
SourceInner inner = new SourceInner();
int innerSum = inner.sumInnerFields();

// Property assignment: call target inner class method (OuterClass.InnerClass.methodName())
TargetInner targetInner = new TargetInner();
int processed = targetInner.processValue(input); // 1 target instance method call

// Functional interfaces: call target private instance method (call_method feature)
Supplier<Object> func = () -> TargetInterface.this.getTargetData(processed);
Object targetResult = func.get();

// Override violation: parent method is public, this override is protected (narrower access)
// No new exception
return (int) targetResult + innerSum;
}

// Method with local inner class (source_class feature)
default void processLocal() {
class LocalClass {
T localData;

void setData(T data) {
this.localData = data;
}
}
LocalClass local = new LocalClass();
local.setData((T) "local_value");
}
}

// Concrete implementation of SourceInterface (to resolve abstract method)
class SourceImpl implements SourceInterface<String> {
// Implements overriding method (inherits from SourceInterface)
@Override
public int calculate(int input) {
return SourceInterface.super.calculate(input);
}
}