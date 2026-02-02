package test;

import java.util.List;
import java.util.ArrayList;

// Source: final generic class with local inner class and static nested class
final class SourceClass<T> {
// Source contains target's field (per_condition)
private TargetClass<T>.TargetInner targetField;

// Static nested class
static class SourceStaticNested {
private U nestedData;

public SourceStaticNested(U data) {
this.nestedData = data;
}

public U getNestedData() {
return nestedData;
}
}

// Recursive inner class (source_inner_rec)
public class SourceInnerRec {
// strictfp normal method to be refactored
public strictfp void normalMethod(TargetClass<T>.TargetInner targetInner) {
// Type declaration statement
SourceStaticNested<Character> charStatic = new SourceStaticNested<>('A'); // CharacterLiteral
List<T> dataList = new ArrayList<>();

// Empty statement
;

// "numbers": "3" feature (use number 3 in logic)
for (int i = 0; i < 3; i++) {
dataList.add(targetInner.getInnerValue()); // Variable call
}

// Variable call (use target parameter & source's targetField)
targetField = targetInner;
T targetData = targetField.getInnerValue();
System.out.println("Target inner data: " + targetData);

// CharacterLiteral usage (from static nested class)
Character literalChar = charStatic.getNestedData();
System.out.println("Character literal: " + literalChar);

// Local inner class (source_feature)
class SourceLocalInner {
void processList(List<T> list) {
for (T item : list) {
System.out.println("List item: " + item);
}
}
}
new SourceLocalInner().processList(dataList);
}
}
}

// Target: protected generic class with member inner class
protected class TargetClass<V> {
// Member inner class (target_inner, target_feature)
public class TargetInner {
private V innerValue;

public TargetInner(V value) {
this.innerValue = value;
}

// Getter for variable call
public V getInnerValue() {
return innerValue;
}

public void setInnerValue(V value) {
this.innerValue = value;
}
}

// Helper method to create TargetInner instance
public TargetInner createTargetInner(V value) {
return new TargetInner(value);
}
}