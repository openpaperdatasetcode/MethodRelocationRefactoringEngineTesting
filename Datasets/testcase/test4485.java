package source;
import target.TargetClass;import java.util.ArrayList;
public sealed class SourceClass permits SubSourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class MemberInner {/**
Recursive method to calculate sum based on target class field
@param targetParam TargetClass instance
@param depth Recursion depth
@return Calculated base type value*/protected int recursiveMethod(TargetClass targetParam, int depth) {@MyAnnotation(value = "callInstanceMethod",func = () -> new MemberInner().privateInstanceMethod(targetParam))class LocalType {}LocalType local = new LocalType();
static int staticVar1 = targetParam.field;static int staticVar2 = targetParam.StaticNested.getStaticField();
int sum = 0;do {sum += staticVar1 + staticVar2;targetParam.instanceMethod();depth--;} while (depth > 0);
return depth == 0 ? sum : recursiveMethod(targetParam, depth);}
private Object privateInstanceMethod(TargetClass target) {return target.field * 2;}}}
non-sealed class SubSourceClass extends SourceClass {}
@interface MyAnnotation {String value();Runnable func();}
// ------------------------------package target;
import java.util.List;
public abstract class TargetClass {int field = 5;
static class StaticNested {private static int staticField = 2;
public static int getStaticField() {return staticField;}}
public void instanceMethod() {}}