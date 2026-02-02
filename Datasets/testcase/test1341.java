package test.refactor.movemethod.source;
import java.util.List;import java.util.ArrayList;import test.refactor.movemethod.target.TargetClass;import test.refactor.movemethod.others.OthersClass;
// Source class: default modifier, different package with targetclass SourceClass {private static TargetClass targetField = new TargetClass(); // per_condition: source contains target's field
// Static nested class 1 (source_class feature)public static class StaticNestedClass1 {public static void nestedMethod1() {}}
// Static nested class 2 (source_class feature)public static class StaticNestedClass2 {public static void nestedMethod2() {}}
// Target method: static, public, return List<String>public static List<String> publicStaticMethod(String... varargs) { // method.type: static, varargs featureList<String> result = new ArrayList<>();
// Constructor invocation + varargs in parameter list (pos: constructor parameter list)OthersClass others = new OthersClass(varargs);
// While statementint count = 0;while (count < 2) { // method_feature: 2result.add("while-" + count);count++;}
// Private ThrowStatement (type=ThrowStatement, modifier=private, pos=diff_package_others)privateThrowLogic();
// Variable callStaticNestedClass1.nestedMethod1();StaticNestedClass2.nestedMethod2();TargetClass.MemberInnerClass targetInner = targetField.new MemberInnerClass();targetInner.innerMethod();
// Depends on inner classresult.add(targetInner.getInnerValue());
// Varargs method call (method_feature: new ClassName().method())List<String> varargsResult = others.processVarargs("extra", varargs);result.addAll(varargsResult);
// No new exceptionreturn result;}
private static void privateThrowLogic() {try {// target_feature: super.field (TargetClass extends ParentClass), 1if (targetField.superField == 1) {throw new IllegalArgumentException("Super field equals 1");}} catch (IllegalArgumentException e) {// No new exception (swallowed gracefully)}}}
package test.refactor.movemethod.target;
// Target class: public, different package with source, target_feature=member inner classpublic class TargetClass extends ParentClass {public class MemberInnerClass {public void innerMethod() {}public String getInnerValue() {return "targetInnerValue";}}}
package test.refactor.movemethod.target;
// Parent class for TargetClass (super.field feature)public class ParentClass {protected int superField = 1; // target_feature: super.field, 1}
package test.refactor.movemethod.others;
import java.util.List;import java.util.ArrayList;
// Others class (method_feature: others_class)class OthersClass {private String[] args;
// Constructor with varargs parameter (pos: constructor parameter list)public OthersClass(String... args) {this.args = args;}
// Varargs method (method_feature: varargs, new ClassName().method())public List<String> processVarargs(String prefix, String... varargs) {List<String> result = new ArrayList<>();result.add(prefix);for (String arg : varargs) {result.add(arg);}return result;}}
// Test classpackage test.refactor.movemethod;
import org.junit.Test;import static org.junit.Assert.*;import test.refactor.movemethod.source.SourceClass;import test.refactor.movemethod.target.TargetClass;import java.util.List;
public class MoveMethodRefactoring5297Test {@Testpublic void testStaticMethodBeforeRefactoring() {// Execute target static methodList<String> result = SourceClass.publicStaticMethod("arg1", "arg2");
// Verify result contentassertTrue(result.containsAll(List.of("while-0", "while-1","targetInnerValue","extra", "arg1", "arg2")));assertEquals(6, result.size());
// Verify per_condition: source contains target's fieldtry {var field = SourceClass.class.getDeclaredField("targetField");field.setAccessible(true);TargetClass target = (TargetClass) field.get(null);assertNotNull(target);} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to verify target field in source: " + e.getMessage());}
// Verify target_feature: member inner classTargetClass target = new TargetClass();assertNotNull(target.new MemberInnerClass());}}