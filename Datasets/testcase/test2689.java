package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
public enum SourceEnum {INSTANCE;
// Static nested class (source_feature)private static class StaticNestedSource {}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
// Overloading method 1private <T extends CharSequence & Comparable<T>> int methodToMove(TargetEnum.TargetInner target) {// EnhancedForStatement with obj.field (count 1, pos: source)List<T> fieldList = new ArrayList<>();fieldList.add((T) target.field);for (T val : fieldList) {// Variable callval.toString();}
// Empty statement;
// Type declaration statementTargetEnum.TargetInner typeDecl = target;// Expression statementtypeDecl.field.toUpperCase();
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetEnum.TargetInner.class);method.invoke(this, target);} catch (Exception e) {}
return target.field.length();}
// Overloading method 2 (override violation: reduces parent method access)private <T extends CharSequence & Comparable<T>> int methodToMove(TargetEnum.TargetInner target, String arg) {return target.field.length() + arg.length();}
// Inner class for call_methodpublic class SourceInner {public void callMethod(TargetEnum.TargetInner target) {// Overriding method reference in expressionRunnable ref = TargetInnerOverride::methodToMove;ref.run();}}
// Parent class for override violationstatic class ParentSourceEnum {protected <T extends CharSequence> int methodToMove(TargetEnum.TargetInner target, String arg) {return 0;}}
// Class for overriding method referencestatic class TargetInnerOverride {public static void methodToMove() {}}}
enum TargetEnum {INSTANCE;
// Member inner class (target_feature)public class TargetInner {public String field = "targetInnerField"; // Source contains target's field (per_condition)}}