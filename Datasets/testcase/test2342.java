package test;
import java.lang.reflect.Method;import java.util.regex.Pattern;
/**
Javadoc for TargetClass
*/
protected class TargetClass {
static class TargetStaticNested {
class TargetInner {}
}
}
public class SourceClass extends ParentClass {protected TargetClass targetField = new TargetClass();private int outerProtectedField = 10;
class SourceInner {class SourceInnerRec {private void varargsMethod(String... args) {try {// Constructor in array initializationObject[] array = {new OthersClass().m1().m2().m3()};
// Expression statementTargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();TargetClass.TargetStaticNested.TargetInner targetInner = nested.new TargetInner();
// Pattern expressions (2)Pattern p1 = Pattern.compile("a");Pattern p2 = Pattern.compile("b");
// Variable callint var = outerProtectedField;
// Access outer protected methodouterProtectedMethod();
// Access instance methodtargetField.toString();
// Used by reflectionMethod method = getClass().getMethod("varargsMethod", String[].class);} catch (Exception e) {e.printStackTrace();}}}}
protected void outerProtectedMethod() {}
void localInnerMethod() {class LocalInner {}}}
class ParentClass {}
class OthersClass {public OthersClass m1() { return this; }public OthersClass m2() { return this; }public OthersClass m3() { return this; }}
class CallerClass {public void call() {new SourceClass().new SourceInner().new SourceInnerRec().varargsMethod();}}
class SubCaller extends CallerClass {@Overridepublic void call() {new SourceClass().new SourceInner().new SourceInnerRec().varargsMethod("a", "b");}}