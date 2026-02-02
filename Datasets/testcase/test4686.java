package test;
protected abstract class SourceClass<T> {private static int staticField = 2;
public static class SourceStaticNested {public void overloadedMethod(int val) {}public void overloadedMethod(String str) {}}
public class SourceInner {public final Object genericMethod (TargetClass target, U param) throws IllegalArgumentException {
int checkVal = staticField;
private int assertThreshold = 2;assert target.field <= assertThreshold : "Target field exceeds limit";
try {if (param == null) {SourceStaticNested.overloadedMethod ("param is null");throw new IllegalArgumentException ();}SourceStaticNested.overloadedMethod (checkVal);} catch (Exception e) {}
Object result = target.field * checkVal;return result;}}
void sampleLocalClass() {class SourceLocalInner {}SourceLocalInner local = new SourceLocalInner();}}
protected abstract class TargetClass {int field;}
