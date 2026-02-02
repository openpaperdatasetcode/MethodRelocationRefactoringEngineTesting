package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
/**
Protected target class with member inner class (target_feature)*/protected class TargetClass {public static int CLASS_FIELD = 1; // ClassName.field=1private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Member inner class (target_feature)
*/
public class TargetInner {
public TargetClass processInner(String input) {
data = data + "inner" + input;
return TargetClass.this;
}
}
public String getData() {return data;}
public TargetInner getTargetInner() {return new TargetInner();}}
/**
Private source class with member inner and static nested classes*/private class SourceClass {// Static nested class (source_class feature)public static class SourceStaticNested {public static void assist(TargetClass target) {target.doTask();}}
// Member inner class (source_class feature)class SourceInner {protected TargetClass createInnerInstance(String data) {return new TargetClass(data);}}
/**
strictfp instance method (position: source)*/public strictfp Object process(TargetClass target) {List<Object> result = new ArrayList<>();
// Private VariableDeclarationStatement (target_feature: ClassName.field=1, pos: source)private int targetStaticField = TargetClass.CLASS_FIELD;result.add(targetStaticField);
// Protected SuperMethodReference (numbers=3)Function<String, TargetClass> superRef1 = SourceInner::new;Function<String, TargetClass> superRef2 = SourceInner::createInnerInstance;Function<TargetClass.TargetInner, TargetClass> superRef3 = TargetClass.TargetInner::processInner;
// Variable callvariableCall(target);SourceStaticNested.assist(target);TargetClass.TargetInner targetInner = target.getTargetInner();
// while (constructor method_feature position)int count = 0;while (count < 1) {// (parameters) -> methodBody: Lambda for inner class constructorFunction<String, TargetClass.SourceInner> innerConstructor = s -> new SourceInner();TargetClass innerInstance = innerConstructor.apply("lambda_data").createInnerInstance("inner_constructor");result.add(innerInstance.getData());count++;}
// Expression statementTargetClass processedInner = superRef3.apply(targetInner).processInner("param");result.add(processedInner.getData());
// Variable callnew SourceInner().createInnerInstance("member_inner");
// Return statementresult.add(target.getData());return result;}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test_data");
SourceClass source = new SourceClass();
Object result = source.process(target);
assert result instanceof List && ((List<?>) result).size() == 4 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}