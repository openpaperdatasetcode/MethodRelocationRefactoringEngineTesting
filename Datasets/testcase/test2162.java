package test;
import java.util.Arrays;import java.util.List;
private class SourceClass {protected String outerProtected = "protected_val";static class StaticNested {}class MemberInner {}
public TargetClass methodToMove(TargetClass target) {// Constructor invocationTargetClass newTarget = new TargetClass(target.field);
// Enhanced for statementList<TargetClass> targets = Arrays.asList(target, newTarget);for (TargetClass t : targets) {t.variableCall();t.instanceMethod();}
// Access outer protected fieldnewTarget.field = SourceClass.this.outerProtected;
// If/else condition with method referenceif (targets.size() > 0) {OthersClass::protectedMethod;} else {OthersClass.protectedMethod(target);}
return newTarget;}}
class TargetClass {String field;
TargetClass(String field) {this.field = field;Runnable anonymous = new Runnable() {public void run() {}};}
void variableCall() {}void instanceMethod() {}}
class OthersClass {protected static void protectedMethod(TargetClass target) {}}