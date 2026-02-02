package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;import diffpackage.OthersDiffPackage;
// Diff package class for SuperConstructorInvocation pos: diff_package_otherspackage diffpackage;public class OthersDiffPackage {public class ParentClass {public int parentField = 2;public ParentClass() {}}}
package test.refactor.movemethod;import diffpackage.OthersDiffPackage;
// Parent annotation for source_class extends feature@interface ParentAnnotation {}
// Source @interface: no modifier, same package with target@interface SourceAnnotation extends ParentAnnotation { // source_class feature: extends// Static nested class (source_class feature)class StaticNestedClass {// Inner recursive class (method_position: source_inner_rec)public class InnerRecursiveClass {// Target method: instance, public, return List<String>public List<String> instanceMethod(TargetAnnotation targetParam) { // per_condition: contains target parameterList<String> result = new ArrayList<>();
// SuperConstructorInvocation (type=SuperConstructorInvocation, modifier=public, pos=diff_package_others)publicSuperConstructorInvocation();
// Generic method feature (type=generic, modifier=final, pos=ternary operators)OthersClass others = new OthersClass();String input = "test";others.finalGenericMethod(input != null ? input : "default");
// Constructor invocationStaticNestedClass nested = new StaticNestedClass();
// Switch caseint value = 1;switch (value) {case 1:result.add("switch-case-1");break;default:result.add("switch-default");}
// SuperMethodInvocation (numbers: "1", modifier: default)defaultSuperMethodInvocation(1);
// Variable callInnerRecursiveClass recursive = new InnerRecursiveClass();recursive.helperMethod();targetParam.new AnonymousInnerClass() {}.doSomething(); // target_feature: anonymous inner class
// Requires try-catchtry {OthersDiffPackage.ParentClass parent = new OthersDiffPackage.ParentClass();result.add(String.valueOf(parent.parentField));} catch (Exception e) {e.printStackTrace();}
// Recursive call (source_inner_rec)if (result.size() < 3) {result.addAll(instanceMethod(targetParam));}
return result;}
// Override violation: method signature conflicts with target's inner class methodpublic List<String> instanceMethod(TargetAnnotation targetParam) {return new ArrayList<>();}
private void helperMethod() {}
public void publicSuperConstructorInvocation() {// target_feature: obj.field (parent.parentField), 2OthersDiffPackage.ParentClass parent = new OthersDiffPackage.ParentClass() {@Overridepublic String toString() {return String.valueOf(parentField); // obj.field = 2}};}
default void defaultSuperMethodInvocation(int num) {// SuperMethodInvocation with numbers: 1super.toString();}}}
// Anonymous inner class (source_class feature)default Runnable createAnonymous() {return new Runnable() {@Overridepublic void run() {System.out.println("Source annotation anonymous inner class");}};}}
// Target @interface: public, target_feature=anonymous inner classpublic @interface TargetAnnotation {// Inner class for target_inner contextclass TargetInnerClass {// Override violation candidatepublic List<String> instanceMethod(TargetAnnotation targetParam) {return new ArrayList<>();}}
// Anonymous inner class (target_feature)default void useAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Target annotation anonymous inner class");}};runnable.run();}
// Nested interface for anonymous inner class instantiationinterface AnonymousInnerClass {void doSomething();}}
// Others class (method_feature: others_class)class OthersClass {// Final generic method (method_feature: generic, 2, others_class, obj.m1().m2().m3())public final <T extends CharSequence> void finalGenericMethod(T input) {String result = input.toString().trim().toUpperCase(); // obj.m1().m2().m3()if (result.length() >= 2) {} // method_feature: 2}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodRefactoring5303Test {@Testpublic void testMethodBeforeRefactoring() {SourceAnnotation source = new SourceAnnotation() {};SourceAnnotation.StaticNestedClass.InnerRecursiveClass innerRec =new SourceAnnotation.StaticNestedClass().new InnerRecursiveClass();TargetAnnotation targetParam = new TargetAnnotation() {};
// Execute target methodList<String> result = innerRec.instanceMethod(targetParam);assertFalse(result.isEmpty());assertTrue(result.contains("switch-case-1"));assertTrue(result.contains("2"));
// Verify per_condition: method contains target parameterassertNotNull(targetParam);
// Verify target_feature: anonymous inner classtargetParam.useAnonymous();
// Verify source_class featuresassertNotNull(source.createAnonymous());}}