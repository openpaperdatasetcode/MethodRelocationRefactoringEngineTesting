package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class SourceClass {class MemberInner {@Overridepublic List<String> overridingMethod() {return new ArrayList<>();}}
@MyAnnotationpublic int methodToMove(TargetClass.Inner inner) {// Super constructor invocationsuper.toString();
// Empty statement;
// For statementint sum = 0;for (int i = 0; i < inner.count; i++) {sum += inner.value;}
// 2 ArrayCreations with protected modifierprotected int[] intArray = new int[5];protected String[] strArray = new String[10];
// Abstract method feature in static code blockstatic {FunctionalInterface fi = (a) -> a * 2;fi.calculate(5);}
// Overload existsreturn methodToMove(inner, 0);}
public int methodToMove(TargetClass.Inner inner, int offset) {// Call method in if/else conditionsList<String> result;if (inner.count > 0) {result = new SourceClass().new MemberInner().overridingMethod();} else {result = new SourceClass().new MemberInner().overridingMethod();}return result.size() + offset;}
@FunctionalInterfaceprivate interface FunctionalInterface {int calculate(int a);}}
sealed class TargetClass permits SubTarget {class Inner {int count;int value;}}
final class SubTarget extends TargetClass {}