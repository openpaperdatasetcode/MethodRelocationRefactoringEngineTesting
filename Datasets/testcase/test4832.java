package test.refactoring;
import other.package.OtherDiffPackageClass;
import java.util.ArrayList;
import java.util.List;

class ParentSourceClass<T> {
protected T parentField;

public ParentSourceClass(T parentField) {
this.parentField = parentField;
}

public T getParentField() {
return parentField;
}
}

protected class SourceClass<T extends Number> extends ParentSourceClass<T> {
private TargetClass<T> targetField;

static class SourceStaticNested {
private U nestedData;

public SourceStaticNested(U nestedData) {
this.nestedData = nestedData;
}

public U getNestedData() {
return nestedData;
}
}

class SourceMemberInner {
public String innerMethod() {
return "SourceInner:" + parentField.toString();
}
}

public SourceClass(T parentField, TargetClass<T> targetField) {
super(parentField);
this.targetField = targetField;
}

protected TargetClass<T> overloadedMethod(T param) {
if (param == null) {
throw new NullPointerException("Param cannot be null");
}
if (targetField == null) {
throw new NullPointerException("TargetField cannot be null");
}

SourceMemberInner inner = new SourceMemberInner();
SourceStaticNested<String> staticNested = new SourceStaticNested<>("static_data");

List rawCollection = new ArrayList();
rawCollection.add(param);
rawCollection.add(inner.innerMethod());

final Object lock = new Object();
synchronized (lock) {
targetField.targetMemberInner.updateData(staticNested.getNestedData());
}

try {
OtherDiffPackageClass diffPackageObj = new OtherDiffPackageClass();
return returnFromDiffPackage(diffPackageObj, param);
} catch (ClassCastException e) {
e.printStackTrace();
return new TargetClass<>(param);
}
}

protected TargetClass<T> overloadedMethod(T param1, T param2) {
T combinedParam = (T) Double.valueOf(param1.doubleValue() + param2.doubleValue());
return overloadedMethod(combinedParam);
}

private TargetClass<T> returnFromDiffPackage(OtherDiffPackageClass diffObj, T param) {
T conditionalVal = diffObj.isValid(param) ? param : parentField;
return new TargetClass<>(conditionalVal);
}

private void variableCall(TargetClass<T> target) {
System.out.println("Variable call: " + target.getTargetData());
}
}

protected class TargetClass<T> {
private T targetData;

class TargetMemberInner {
private String innerData;

public void updateData(String data) {
this.innerData = data;
}

public String getInnerData() {
return innerData;
}
}

public TargetClass(T targetData) {
this.targetData = targetData;
}

public T getTargetData() {
return targetData;
}

public void setTargetData(T targetData) {
this.targetData = targetData;
}
}

// Diff package class (simulated separate package structure)
package other.package;

public class OtherDiffPackageClass {
public <T extends Number> boolean isValid(T value) {
return value.doubleValue() > 0;
}
}