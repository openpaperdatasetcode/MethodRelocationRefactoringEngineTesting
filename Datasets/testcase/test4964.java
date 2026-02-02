package target;
import java.util.function.Consumer;

/**

Javadoc for TargetEnum (target_feature)
Abstract enum containing static nested class and fields used by source enum
*/
public abstract enum TargetEnum {
// Enum constants
INSTANCE1(10),
INSTANCE2(20),
INSTANCE3(30);
// Field accessed by source (obj.field)
public int targetField;
// Static nested class (target_feature)
public static class TargetStaticNested {
// Public method references (3 required)
public static void methodRef1(TargetEnum enumVal) {
System.out.println(enumVal.targetField);
}
public static void methodRef2(int val) {
System.out.println(val);
}
public void methodRef3() {
System.out.println("Instance method ref");
}
}
// Enum constructor
TargetEnum(int targetField) {
this.targetField = targetField;
}
// Abstract method (for override_violation possibility)
public abstract TargetEnum process(TargetEnum... args);
// Instance method for method reference
public void instanceMethodRef() {
System.out.println("Instance method in TargetEnum");
}
}
</doubaocanvas-part>
<doubaocanvas-part path="source/SourceEnum.java" language="java" description="Public source enum with extends, static nested classes and varargs method">package source;

import target.TargetEnum;
import java.util.function.Consumer;

// Source enum: public, different package, extends (Enum is implicit, extends custom interface for clarity)
public enum SourceEnum implements SourceInterface {
// Enum constants
SOURCE1,
SOURCE2;

// Field of target (per_condition)
private TargetEnum targetField = TargetEnum.INSTANCE1;

// 1st static nested class (source_feature)
public static class SourceStaticNested1 {
// Recursive inner class containing varargs method (method_position: source_inner_rec)
public static class SourceRecursiveInner1 {
// Varargs method to be refactored (type: varargs, return: TargetClass Type)
TargetEnum varargsMethod(TargetEnum... targets) {
// Labeled statement
outerLoop:
for (int i = 0; i < targets.length; i++) {
// Variable call
TargetEnum current = targetField;
if (current == null) {
break outerLoop;
}

// TryStatement (type: TryStatement, modifier: static, pos: source)
try {
staticTryBlock(current);
} catch (Exception e) {
// no_new_exception (no new custom exception thrown)
e.printStackTrace();
}

// For statement
for (TargetEnum te : TargetEnum.values()) {
if (te.targetField == current.targetField) {
continue outerLoop;
}
}
}

// 3 public MethodReferences (numbers: 3, modifier: public)
Consumer<TargetEnum> ref1 = TargetEnum.TargetStaticNested::methodRef1; // Static method ref
Consumer<Integer> ref2 = TargetEnum.TargetStaticNested::methodRef2; // Static method ref
TargetEnum.TargetStaticNested nestedInst = new TargetEnum.TargetStaticNested();
Runnable ref3 = nestedInst::methodRef3; // Instance method ref

// Invoke method references
ref1.accept(targets[0]);
ref2.accept(100);
ref3.run();

// Return TargetClass Type
return targets.length > 0 ? targets[0] : TargetEnum.INSTANCE1;
}

// Static method for TryStatement
private static void staticTryBlock(TargetEnum target) {
if (target.targetField < 0) {
throw new IllegalArgumentException("Invalid field value");
}
}
}
}

// 2nd static nested class (source_feature)
public static class SourceStaticNested2 {
// Recursive inner class (source_inner_rec)
public class SourceRecursiveInner2 {
// Overloaded method (for overload_exist context)
TargetEnum varargsMethod(TargetEnum target) {
return target;
}
}
}

// Implement interface method (extends feature)
@Override
public void interfaceMethod() {
System.out.println("Implement interface method");
}
}

// Interface for source enum to extend (explicit extends feature)
interface SourceInterface {
void interfaceMethod();
}