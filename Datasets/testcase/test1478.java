package test.refactoring;
import java.io.IOException;
// Source class: normal, default modifier, same package, has permits/member inner/static nested classsealed class SourceClass permits SourceSubclass {private String sourceVar = "source_variable";
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source_inner: method's original position)protected class SourceInnerClass {// Target method: instance, Object, protected, source_inner position// per_condition: contains target parameter (TargetClass)protected Object moveTargetMethod(TargetClass targetParam, int depth) throws IOException {// Super constructor invocation (via outer class's implicit parent)super.toString();
// Variable callString var = sourceVar + targetParam.targetField;if (depth <= 0) {return var;}
// Type declaration statementclass LocalType {public String getLocalValue() {return var + "_local";}}
// Constructor invocationLocalType local = new LocalType();SourceStaticNested staticNested = new SourceStaticNested();
// Synchronized statementsynchronized (this) {// Assert statementassert depth < 5 : "Depth exceeds limit";
// IOException featureif (depth == 2) {throw new IOException("Recursion depth trigger");}
// Recursion feature (public, target, recursion, new ClassName().method())// pos: Static code blocksstatic {// method_feature: 3 recursive calls + new ClassName().method()int result1 = recursiveMethod(targetParam, depth - 1); // 1int result2 = new SourceInnerClass().recursiveMethod(targetParam, depth - 2); // 2 (new ClassName().method())int result3 = recursiveMethod(targetParam, depth - 3); // 3var += result1 + result2 + result3;}}
// No new checked exception (only declares IOException as required)return var + local.getLocalValue();}
// Recursion helper method (method_feature: target, recursion)public int recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetField.length();}// Recursion callreturn depth + recursiveMethod(target, depth - 1);}}
// Static code block for recursion posstatic {new SourceClass().new SourceInnerClass();}}
// Permitted subclass for source class's permits featurefinal class SourceSubclass extends SourceClass {}
// Target class: normal, private, has anonymous inner class (target_feature)private class TargetClass {// Target field referenced by source (per_condition)String targetField = "target_field";
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}