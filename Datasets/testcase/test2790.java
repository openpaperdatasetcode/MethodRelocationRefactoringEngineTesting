package test;
non-sealed abstract class SourceClass implements SomeInterface {/**
Method Javadoc for overriding method
@param target TargetClass instance containing the field
@return Object result*/private Object methodToMove(TargetClass target) {// Instance method call in instance code blocks via this.methodName(arguments){this.instanceHelperMethod(target.targetField);}
super();target.toString();
// Raw type usageList rawList = new ArrayList();rawList.add(target.targetField);
return rawList;}
public void instanceHelperMethod(Object arg) {}}
/**
Javadoc for TargetClass (target_feature: javadoc)*/abstract class TargetClass {public String targetField = "targetFieldValue"; // Source contains target's field (per_condition)
public void someMethod() {// Local inner class (target_feature)class TargetLocalInner {}}
@Overridepublic Object methodToMove(TargetClass target) {return super.toString();}}
interface SomeInterface {}