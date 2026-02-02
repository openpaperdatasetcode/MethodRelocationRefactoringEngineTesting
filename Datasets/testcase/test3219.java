package test;
import java.util.List;import java.util.ArrayList;
// Target parent class (for call_method)class TargetParentClass {protected int parentMethod() {return 10;}}
// Source class (default modifier, no features)class SourceClass {/**
Method Javadoc: Varargs method handling TargetClass parameters
@param targets Array of TargetClass instances (per condition)
@return List of processed strings*/private List<String> varargsMethod(TargetClass... targets) {List<String> result = new ArrayList<>();if (targets == null) return result;
for (TargetClass target : targets) {// Uses outer this referenceSourceClass outerThis = SourceClass.this;
// Constructor invocationTargetClass newTarget = new TargetClass();
// FieldAccess (numbers=2, modifier=public)public int field1 = target.publicField;public int field2 = newTarget.anotherField;
// Assert statementassert target != null : "Target cannot be null";
// Variable calltarget.targetMethod();target.createAnonymousInner();
// Call method (parent_class, default, overriding, super.methodName(), pos: expression)int parentVal = target.callParentMethod();result.add("Parent result: " + parentVal);}
return result;}}
// Target class (protected modifier, anonymous inner class)protected class TargetClass extends TargetParentClass {public int publicField = 5;public int anotherField = 15;
public void targetMethod() {}
// Anonymous inner class (target_feature)public void createAnonymousInner() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
// Overriding parent method (for call_method)@Overrideprotected int callParentMethod() {return super.parentMethod() + 5;}}