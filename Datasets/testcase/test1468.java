package test.refactoring;
// Source class: normal, protected, same package, has static nested/local inner classprotected class SourceClass {private String sourceVar = "source_variable";
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (parent of source_inner_rec)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: instance, Object, public, source_inner_rec position// per_condition: contains target parameter (TargetClass)public Object moveTargetMethod(TargetClass targetParam) {Object result = sourceVar; // variable call
// Do statementint doCount = 0;do {result = result.toString() + targetParam.targetField; // variable calldoCount++;} while (doCount < 2);
// While statementint whileCount = 0;while (whileCount < 3) {result = result.toString() + "_" + whileCount;whileCount++;}
// Variable call to target's inner classtargetParam.targetInnerClass.innerMethod();
// No new checked exceptionreturn result;}}}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void invokeInnerMethod() {SourceInnerClass inner = new SourceInnerClass();inner.new SourceInnerRecClass().moveTargetMethod(new TargetClass());}}new SourceLocalInner().invokeInnerMethod();}}
// Parent class for target's extends featureclass TargetParentClass {}
// Target class: normal, private, has extends/member inner class (target_feature)private class TargetClass extends TargetParentClass {// Target field referenced by sourceString targetField = "target_field";
// Member inner class (target_feature)class TargetInnerClass {public void innerMethod() {}}
// Target inner class instanceTargetInnerClass targetInnerClass = new TargetInnerClass();}