package test.refactor.movemethod;
import diff.pkg.OtherPackageClass;import java.lang.reflect.Method;import java.util.function.Function;
// Source class (protected, same package, two anonymous inner classes)protected class SourceClass {// Member inner class (source_inner_rec: recursive inner structure)protected class SourceInner {public class RecursiveInner {// Method to be refactored: normal, private, TargetClass returnprivate TargetClass processTarget(TargetClass targetParam, String... varargs) throws ReflectiveOperationException { // per_condition + requires_throws// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// With_bounds (generic with bounds)Function<? extends CharSequence, String> boundedFunc = CharSequence::toString;
// VariableDeclarationStatement (public, diff_package_others pos, ClassName.field x1)public OtherPackageClass otherPackageObj = new OtherPackageClass();String pkgFieldValue = OtherPackageClass.PUBLIC_STATIC_FIELD; // ClassName.field
// For statementfor (int i = 0; i < varargs.length; i++) {String processed = boundedFunc.apply(varargs[i]);targetParam.addData(processed);}
// First anonymous inner class (source feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {System.out.println("Anon1: " + pkgFieldValue);}};
// Second anonymous inner class (source feature - duplicate)Runnable anon2 = new Runnable() {@Overridepublic void run() {System.out.println("Anon2: " + targetParam.getInnerData());}};
try {// Used_by_reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("getInnerData");String reflectedData = (String) targetMethod.invoke(targetParam);
// Variable call: target's member inner class (target_feature)TargetClass.TargetInner targetInner = targetParam.new TargetInner();targetInner.process(targetParam.getData());
// Varargs method feature (source, varargs, OuterClass.InnerClass.methodName(), pos: expression)TargetClass result = SourceClass.SourceInner.RecursiveInner.this.varargsMethod(targetParam, "extraArg1", "extraArg2");
anon1.run();anon2.run();return result;} catch (ReflectiveOperationException e) {// requires_throws: rethrowthrow e;} catch (Exception e) {throw new RuntimeException(e);}}
// Varargs method (method_feature: 1 occurrence, source, varargs)public TargetClass varargsMethod(TargetClass target, String... args) {for (String arg : args) {target.addData(arg + "_varargs");}return target;}}}
// Public method to invoke refactored methodpublic TargetClass invokeProcess(TargetClass target, String... varargs) throws ReflectiveOperationException {SourceInner inner = new SourceInner();SourceInner.RecursiveInner recursive = inner.new RecursiveInner();return recursive.processTarget(target, varargs);}}
// Target class (private, target_feature: member inner class)private class TargetClass {private final java.util.List<String> dataList = new java.util.ArrayList<>();
// Target_feature: member inner classpublic class TargetInner {public void process(String data) {dataList.add(data + "_innerProcessed");}}
public void addData(String data) {dataList.add(data);}
public String getInnerData() {return String.join(",", dataList);}
public java.util.List<String> getData() {return new java.util.ArrayList<>(dataList);}}
// diff.pkg for diff_package_others pospackage diff.pkg;
public class OtherPackageClass {// ClassName.field for VariableDeclarationStatementpublic static final String PUBLIC_STATIC_FIELD = "DiffPackageStaticValue";}
// Test classpackage test.refactor.movemethod;
public class MoveMethodTest5298 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();try {TargetClass result = source.invokeProcess(target, "arg1", "arg2");System.out.println("Refactored result data: " + result.getInnerData());} catch (ReflectiveOperationException e) {e.printStackTrace();}}}