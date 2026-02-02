package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public class SourceClass {private String outerField = "outerData";
class MemberInner {void useOuterThis() {System.out.println(SourceClass.this.outerField);}}
@MethodAnnotpublic TargetClass varargsMethod (TargetClass target, String... args) {new Runnable () {public void run () {SourceClass.this.outerField = "updatedOuterData";}}.run ();
@MethodAnnotclass TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
MemberInner inner = new MemberInner();inner.useOuterThis();
for (String arg : args) {variableCall(target.staticNested);if (arg.equals("break")) break;}
return target;}
private void variableCall(TargetClass.StaticNested nested) {nested.nestedMethod();}}
class TargetClass {static class StaticNested {void nestedMethod() {System.out.println("Target static nested class method");}}
StaticNested staticNested = new StaticNested();}