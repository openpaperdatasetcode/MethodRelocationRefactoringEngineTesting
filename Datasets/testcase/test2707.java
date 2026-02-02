package test;
import java.lang.reflect.Method;
protected class SourceClass {public class SourceInner {public static class SourceInnerRec {// Overload exists: method 1public static int methodToMove(TargetClass target) {return processTarget(target, 1);}
// Overload exists: method 2public static int methodToMove(TargetClass target, int multiplier) {return processTarget(target, multiplier);}
private static int processTarget(TargetClass target, int multiplier) {int result = 0;// Uses outer this (via enclosing instance reference)SourceClass outerThis = new SourceClass();SourceInner inner = outerThis.new SourceInner();
// Synchronized statementsynchronized (TargetClass.StaticNested.class) {// Requires_try_catchtry {// Variable call + contains target parameter (per_condition)target.toString();String targetField = target.targetField;
// Expression statementresult = targetField.length() * multiplier;
// Assert statementassert result > 0 : "Result must be positive";
// Break statementfor (int i = 0; i < 2; i++) {if (i == 1) break;result += i;}
// Used by reflectionMethod method = SourceInnerRec.class.getMethod("methodToMove", TargetClass.class);method.invoke(null, target);} catch (Exception e) {result = -1;}}
return result;}}}}
class TargetClass {public String targetField = "targetContent"; // Source contains target's field (per_condition)
// Static nested class (target_feature)public static class StaticNested {}}