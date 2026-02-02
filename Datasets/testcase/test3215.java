package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface OverloadAnnot {}
/**
Target class with static nested class (target_feature)*/class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Member inner class (target_inner)
*/
public class TargetInner {
public String getInnerData() {
return data + "_inner";
}
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String process(String input) {
return input.toUpperCase();
}
}
public TargetInner getTargetInner() {return new TargetInner();}
public String getData() {return data;}}
/**
Default source class with member inner and anonymous inner classes*/class SourceClass {// Member inner class (source_class feature)class MemberInner {public String assist(TargetClass.TargetInner inner) {return inner.getInnerData() + "_assisted";}}
// Overloading method 1private List<String> process(TargetClass target) {return process(target, "default_param");}
/**
Private overloading method 2 (position: source)*/@OverloadAnnot // has_annotationprivate List<String> process(TargetClass target, String param) {// Type declaration statementclass LocalType {String value;LocalType(String value) { this.value = value; }}
// Protected VariableDeclarationExpression (numbers=2)protected String var1 = target.getData();protected String var2 = param;
// Expression statementTargetClass.TargetInner targetInner = target.getTargetInner();String combined = var1 + "_" + var2;
// Variable callvariableCall(target);String innerResult = new MemberInner().assist(targetInner);
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {TargetClass.TargetStaticNested.process(combined);}};task.run();
List<String> result = new ArrayList<>();result.add(combined);result.add(innerResult);result.add(new LocalType(var1).value);
return result;}
private void variableCall(TargetClass target) {target.doTask();}
// Trigger methodpublic List<String> triggerProcess(TargetClass target) {return process(target);}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
List<String> result = source.triggerProcess(target);
assert result.size() == 3 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}