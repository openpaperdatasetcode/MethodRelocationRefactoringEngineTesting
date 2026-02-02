package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.List;
interface TestInterface {}interface TargetInterface {}
protected class SourceClass implements TestInterface {// Member inner class (source_class feature)protected class SourceMemberInner {public void innerMethod() {}}
// Anonymous inner class (source_class feature)private Runnable anonymousInner = new Runnable() {@Overridepublic void run() {SourceMemberInner inner = new SourceMemberInner();inner.innerMethod();}};
/**
Instance method to be refactored (contains target class parameter - per_condition)
@param target target class parameter
@return Object instance*/public final Object methodToMove(FinalTargetClass target) {// ExpressionStatement (type: ExpressionStatement, modifier: protected, pos: same_package_target)protected void expressionStmt() {// obj.field (target_feature)System.out.println(target.staticNested.field);}
// Do statementint count = 0;do {// Variable callSourceMemberInner inner = new SourceMemberInner();inner.innerMethod();count++;} while (count < 1);
// Depends_on_inner_classanonymousInner.run();
expressionStmt();return new Object();}
// Override_violation: Final method cannot be overridden@Overridepublic String toString() {return super.toString();}}
final class FinalTargetClass implements TargetInterface {// Target_feature: static nested classpublic static class TargetStaticNested {public String field = "targetField";}
// Target_inner_rec: target class for method movementpublic class TargetInnerRecClass {// Refactored method should be placed herepublic final Object methodToMove(FinalTargetClass target) {protected void expressionStmt() {System.out.println(target.staticNested.field);}
int count = 0;do {SourceClass source = new SourceClass();SourceClass.SourceMemberInner inner = source.new SourceMemberInner();inner.innerMethod();count++;} while (count < 1);
source.new Runnable() {@Overridepublic void run() {SourceClass.SourceMemberInner inner = source.new SourceMemberInner();inner.innerMethod();}}.run();
expressionStmt();return new Object();}}
public TargetStaticNested staticNested = new TargetStaticNested();public TargetInnerRecClass innerRec = new TargetInnerRecClass();}
// Test class for refactoring verificationpublic class MoveMethodTest5051 {public static void main(String[] args) throws Exception {SourceClass source = new SourceClass();FinalTargetClass target = new FinalTargetClass();
// Original method call (before refactoring)Object originalResult = source.methodToMove(target);
// Used_by_reflection: Call original method via reflectionMethod originalMethod = SourceClass.class.getMethod("methodToMove", FinalTargetClass.class);Object reflectionResult = originalMethod.invoke(source, target);
// Refactored method call (after moving to TargetInnerRecClass)FinalTargetClass.TargetInnerRecClass targetInnerRec = target.innerRec;Object refactoredResult = targetInnerRec.methodToMove(target);
// Verify override_violation: Attempting to override final method causes compilation error// Uncommenting the following code should trigger compilation failure/*new FinalTargetClass.TargetInnerRecClass(target) {@Overridepublic Object methodToMove(FinalTargetClass target) { // Error: cannot override final methodreturn null;}};*/}}