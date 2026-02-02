package test.refactoring.movemethod;
import java.lang.reflect.Method;
/**
Final source class with required features*/final class SourceClass {// Private field for access_outer_private featureprivate String outerPrivateField = "outerPrivateValue";// Instance field for access_instance_field featureString instanceField = "instanceValue";
/**
Static nested class in source class
*/
static class SourceStaticNested {}
/**
Member inner class in source class
*/
class SourceMemberInner {}
/**
Default instance method to be refactored (source position)
Meets per_condition: contains TargetClass parameter
@param target target class parameter*/void refactorTargetMethod(TargetClass target) {// 2 StringLiteral expressions (default modifier)String literal1 = "firstStringLiteral";String literal2 = "secondStringLiteral";
// This.var = var featurethis.instanceField = literal1;
// Variable call + access_instance_fieldString fieldValue = this.instanceField;System.out.println(fieldValue);
// If statementif (literal2.length() > 5) {// Access_outer_private (access source class's private field)System.out.println(outerPrivateField);}
// Override_violation (target_inner has same-signature method with incompatible access)// Note: TargetClass's local inner class has default method with same signaturetarget.invokeLocalInnerMethod();
// No new exception (no throw statements)
// Used_by_reflection featuretry {Method method = TargetClass.class.getDeclaredMethod("invokeLocalInnerMethod");method.setAccessible(true);method.invoke(target);} catch (Exception e) {e.printStackTrace();}}}
/**
Public target class with local inner class*/public class TargetClass {// Instance field for access in inner classString targetInstanceField = "targetInstanceValue";
/**
Method to invoke local inner class
/
public void invokeLocalInnerMethod() {
// Local inner class (target_feature)
class TargetLocalInner {
/*
Default method with same signature as refactored method (causes override_violation)
Incompatible access if refactored method is moved here (default vs default but context conflict)
*/
void refactorTargetMethod(TargetClass target) {
System.out.println(target.targetInstanceField);
}
}
new TargetLocalInner().refactorTargetMethod(this);
}
}
/**
Test class for Move Method refactoring (id: 5329)*/public class MoveMethodRefactoringTest5329 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Invoke the method to be refactoredsource.refactorTargetMethod(target);}}