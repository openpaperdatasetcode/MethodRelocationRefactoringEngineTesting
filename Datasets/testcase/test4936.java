package test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

interface GenericInterface<T> {
T process(T value);
}

sealed class ParentGenericClass<T> permits SourceClass {
protected T parentField;

public ParentGenericClass(T value) {
this.parentField = value;
}
}

class SourceClass<T> extends ParentGenericClass<T> implements GenericInterface<T> {
public SourceClass(T value) {
super(value);
}

@Override
public T process(T value) {
return value;
}

protected Object instanceMethod(TargetClass<T> target) {
// Super constructor invocation
ParentGenericClass<String> parent = new ParentGenericClass<>("parent");

// Type declaration statement
List<T> items = new ArrayList<>();
T data = target.getData();

// Variable call
items.add(target.getData());
items.add(this.parentField);

// Empty statement
;

// Used by reflection
try {
Method method = TargetClass.class.getMethod("getData");
Object reflectedData = method.invoke(target);
items.add((T) reflectedData);
} catch (Exception e) {
// No new exception
}

// Call target method in for loop
for (T item : items) {
Object result = target.new TargetInner().overrideMethod(item);
}

return items;
}
}

protected class TargetClass<T> extends ParentGenericClass<T> {
public TargetClass(T value) {
super(value);
}

public T getData() {
return parentField;
}

public class TargetInner {
public Object overrideMethod(T value) {
return value.toString();
}
}
}