package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.util.function.Function;
protected enum SourceEnum {VALUE;
public List<String> instanceMethod(TargetEnum.MemberInner.InnerRec targetInnerRec) {// Two local inner classesclass LocalInner1 {}class LocalInner2 {}
// Access target parameterString targetField = targetInnerRec.targetField;
// Instance methods from inner class in exception throwing statements (3 instances)try {Object obj1 = TargetEnum.MemberInner.method1(targetInnerRec);Object obj2 = TargetEnum.MemberInner.method2(targetInnerRec, "arg");Object obj3 = TargetEnum.MemberInner.method3();} catch (IllegalStateException e) {throw new RuntimeException("Processing failed", e);}
// Enhanced for statementList<String> items = targetInnerRec.getItems();for (String item : items) {variableCall();}
// Expression statementint expr = items.size() * 2;
// TypeMethodReference (3 instances)protected Function<String, Integer> ref1 = Integer::parseInt;protected Function<String, String> ref2 = String::toUpperCase;protected Function<List<String>, Integer> ref3 = List::size;
variableCall();
// Depends on static fieldint staticVal = TargetEnum.STATIC_FIELD;
// Uses outer thisSourceEnum outerThis = SourceEnum.this;
// Used by reflectiontry {Method method = TargetEnum.MemberInner.InnerRec.class.getMethod("getItems");} catch (NoSuchMethodException e) {}
return new ArrayList<>();}
private void variableCall() {}}
protected enum TargetEnum {TARGET_VALUE;
static int STATIC_FIELD;
class MemberInner {class InnerRec {String targetField;
List<String> getItems() {return new ArrayList<>();}}
public static Object method1(InnerRec rec) {return rec.targetField;}
public static Object method2(InnerRec rec, String arg) {return rec.targetField + arg;}
public static Object method3() {return new Object();}}}