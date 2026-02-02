package test;
public class SourceClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();
// Static nested class (source feature)public static class SourceStaticNested {void nestedMethod() {}}
// Local inner class (source feature)public void sourceLocalInnerHolder() {class SourceLocalInner {void doSomething() {}}new SourceLocalInner().doSomething();}
protected Object methodToMove() {// variable callSourceStaticNested staticNested = new SourceStaticNested();staticNested.nestedMethod();
TargetClass target = targetField;target.targetLocalInnerHolder();
// Access target's field (per_condition verification)Object targetData = target.targetField;
return new Object();}}
/**
TargetClass Javadoc (target_feature: javadoc)
Contains local inner class and target field used by SourceClass*/public class TargetClass {// Target field used by sourcepublic String targetField = "target_data";
/**
Method with javadoc, holds local inner class (target_feature: local inner class)
*/
public void targetLocalInnerHolder() {
class TargetLocalInner {
void useTargetField() {
System.out.println(targetField);
}
}
new TargetLocalInner().useTargetField();
}
}