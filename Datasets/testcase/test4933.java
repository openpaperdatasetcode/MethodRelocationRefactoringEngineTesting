package source.pkg;

import target.pkg.TargetClass;
import java.util.List;
import java.util.ArrayList;

// Source: default normal class in different package with local inner & static nested class
class SourceClass {
// Static nested class
static class SourceStaticNested {
private String nestedData;

public SourceStaticNested(String data) {
this.nestedData = data;
}

public SourceStaticNested m1() { return this; }
public SourceStaticNested m2() { return this; }
public String m3() { return nestedData; }
}

// Inner class (source_inner)
class SourceInner {
// Instance method to be refactored (requires_throws)
public Object instanceMethod(TargetClass<String> target) throws IllegalArgumentException {
// Variable call (use target parameter)
String targetData = target.getInnerData("param1");
Object result = targetData;

// Exception handling statements with private constructor
try {
// Private constructor (method_feature: 2, source, constructor, obj.m1().m2().m3())
LocalConstructorHelper helper = new LocalConstructorHelper(2, new SourceStaticNested("source_data"));
List<String> helperList = helper.getList();
helperList.add(target.m1().m2().m3()); // obj.m1().m2().m3()
result = helperList;
} catch (NullPointerException e) {
throw new IllegalArgumentException("Invalid target data", e); // Requires_throws
}

return result;
}

// Local inner class with private constructor
private class LocalConstructorHelper {
private int count;
private List<String> list;

// Private constructor (modifier: private)
private LocalConstructorHelper(int count, SourceStaticNested nested) {
this.count = count;
this.list = new ArrayList<>();
this.list.add(nested.m3()); // Use source static nested class
}

public List<String> getList() {
return list;
}
}
}
}

package target.pkg;

import java.util.List;
import java.util.ArrayList;

// Target: private normal class with type parameter & member inner class
private class TargetClass<T> {
private T data;

// Member inner class (target_feature)
public class TargetInner {
private T innerValue;

public TargetInner(T value) {
this.innerValue = value;
}

public T getValue() {
return innerValue;
}
}

public TargetClass(T data) {
this.data = data;
}

// Method for variable call
public T getInnerData(T param) {
return param;
}

// Method chain for obj.m1().m2().m3()
public TargetClass<T> m1() { return this; }
public TargetClass<T> m2() { return this; }
public T m3() { return data; }

// Create inner class instance
public TargetInner createInner(T value) {
return new TargetInner(value);
}
}