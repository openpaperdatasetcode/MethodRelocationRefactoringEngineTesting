import java.util.List;
// Target class (private modifier, with anonymous inner class)
private class TargetClass {
// Static field (for depends_on_static_field feature)
public static final String TARGET_STATIC_FIELD = "target_static_value";
// Instance field (for obj.field access)
protected String targetInstanceField;

public TargetClass(String fieldValue) {
this.targetInstanceField = fieldValue;
// Anonymous inner class (target_feature)
Runnable targetAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous: " + targetInstanceField);
}
};
targetAnonymous.run();
}
}

// Source class (public modifier, with member inner class & anonymous inner class)
public class SourceClass {
// Protected outer field (for access_outer_protected feature)
protected List<String> outerProtectedList;
// Instance field (for access_instance_field feature)
private int outerInstanceInt = 10;

// Anonymous inner class (source_class feature)
private Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous: " + outerInstanceInt);
}
};

// Member inner record (source_inner_rec: method_position)
public record SourceInnerRec(String id, int count) {
// Super constructor invocation (feature: super constructor invocation)
public SourceInnerRec {
super(); // Explicit super constructor call for record
}

// Abstract method to be moved (return TargetClas Type, public access)
// Per_condition: contains TargetClass parameter
public abstract TargetClass processTarget(TargetClass targetParam);

// Private DoStatement (feature: DoStatement with obj.field & count=2)
private void processFields(TargetClass target1, TargetClass target2) {
int fieldAccessCount = 0;
do {
// obj.field access (target_feature: obj.field, 2 times total)
String field1 = target1.targetInstanceField;
String field2 = target2.targetInstanceField;
fieldAccessCount++;
} while (fieldAccessCount < 2);
}

// Method with 3 SwitchExpressions (feature: numbers=3, default modifier)
default int evaluateSwitch(int input) {
// 1st SwitchExpression
int result1 = switch (input % 3) {
case 0 -> 0;
case 1 -> 1;
default -> 2;
};
// 2nd SwitchExpression
int result2 = switch (input % 2) {
case 0 -> 10;
default -> 20;
};
// 3rd SwitchExpression
return switch (result1 + result2) {
case 10, 21 -> result1;
case 11, 22 -> result2;
default -> input;
};
}

// Method with return this; (feature: return this;)
public SourceInnerRec updateCount(int newCount) {
this.count = newCount; // Variable call (feature: variable call)
return this;
}

// Method demonstrating access_outer_protected & depends_on_static_field
public void useOuterAndStatic(SourceClass outer) {
// access_outer_protected: access source outer's protected field
outer.outerProtectedList.add("inner_rec_item");
// depends_on_static_field: use target class's static field
String staticVal = TargetClass.TARGET_STATIC_FIELD;
// access_instance_field: access source outer's instance field
int outerInt = outer.outerInstanceInt;
}
}
}

// Concrete implementation of SourceInnerRec (to instantiate abstract method)
class ConcreteSourceInnerRec extends SourceClass.SourceInnerRec {
public ConcreteSourceInnerRec(String id, int count) {
super(id, count);
}

// Implement abstract method (no_new_exception feature: no new checked/unchecked exceptions)
@Override
public TargetClass processTarget(TargetClass targetParam) {
// Variable call: use target parameter
this.processFields(targetParam, new TargetClass("second_target"));
// return this; (reuse existing method with return this)
this.updateCount(20);
// depends_on_static_field: reference target static field
String staticRef = TargetClass.TARGET_STATIC_FIELD;
return targetParam;
}
}