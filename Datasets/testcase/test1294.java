package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Target abstract class (private, local inner class as target_feature)private abstract class TargetClass {public static final String TARGET_STATIC_FIELD = "target_static_field"; // ClassName.field for IfStatement
public TargetClass() {}
public void targetInstanceMethod() {// Local inner class (target_feature)class TargetLocalInner {public String getInnerData() {return "target_local_inner_data";}}System.out.println(new TargetLocalInner().getInnerData());}}
// Permitted subclass for source class (permits feature)private class PermittedSourceSubClass extends SourceClass {@Overrideprotected List<String> refactorCandidateMethod(TargetClass targetParam) {return super.refactorCandidateMethod(targetParam);}}
// Source abstract class (private, same package, permits, anonymous inner class, member inner class)private abstract class SourceClass permits PermittedSourceSubClass {// Member inner class (source_class feature)protected class SourceMemberInner {// Nested member inner class (source_inner_rec: recursive inner structure)public class SourceInnerRecursive {/**
Method Javadoc (method feature: method javadoc)
Refactoring candidate method with target parameter (per_condition)
@param targetParam Target class parameter
@return List of strings*/protected List<String> refactorCandidateMethod(TargetClass targetParam) { // Contains target parameter (per_condition)List<String> result = new ArrayList<>();
// IfStatement (private, ClassName.field, 1, pos: same_package_others)private int flag = 1; // "1" featureif (flag == 1 && TargetClass.TARGET_STATIC_FIELD != null) { // ClassName.field (TargetClass.field)result.add(TargetClass.TARGET_STATIC_FIELD);}
// Synchronized statementObject lock = new Object();synchronized (lock) {result.add("synchronized_block_data");}
// Type declaration statementclass LocalTypeDeclaration {public String getTypeData() {return "local_type_declaration_data";}}result.add(new LocalTypeDeclaration().getTypeData());
// While statementint count = 0;while (count < 2) {result.add("while_loop_" + count);count++;}
// Variable call (target parameter, inner class, source class)targetParam.targetInstanceMethod(); // Target parameter callString innerRecursiveData = this.getClass().getSimpleName();result.add(innerRecursiveData);
// Used_by_reflectiontry {Method targetMethod = TargetClass.class.getMethod("targetInstanceMethod");targetMethod.invoke(targetParam);result.add("reflection_invoked");} catch (Exception e) {e.printStackTrace();}
// Anonymous inner class (source_class feature)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {result.add("anonymous_inner_executed");}};anonymousTask.run();
// No_new_exception (no checked exceptions thrown)return result;}}}
// Abstract method to enforce subclass implementationprotected abstract List<String> refactorCandidateMethod(TargetClass targetParam);
// Factory method to create recursive inner instanceprotected SourceMemberInner.SourceInnerRecursive createInnerRecursiveInstance() {return new SourceMemberInner().new SourceInnerRecursive();}}
// Test class for refactoring verificationpublic class MoveMethodRefactorTest_5216 {public static void main(String[] args) {SourceClass source = new PermittedSourceSubClass();SourceClass.SourceMemberInner.SourceInnerRecursive innerRec = source.createInnerRecursiveInstance();TargetClass target = new TargetClass() {}; // Anonymous subclass of abstract TargetClass
List<String> result = innerRec.refactorCandidateMethod(target);System.out.println("Refactored method result: " + result);}}