package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;
// Parent class for overriding featureabstract class ParentClass {private abstract int processTarget(TargetClass<String> target);}
// Source class (protected, same package, anonymous inner + member inner class)protected class SourceClass extends ParentClass {// Feature: member inner classpublic class SourceMemberInner {public void useOverriddenMethod(TargetClass<String> target) {SourceClass.this.processTarget(target);}}
// Method to be refactored: overriding, private, base type return@Overrideprivate int processTarget(TargetClass<String> targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;
// Protected Pattern (numbers:1, modifier:protected, exp:Pattern)protected Pattern regexPattern = Pattern.compile(targetField);
// Raw_typeList rawList = new ArrayList();rawList.add(targetField);
// Variable call: target's anonymous inner classRunnable targetAnon = targetParam.createAnonymousAction();targetAnon.run();
// Feature: anonymous inner classRunnable sourceAnon = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous uses target field: " + targetField);}};
try {sourceAnon.run();// Variable call: target's generic method (type parameter feature)String genericResult = targetParam.processGeneric(targetField + "_suffix");// Variable call: Pattern usageboolean matches = regexPattern.matcher(genericResult).find();return matches ? targetField.length() : 0;} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}}}
// Target class (protected, target_feature: type parameter + anonymous inner class)protected class TargetClass<T> {// Target field (per_condition)public T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous uses field: " + targetField);}};}
// Generic method (type parameter feature)public T processGeneric(T input) {return (T) (input + "_processed");}}
// Test classpublic class MoveMethodTest5269 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>("testPattern");source.new SourceMemberInner().useOverriddenMethod(target);int result = source.processTarget(target);System.out.println("Refactoring test result: " + result);}}
