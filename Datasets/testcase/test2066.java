package test;
import java.lang.reflect.Method;
private class SourceClass {static class StaticNested {}class MemberInner {private TargetClass methodToMove(TargetClass... targets) {// For statementfor (int i = 0; i < targets.length; i++) {TargetClass target = targets[i];// Access target fieldString fieldVal = target.targetField;
// Expression statementtarget.nestedField = fieldVal + i;}
// Switch caseswitch (targets.length) {case 0:break;case 1:TargetClass.StaticNested staticNested = new TargetClass.StaticNested();break;default:break;}
// Used by reflectiontry {Method method = MemberInner.class.getMethod("methodToMove", TargetClass[].class);method.invoke(this, (Object) targets);} catch (Exception e) {}
// Call method in property assignmentif (targets.length > 0) {targets[0].lambdaProperty = (s) -> targets[0].privateMethod(s);}
return targets.length > 0 ? targets[0] : null;}}}
class TargetClass {String targetField;String nestedField;java.util.function.Function<String, String> lambdaProperty;
static class StaticNested {}
private String privateMethod(String input) {return input.toUpperCase();}}