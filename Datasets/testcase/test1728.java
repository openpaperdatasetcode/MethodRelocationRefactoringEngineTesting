package test;
import java.lang.reflect.Method;import java.util.ArrayList;
class SourceClass {static class StaticNested1 {}static class StaticNested2 {}static int staticField;
// Overloading methodsprotected void overloadedMethod(TargetClass.StaticNested.InnerRec param) {process(param);}
protected void overloadedMethod(int value, TargetClass.StaticNested.InnerRec param) {process(param);}
private void process(TargetClass.StaticNested.InnerRec param) {// Access target fieldString fieldVal = param.targetField;
// LabeledStatement in same_package_others with super.field access (1 target)SamePackageOthers others = new SamePackageOthers();Label: {public int superVal = param.superField;if (superVal < 0) break Label;others.process(superVal);}
// Assert statementassert param != null : "Target parameter must not be null";
// Type declaration statementTargetClass.StaticNested.InnerRec typeDecl;
// Used by reflection (2 instances)try {Method method = TargetClass.StaticNested.InnerRec.class.getMethod("instanceMethod");Method fieldGetter = TargetClass.StaticNested.InnerRec.class.getMethod("getTargetField");} catch (NoSuchMethodException e) {}
variableCall();
// Depends on static fieldint val = SourceClass.staticField;
// Overloading methods from inner class in object initialization (3 instances)Object obj1 = new NestedProcessor().process();Object obj2 = new NestedProcessor().process(1);Object obj3 = new NestedProcessor().process("str");}
private void variableCall() {}
class NestedProcessor {public Object process() { return new Object(); }public Object process(int num) { return new Object(); }public Object process(String str) { return new Object(); }}}
class TargetClass extends ParentClass {static class StaticNested {class InnerRec {String targetField;int superField = TargetClass.super.parentField; // Access super field
void instanceMethod() {}String getTargetField() { return targetField; }}}}
class ParentClass {protected int parentField;}
class SamePackageOthers {public void process(int value) {}}