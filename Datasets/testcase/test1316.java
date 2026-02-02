package test.refactor.movemethod;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public enum SourceEnum {INSTANCE1, INSTANCE2;
private String outerPrivateField = "sourcePrivate";
public static class StaticNestedClass1 {public void nestedMethod1() {}}
public static class StaticNestedClass2 {public class InnerRecursiveClass {@TestAnnotation@Overridepublic void publicInstanceMethod(TargetEnum targetParam) throws IOException {// Super constructor invocation (implicit in enum, explicit via inner class context)super.toString();
// NullPointerExcepion riskif (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
// Instance method feature: obj.m1().m2().m3()Object chainResult = targetParam.m1().m2().m3();
// If statementif (chainResult != null) {// Switch caseswitch (chainResult.toString()) {case "test":// Expression statementSystem.out.println("Case test");break;default:System.out.println("Default case");}}
// Variable callString privateFieldAccess = outerPrivateField; // Access outer privateStaticNestedClass1 nested1 = new StaticNestedClass1();nested1.nestedMethod1();
// Depends on inner classTargetEnum.MemberInnerClass inner = targetParam.new MemberInnerClass();inner.innerMethod();
// IOException throwif (privateFieldAccess.isEmpty()) {throw new IOException("Private field is empty");}
// Override violation (method signature conflicts with potential superclass method)}}}
// Instance method for method chainpublic SourceEnum m1() {return this;}
public SourceEnum m2() {return this;}
public Object m3() {return "chainResult";}}
enum TargetEnum {TARGET_INSTANCE1, TARGET_INSTANCE2;
public class MemberInnerClass {public void innerMethod() {}
// Target inner class to receive the moved method (override violation potential)@TestAnnotationpublic void publicInstanceMethod(TargetEnum targetParam) throws IOException {// Placeholder for refactored method}}
// Instance method for method chainpublic TargetEnum m1() {return this;}
public TargetEnum m2() {return this;}
public Object m3() {return "targetChainResult";}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5246Test {@Testpublic void testMethodBeforeRefactoring() throws IOException {SourceEnum.StaticNestedClass2.InnerRecursiveClass innerObj =new SourceEnum.StaticNestedClass2().new InnerRecursiveClass();TargetEnum targetParam = TargetEnum.TARGET_INSTANCE1;
innerObj.publicInstanceMethod(targetParam);assertTrue(innerObj.getClass().isAnnotationPresent(TestAnnotation.class));}
@Test(expected = NullPointerException.class)public void testNullParameter() throws IOException {SourceEnum.StaticNestedClass2.InnerRecursiveClass innerObj =new SourceEnum.StaticNestedClass2().new InnerRecursiveClass();innerObj.publicInstanceMethod(null);}}