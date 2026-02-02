package test;
import otherpackage.OthersClass;import java.lang.annotation.*;import java.io.IOException;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass implements Runnable {class MemberInner {}
static int staticField;
@MyAnnotationpublic int instanceMethod(StrictfpTargetClass.StaticNested.InnerRec param) throws IOException {class LocalInner {}
// Access target fieldString field1 = param.thisField1;String field2 = param.thisField2;
// ReturnStatement in diff_package_others with this.field access (2 targets)OthersClass others = new OthersClass();private String ret1 = param.thisField1;private String ret2 = param.thisField2;if (ret1 == null) {return others.returnValue(ret1);}if (ret2 == null) {return others.returnValue(ret2);}
// Static methods from others_class in array initialization (3 instances)Object[] array = {OthersClass.staticMethod1(),OthersClass.staticMethod2(),OthersClass.staticMethod3()};
// Constructor invocationStrictfpTargetClass.StaticNested nested = new StrictfpTargetClass.StaticNested();
variableCall();
// Depends on static fieldint val = SourceClass.staticField;
return 0;}
private void variableCall() {}
@Overridepublic void run() {}}
strictfp class StrictfpTargetClass extends ParentClass {static class StaticNested {class InnerRec {String thisField1;String thisField2;}}}
class ParentClass {}
package otherpackage;
public class OthersClass {public static Object staticMethod1() { return new Object(); }public static Object staticMethod2() { return new Object(); }public static Object staticMethod3() { return new Object(); }
public int returnValue(String s) {return s == null ? 0 : s.length();}}