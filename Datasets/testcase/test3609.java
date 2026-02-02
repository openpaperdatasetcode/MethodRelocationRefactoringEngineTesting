package test;
import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
strictfp class SourceClass permits SourceSubClass {public static String staticField = "staticData";
static class StaticNested {void nestedMethod() {}}
Object instanceMethod(TargetClass target) {@MethodAnnotclass TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
new Runnable() {public void run() {StaticNested staticNested = new StaticNested();staticNested.nestedMethod();}}.run();
super.toString();variableCall(target);
TargetClass.MemberInner inner = target.new MemberInner();if (inner != null) {List<String> result = inner::overriddenMethod;} else {List<String> result = inner::overriddenMethod;}
try {String data = staticField;} catch (Exception e) {e.printStackTrace();}
return typeDecl;}
private void variableCall(TargetClass target) {target.memberInner.method();}}
class SourceSubClass extends SourceClass {}
private class TargetClass {class MemberInner {public List<String> overriddenMethod() {return List.of("targetInner");}
void method() {}}MemberInner memberInner = new MemberInner();}