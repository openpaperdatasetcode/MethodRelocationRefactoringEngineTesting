package test;
import java.util.List;
public class SourceClass extends ParentClass {private String privateField = "sourcePrivate";
// Member inner classpublic class SourceInner {// Overloading method 1private int methodToMove(TargetClass target) {return process(target, 1);}
// Overloading method 2private long methodToMove(TargetClass target, long factor) {return process(target, factor);}
private <T extends Number> T process(TargetClass target, T factor) {super(); // Super constructor invocation// Variable call + contains target field (per_condition)target.toString();String targetField = target.targetField;
// Access outer private fieldString combined = targetField + SourceClass.this.privateField;
// Raw typeList rawList = target.getRawList();rawList.add(combined);
// Calculate resultNumber result = targetField.length() * factor.doubleValue();
try {if (rawList.isEmpty()) throw new Exception();} catch (Exception e) {// No new exception}
return (T) result;}}
@Overridepublic Object callParentMethod(TargetClass target) {// Final parent class method in if/else conditionsSourceInner inner = new SourceInner();if (target.targetField.length() > 5) {return ParentClass.super.callGenericMethod(inner.methodToMove(target));} else {return ParentClass.super.callGenericMethod(inner.methodToMove(target, 2L));}}}
class ParentClass {// Final parent class generic methodpublic final <T> Object callGenericMethod(T value) {return value;}
public Object callParentMethod(TargetClass target) {return null;}}
public class TargetClass {public String targetField = "targetField"; // Source contains target's field
// Raw type methodpublic List getRawList() {return new java.util.ArrayList();}
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}}