package test;
import java.util.List;import java.util.ArrayList;
protected class TargetClass {public String targetField;public void targetMethod() {class LocalInner {}}}
public class SourceClass {public void example() {class LocalInner {}Runnable r = new Runnable() {@Overridepublic void run() {}};}
@RefactorAnnotationprotected List<String> methodToMove(TargetClass target, String... varargs) {List<String> result = new ArrayList<>();result.add(target.targetField);
// Array initialization with recursion callString[] arr = { new TargetClass().recursiveMethod(target, 1) };
if (varargs.length > 0) {for (String arg : varargs) {result.add(arg);}}
try {target.targetMethod();} catch (Exception e) {}
return result;}
public void recursiveMethod(TargetClass target, int depth) {if (depth == 0) return;variableCall(target);new TargetClass().recursiveMethod(target, depth - 1); // Recursion}
private void variableCall(TargetClass target) {String var = target.targetField;target.targetField = "updated"; // Expression statement}}
@interface RefactorAnnotation {}
