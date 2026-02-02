package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
// Source abstract class (default modifier, same package)abstract class SourceClass {// Target class field (per_condition: source contains target's field)private TargetClass targetField;
// Instance method to be refactored (method.type: instance)List<String> refactorMethod() {// Type declaration statement (method.features)List<String> resultList = new ArrayList<>();// Protected CharacterLiteral expressions (2 instances)protected char char1 = 'A';protected char char2 = 'B';// Variable call (method.features)resultList.add(String.valueOf(char1));// Private normal method call in ternary operators (method.features)void targetMethodCall = (targetField != null) ? targetField.process("arg1", "arg2", "arg3") : null;return resultList;}
// Anonymous inner class (source_class feature)Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {}};
// Member inner class (source_class feature)class SourceInnerClass {}}
// Target abstract class (public modifier, same package)public abstract class TargetClass {// Generic method for call_method target_feature (obj.m1().m2().m3())public <T> TargetClass m1() { return this; }public <T> TargetClass m2() { return this; }public <T> TargetClass m3() { return this; }
// Private normal method (matches method.features' method call)private void process(String a, String b, String c) {}
// Anonymous inner class (target_class feature)Callable<String> targetAnonymous = new Callable<>() {@Overridepublic String call() { return ""; }};}
// Annotation for call_method position (attribute values of annotations)@interface TestAnnotation {TargetClass value();}
// Usage of call_method (type: target, pos: annotation attribute)@TestAnnotation(value = new TargetClass() {// Private modifier (call_method.modifier: private)private TargetClass getTarget() {// Target_feature: generic + obj.m1().m2().m3()return new TargetClass().m1().m2().m3();}}.getTarget())class AnnotationUser {}
