package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class ParentClass {public List<String> moveMethod() {return new ArrayList<>();}}
class TargetParent {protected int superField = 1;}
class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();static int staticField = 5;
public void outerMethod1() {class LocalInner1 {}new LocalInner1();}
public void outerMethod2() {class LocalInner2 {public List<String> innerMoveMethod() {return SourceClass.this.moveMethod();}}new LocalInner2().innerMoveMethod();}
@Overridepublic List<String> moveMethod() {assert targetField.super.field == 1 : "Super field mismatch";
class TypeDeclaration {}new TypeDeclaration();
Supplier<Object> func = () -> new SubClass().normalMethod(targetField);func.get();
this.helperMethod();variableCall();
return new ArrayList<>();}
private void helperMethod() {}
private void variableCall() {targetField.staticNested.doTask();}}
protected class TargetClass extends TargetParent {public static class StaticNested {void doTask() {}}
StaticNested staticNested = new StaticNested();
@Overridepublic List<String> moveMethod() {return new ArrayList<>();}}
class SubClass extends TargetClass {public Object normalMethod(TargetClass target) {return target.staticField + SourceClass.staticField;}}