package test;

import java.util.List;
import java.util.ArrayList;

// Source: public enum class with permits, anonymous inner, and static nested class
public sealed enum SourceEnum permits SourceEnum.Value1, SourceEnum.Value2 {
VALUE1, VALUE2;

private String outerPrivateField = "source_private"; // For access_outer_private

// Static nested class
public static class SourceStaticNested<T extends CharSequence> {
// Inner class containing abstract method (source_inner)
public abstract class SourceInner<T> {
protected T innerField;

// Super constructor invocation
protected SourceInner(T value) {
super();
this.innerField = value;
}

// Abstract method to be refactored
public abstract List<String> abstractMethod(TargetEnum.TargetInner targetInner);
}
}

// Implement VALUE1
static {
VALUE1 = new SourceEnum() {
@Override
public SourceStaticNested<String> getStaticNested() {
return new SourceStaticNested<>() {
// Concrete implementation of inner abstract class
public class ConcreteInner extends SourceInner<String> {
public ConcreteInner(String value) {
super(value);
}

@Override
public List<String> abstractMethod(TargetEnum.TargetInner targetInner) {
List<String> result = new ArrayList<>();

// Variable call (use target parameter)
result.add(targetInner.getInnerData());

// Access outer private field
result.add(SourceEnum.this.outerPrivateField);

// Access instance field
result.add(this.innerField);

// With bounds (T extends CharSequence)
result.add(this.innerField.substring(0, 2));

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Processing target inner: " + targetInner.getInnerData());
}
};
anonTask.run();

return result;
}
}
};
}
};
}

// Abstract method for permits
public abstract SourceStaticNested<String> getStaticNested();
}

// Target: protected enum class with anonymous inner class
protected enum TargetEnum {
TARGET1, TARGET2;

// Inner class (target_inner)
public class TargetInner {
private String innerData;

public TargetInner(String data) {
this.innerData = data;

// Anonymous inner class (target_feature)
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println("TargetInner anonymous class: " + innerData);
}
};
anon.run();
}

public String getInnerData() {
return innerData;
}
}
}