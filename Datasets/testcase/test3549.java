package test;
import java.util.function.Function;import diffpackage.OthersClass;
interface ActionInterface {Object moveMethod();}
/**
Strictfp source class for Move Method refactoring test*/strictfp class SourceClass implements ActionInterface {private TargetClass targetField = new TargetClass();
static class StaticNested {public void nestedMethod(TargetClass target) {target.doTask();}}
public void outerMethod() {class LocalInner {}new LocalInner();}
/**
Overridden method with required features
@return Object result*/@Overridepublic Object moveMethod() {OthersClass.validate(targetField);assert targetField.this.field == 1 : "Field value assertion";
// Instance method in if/else conditions (lambda)Function<TargetClass, Object> lambda = (t) -> t.instanceMethod();Object lambdaResult = targetField != null ? lambda.apply(targetField) : null;
// TypeMethodReferenceFunction<TargetClass, String> methodRef = TargetClass::toString;methodRef.apply(targetField);
variableCall();targetField.innerClass.doTask();accessInstanceMethod();
return lambdaResult;}
public Object moveMethod(String param) {return param;}
private void variableCall() {targetField.staticNested.nestedTask();}
private void accessInstanceMethod() {targetField.publicInstanceMethod();}
public void callMethod() {TargetClass target = new TargetClass() {@Overridepublic Object moveMethod() {return super.moveMethod();}};SourceClass source = new SourceClass();source.moveMethod();source.moveMethod("overloadArg");}}
/**
Javadoc for TargetClass: Public target class with anonymous inner class*/public class TargetClass {public int field = 1;
static class StaticNested {public void nestedTask() {}}
public StaticNested staticNested = new StaticNested();
class TargetInner {public void doTask() {}}
private TargetInner innerClass = new TargetInner();
public void doTask() {}
public Object instanceMethod() {return this;}
public void publicInstanceMethod() {}
{new Runnable() {@Overridepublic void run() {}}.run();}
@Overridepublic Object moveMethod() {return this;}
public Object moveMethod(int param) {return param;}}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void validate(TargetClass target) {assert target.field == 1 : "Cross-package assertion";}}