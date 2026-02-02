package test;
import java.lang.reflect.Method;import java.util.Arrays;
public class SourceClass {public class InnerRecord {private class NestedInner extends TargetClass.InnerRecord {@MyAnnotation(call = "callMethod")@Overrideprivate int overridingMethod() {super();labeled: {int val = targetField;TargetClass.InnerRecord inner = new TargetClass.InnerRecord();var result = inner.callMethod("arg1", "arg2");if (result == null) break labeled;return val;}return 0;}
{try {Method method = getClass().getDeclaredMethod("overridingMethod");method.invoke(this);} catch (Exception e) {}}}}
private TargetClass target = new TargetClass();private int targetField = target.field;}
public class TargetClass {int field = 5;
public record InnerRecord() {TargetClass callMethod(String... args) {return super.getClass().getEnclosingClass().newInstance();}}}
@interface MyAnnotation {String call();}