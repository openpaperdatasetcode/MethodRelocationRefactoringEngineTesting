package test.refactoring.movemethod;
/**
Source class: public normal class, same package with target
Features: static nested class + member inner class*/public class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: static nested classpublic static class SourceStaticNestedClass {public void staticNestedMethod() {}}
// Source feature: member inner classpublic class SourceMemberInnerClass {public void memberInnerMethod() {}}
/**
Instance method to be refactored (strictfp access, void return)
@brief Refactors target class-related operations with inner class dependency
@details Implements variable call to target field and depends on target's static nested class.
No new exceptions are thrown during execution.*/// method javadoc featurestrictfp void refactorTargetMethod() {// Variable call: Access target class field and its methodsTargetClass tempTarget = targetField;tempTarget.publicMethod();
// Depends_on_inner_class: Use target's static nested classTargetClass.TargetStaticNestedClass targetNested = new TargetClass.TargetStaticNestedClass();targetNested.nestedOperation();
// No_new_exception: No checked/unchecked exceptions thrown}}
/**
Target class: default modifier
Target_features: javadoc + static nested class/
// target_feature: javadoc
/*
Target class for Move Method refactoring test
Provides public methods and a static nested class for source class dependency./
class TargetClass {
// Target feature: static nested class
public static class TargetStaticNestedClass {
/*
Performs nested class operation for source class dependency
*/
public void nestedOperation() {}
}
/**
Public method for source class variable call
*/
public void publicMethod() {}
}
