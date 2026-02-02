package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public class SourceClass {public static String staticField = "static_data";
static class StaticNested {record InnerRec() {@MethodAnnot // has_annotationpublic static TargetClass methodToMove() {// Uses outer this (via enclosing instance)SourceClass outer = new SourceClass();
// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {}};
// Variable call (source contains target's field)TargetClass target = new TargetClass();int val = target.TargetStaticNested.staticField;
// Expression statementval *= 2;
// Break statementloop: {if (val > 5) {break loop;}}
// Depends on static fieldString data = SourceClass.staticField;
return target;}}}}
private class TargetClass {static class TargetStaticNested {static int staticField = 3; // Target field used in source}}
class SubClass extends SourceClass {strictfp String callMethod() {// Varargs and new instance method call in expressionreturn new SubClass().varargsMethod("param1", "param2");}
String varargsMethod(String... args) {return String.join(",", args);}}