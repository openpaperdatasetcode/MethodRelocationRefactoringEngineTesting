package source.pkg;

import target.pkg.TargetClass;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

// Source: protected normal class in different package with 2 local inner classes
protected class SourceClass extends ParentSourceClass {
// Source contains target's field (per_condition)
private TargetClass.TargetInnerRec targetField;

// Instance method to be refactored
List<String> instanceMethod(TargetClass.TargetInnerRec targetParam) {
// Variable call (use target parameter)
this.targetField = targetParam;
List<String> result = new ArrayList<>();

// Type declaration statement
RawTypeHolder rawHolder = new RawTypeHolder();
rawHolder.rawList.add(targetParam.getInnerData()); // Raw type usage

// Super keywords (call parent class method)
result.add(super.parentMethod("source_super_call"));

// First local inner class
class LocalProcessorOne {
String process(TargetClass.TargetInnerRec inner) {
return "processed_one:" + inner.getInnerData();
}
}
result.add(new LocalProcessorOne().process(targetField));

// Array initialization with overriding method (pos: array initialization)
DataHandler[] handlers = new DataHandler[]{
new DataHandler() {
// Synchronized overriding method
@Override
public synchronized List<String> handle(TargetClass.TargetInnerRec target) {
List<String> data = new ArrayList<>();
data.add(target.getInnerData() + "_override1");
data.add(this.helperMethod(target)); // this.methodName(arguments)
return data;
}

private String helperMethod(TargetClass.TargetInnerRec target) {
return "helper:" + target.getInnerData() + "_1"; // "1" in method_feature
}
},
new DataHandler() {
@Override
public synchronized List<String> handle(TargetClass.TargetInnerRec target) {
List<String> data = new ArrayList<>();
data.add(target.getInnerData() + "_override2");
return data;
}
}
};

// Invoke overriding methods from array
for (DataHandler handler : handlers) {
result.addAll(handler.handle(targetField));
}

// Second local inner class
class LocalProcessorTwo {
void appendData(List<String> list) {
list.add("processed_two:" + targetField.getInnerData());
}
}
new LocalProcessorTwo().appendData(result);

return result;
}

// Interface for overriding
private interface DataHandler {
List<String> handle(TargetClass.TargetInnerRec target);
}

// Raw type helper class
private class RawTypeHolder {
Collection rawList = new ArrayList(); // Raw type
}
}

// Parent class for source's super keywords
class ParentSourceClass {
protected String parentMethod(String arg) {
return "parent_result:" + arg;
}
}

package target.pkg;

import java.util.List;

// Target: normal class with local inner class (target_feature)
class TargetClass {
// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private String innerData;
private int recursiveCount;

public TargetInnerRec(String data, int count) {
this.innerData = data;
this.recursiveCount = count;
// Local inner class (target_feature)
class InnerInitializer {
void init() {
TargetInnerRec.this.innerData = "init_" + data;
}
}
new InnerInitializer().init();
}

public String getInnerData() {
return innerData;
}

public int getRecursiveCount() {
return recursiveCount;
}

public void setRecursiveCount(int count) {
this.recursiveCount = count;
}
}
}