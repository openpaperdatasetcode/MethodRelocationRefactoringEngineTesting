package test;
strictfp class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
{Runnable anon1 = new Runnable() { @Override public void run() {} };Runnable anon2 = new Runnable() { @Override public void run() {} };}
/**
Javadoc for varargs moveMethod: Processes multiple parameters and interacts with TargetClass inner layers
@param args Variable-length input arguments
@return Processed Object result*/protected Object moveMethod(Object... args) {; // Empty statementint count = 0;while (count < args.length) {if (args[count] != null) {synchronized (targetField) {TargetClass.TargetInner.TargetInnerRec innerRec = targetField.new TargetInner().new TargetInnerRec();innerRec.m1().m2().m3();}}count++;}
for (Object arg : args) {if (arg instanceof String) {targetField.new TargetInner().new TargetInnerRec().process((String) arg);}}
return new Object();}
default TargetClass.TargetInner.TargetInnerRec method1() {return targetField.new TargetInner().new TargetInnerRec();}
default TargetClass.TargetInner.TargetInnerRec method2() {return method1().m1();}
default TargetClass.TargetInner.TargetInnerRec method3() {return method2().m2();}}
private class TargetClass {class TargetInner {class TargetInnerRec {public TargetInnerRec m1() { return this; }public TargetInnerRec m2() { return this; }public TargetInnerRec m3() { return this; }public void process(String data) {}}}}
abstract class ParentClass {public final String callMethod() {SourceClass source = new SourceClass();if (source != null) {return ParentClass.staticM1().staticM2().staticM3();} else {return "Default";}}
public static ParentClass staticM1() { return new SourceClass(); }public static ParentClass staticM2() { return staticM1(); }public static String staticM3() { return "StaticResult"; }}