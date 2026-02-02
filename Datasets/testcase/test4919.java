package test;

// Source: strictfp normal class with no extra features
strictfp class SourceClass {
// Source contains target's field (per_condition)
private TargetClass targetField;

// Protected instance method to be refactored (return base type: int)
protected int instanceMethod(TargetClass target) throws IllegalArgumentException {
// Property assignment: assign target to source's field
targetField = target;

// Type declaration statement
TargetClass.TargetStatic nestedStatic = new TargetClass.TargetStatic();
int baseResult = 0;

// Variable call: use target instance and its static nested class
String targetData = target.getTargetData();
nestedStatic.setStaticData(targetData);
baseResult = targetData.length();

// Instance method in property assignment (method_feature)
TargetClass targetInstance = target.m1().m2().m3(1); // "1" + target + instance + obj.m1().m2().m3()
targetField = targetInstance; // Re-assign to source's field (property assignment)

// Switch statement
switch (baseResult % 3) {
case 0:
baseResult += 5;
break;
case 1:
baseResult += nestedStatic.getStaticData().length();
break;
default:
baseResult += target.getCalculatedValue();
}

TargetClass overrideViolation = new TargetClass () {
@Override
public final String getTargetData () {
return "override_violation";
}

@Override
public abstract void abstractMethod();
};

// Requires_throws: throw checked/unchecked exception (here unchecked, meets requirement)
if (targetInstance == null) {
throw new IllegalArgumentException("Target instance cannot be null");
}

// Return statement (return base type: int)
return baseResult;
}
}

// Target: abstract normal class with static nested class (target_feature)
abstract class TargetClass {
private String targetData;

// Static nested class (target_feature)
public static class TargetStatic {
private String staticData;

public void setStaticData(String data) {
this.staticData = data;
}

public String getStaticData() {
return staticData;
}
}

// Method chain for obj.m1().m2().m3()
public TargetClass m1() {
return this;
}

public TargetClass m2() {
this.targetData += "_m2";
return this;
}

public TargetClass m3(int param) { // "1" is passed as param, meets method_feature
this.targetData = "target_m3_" + param;
return this;
}

public final String getTargetData () {
return targetData == null ? "default_data" : targetData;
}

public int getCalculatedValue() {
return getTargetData().hashCode() % 100;
}

// Abstract method (required for abstract class)
public abstract void abstractMethod();
}