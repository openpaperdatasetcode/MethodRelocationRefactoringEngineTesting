package test.refactor.movemethod;
// Parent class for target_class extends featureclass TargetParentClass {protected String superField = "TargetParentSuperField";}
// Source class (default modifier, same package, type parameter + static nested + member inner class)class SourceClass<T> {// Feature: static nested classpublic static class SourceStaticNested {public static int staticHelper(String data) {return data.length();}}
// Feature: member inner classprotected class SourceMemberInner {public int innerMethod(T data) {return data.toString().length() * 2;}}
// call_method: source type, protected modifier, constructor + (parameters) -> methodBodyprotected int callInObjectInit(String input) {return input.hashCode();}
// Method to be refactored: varargs, default access, Object returnObject processTarget(TargetClass targetParam, T... varargs) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// ConstructorInvocation (private, source pos, super.field x1)private class ConstructorHelper extends TargetParentClass {public ConstructorHelper() {System.out.println("Super.field: " + super.superField); // super.field 1}}new ConstructorHelper(); // ConstructorInvocation
// Type declaration statementSourceMemberInner inner = new SourceMemberInner();SourceStaticNested staticNested = new SourceStaticNested();
// Expression statementtargetParam.updateField(superField + "_updated");staticNested.staticHelper(targetParam.getTargetField());
// Variable call: target's local inner class (target_feature)targetParam.useLocalInner();
// Object initialization with call_method (pos: object initialization)Runnable lambda = (() -> {// (parameters) -> methodBody target_featureint result = SourceClass.this.callInObjectInit(targetParam.getTargetField());System.out.println("Lambda call result: " + result);});
try {// Variable call: varargs processingObject result = new Object() {int sum = 0;{for (T arg : varargs) {sum += inner.innerMethod(arg);sum += staticNested.staticHelper(arg.toString());}}}.sum;
lambda.run();return result;} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}}}
// Target class (public, target_feature: extends + local inner class)public class TargetClass extends TargetParentClass {private String targetField;
public TargetClass(String targetField) {this.targetField = targetField;}
// Target_feature: local inner classpublic void useLocalInner() {class TargetLocalInner {public void printData() {System.out.println("Target local inner: " + targetField + "_" + superField);}}new TargetLocalInner().printData();}
public String getTargetField() {return targetField;}
public void updateField(String value) {this.targetField = value;}}
// Test classpublic class MoveMethodTest5321 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass("testTargetField");Object result = source.processTarget(target, "vararg1", "vararg2", "vararg3");System.out.println("Refactoring test result: " + result);}}