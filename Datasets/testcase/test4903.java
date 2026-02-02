package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Parent class for source's "extends" feature
class SourceParentClass {
protected String parentProtectedField = "source_parent_data";

public List<String> parentMethod(int count) {
List<String> result = new ArrayList<>();
for (int i = 0; i < count; i++) {
result.add("parent_item_" + i);
}
return result;
}
}

// Parent class for target's "extends" feature
class TargetParentClass {
protected int targetParentCount = 3; // "3" aligns with target_feature
}

// Source: public normal class (extends SourceParentClass) with 2 static nested classes
public class SourceClass extends SourceParentClass {
// Static nested class 1 (source_feature)
public static class SourceStaticOne {
public static final String STATIC_FIELD_1 = "static_one_data"; // For depends_on_static_field
}

// Static nested class 2 (source_feature)
public static class SourceStaticTwo {
public static List<String> staticProcess(List<String> list) {
List<String> result = new ArrayList<>();
for (String s : list) {
result.add("static_two_" + s);
}
return result;
}
}

// Source inner class (source_inner) - contains synchronized constructor to be refactored
public class SourceInner {
// Instance field: contains target's field (satisfies per_condition)
private TargetClass.TargetInnerRec targetInnerField;

// 1. Synchronized constructor (method: type=constructor, access_modifier=synchronized)
public synchronized SourceInner(TargetClass target, int initParam) throws IllegalArgumentException {
// Step 1: Variable call - use target parameter to create inner recursive class
this.targetInnerField = target.createInnerRec(initParam);
String innerData = targetInnerField.getInnerData();
System.out.println("TargetInnerRec initialized with: " + innerData);

// Step 2: VariableDeclarationStatement (modifier: static, target_feature: obj.field, "3", pos: source)
// Static variable declaration (uses target inner class field + "3")
static int staticDeclaredVar = targetInnerField.getInnerCount() + 3; // "3" + obj.field (targetInnerField.getInnerCount())
System.out.println("Static declared variable (with '3'): " + staticDeclaredVar);

// Step 3: depends_on_static_field - use source static nested class's static field
String staticData = SourceStaticOne.STATIC_FIELD_1;
targetInnerField.addToInnerList(staticData);
System.out.println("Added static field to target inner list: " + staticData);

// Step 4: while statement - process target inner class's list
int whileIndex = 0;
while (whileIndex < targetInnerField.getInnerListSize()) {
String item = targetInnerField.getInnerList().get(whileIndex);
System.out.println("While processing: " + item);
whileIndex++;
}

// Step 5: overloading method in functional interfaces (method_feature)
// public modifier (via overloaded method) + "3" + target + this.methodName(arguments)
Supplier<List<String>> funcSupplier = () -> this.overloadMethod(target, 3); // "3" + target
List<String> overloadResult = funcSupplier.get();
System.out.println("Overload method result (functional interface): " + overloadResult);

// Step 6: requires_throws - throw exception for invalid target inner state
if (targetInnerField.getInnerListSize() == 0) {
throw new IllegalArgumentException("TargetInnerRec list cannot be empty");
}
}

// 2. Overloaded method 1 (method_feature: type=overloading, modifier=public)
public List<String> overloadMethod(TargetClass target, int param) {
List<String> result = targetInnerField.getInnerList();
// this.methodName(arguments) - call overloaded method 2
result.addAll(this.overloadMethod(target, param, "overload_suffix"));
// Use source static nested class 2 to process result
return SourceStaticTwo.staticProcess(result);
}

// 3. Overloaded method 2 (overload_exist feature)
public List<String> overloadMethod(TargetClass target, int param, String suffix) {
List<String> result = new ArrayList<>();
String item = target.getTargetBaseData() + "_" + param + suffix; // "3" in param
result.add(item);
return result;
}

// Getter for target inner field (for verification)
public TargetClass.TargetInnerRec getTargetInnerField() {
return targetInnerField;
}
}

// Helper method to create SourceInner instance (for external access)
public SourceInner createSourceInner(TargetClass target, int initParam) throws IllegalArgumentException {
return new SourceInner(target, initParam);
}
}

// Target: private normal class (extends TargetParentClass) with member inner class (target_inner_rec)
class TargetClass extends TargetParentClass {
private String targetBaseData = "target_base_data";

// Member inner class (target_inner_rec: recursive inner class, target_feature: extends)
public class TargetInnerRec {
private String innerData;
private int innerCount;
private List<String> innerList;
private TargetInnerRec nextInner; // Recursive reference

// Constructor - supports recursion
public TargetInnerRec(String data, int count) {
this.innerData = data;
this.innerCount = count;
this.innerList = new ArrayList<>();
// Recursive initialization (if count > 1)
if (count > 1) {
this.nextInner = new TargetInnerRec(data + "_rec", count - 1);
}
}

// Add item to inner list (for variable call)
public void addToInnerList(String item) {
this.innerList.add(item);
}

// Getters for variable call
public String getInnerData() {
return innerData;
}

public int getInnerCount() {
return innerCount;
}

public List<String> getInnerList() {
return new ArrayList<>(innerList);
}

public int getInnerListSize() {
return innerList.size();
}

public TargetInnerRec getNextInner() {
return nextInner;
}
}

// Helper method to create TargetInnerRec instance (for source's variable call)
public TargetInnerRec createInnerRec(int count) {
return new TargetInnerRec(targetBaseData + "_inner", count);
}

// Getter for target base data (for variable call)
public String getTargetBaseData() {
return targetBaseData;
}
}
