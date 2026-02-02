package test;
import java.util.function.Function;
protected class SourceClass<T extends Number> {// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
public Object methodToMove(int baseParam, TargetClass<T> target) {super(); // Super constructor invocation// Uses outer this referenceSourceClass<T> outerThis = SourceClass.this;
// Functional interface with target varargs method call: super.methodName(arguments)Function<TargetClass<T>[], TargetClass<T>.Inner.Rec> func = this::callTargetVarargs;
int count = 0;// While statementwhile (count < 1) {// Variable call + contains target parameter (per_condition)target.toString();TargetClass<T>.Inner.Rec rec = target.new Inner().new Rec();rec.field = target.data;count++;}
return func.apply(new TargetClass[]{target});}
// Target varargs method (method_feature)public TargetClass<T>.Inner.Rec callTargetVarargs(TargetClass<T>... targets) {return targets[0].new Inner().new Rec();}}
protected class TargetClass {
public U data; // Source contains target's field (per_condition)
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}
public class Inner {public class Rec {public U field;}}}
