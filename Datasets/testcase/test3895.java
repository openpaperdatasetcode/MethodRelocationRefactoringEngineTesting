package test;
import java.util.List;import java.util.ArrayList;
// Note: "permits" is only valid for sealed classes; adjusted to sealed normal class (Java 17+)private sealed class SourceClass permits SubSourceClass {// Member inner class (source feature)class SourceInner {// Recursive method: strictfp access, List<String> return, no parametersstrictfp List<String> recursiveMethod(TargetAbstractClass targetParam, int depth) {List<String> result = new ArrayList<>();// Base case for recursionif (depth <= 0) {return result;}
// Static AssertStatement (method feature) - static context via nested static blockstatic {assert TargetAbstractClass.STATIC_FIELD == 1 : "Static field mismatch"; // this.field (static) & "1"}
// Ordinary assert statement (method feature)assert targetParam != null : "Target parameter cannot be null";
// Synchronized statement (method feature)synchronized (this) {variableCall(); // Variable call (method feature)result.add("Recursion depth: " + depth);}
// Super keywords (method feature)Object superObj = SourceInner.super.getClass();result.add("Super class: " + superObj.getSimpleName());
// Recursive call (method type)result.addAll(recursiveMethod(targetParam, depth - 1));
return result;}
// Variable call target method (method feature)private void variableCall() {}}
// Anonymous inner class (source feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {new SourceInner().variableCall();}};}
// Permitted subclass for sealed SourceClass (matches "permits" feature)final class SubSourceClass extends SourceClass {}
// Target abstract class: extends ParentClass, with local inner classabstract class TargetAbstractClass extends ParentClass {// Static field for "this.field" (static context) in AssertStatementstatic int STATIC_FIELD = 1;
// Local inner class (target feature)public void targetMethod() {class TargetLocalInner {void processList(List<String> list) {list.add("Processed by local inner class");}}new TargetLocalInner().processList(new ArrayList<>());}}
// Parent class for target class "extends" featureclass ParentClass {}