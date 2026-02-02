package test;
abstract class TargetClass {int targetField;class TargetInner {}
public void example() {class LocalInner {} // target_feature: local inner class}}
class SamePackageOtherClass {private void process(TargetClass.TargetInner inner) {// DoStatement with obj.field and 1TargetClass target = new TargetClass() {};int count = 0;do {target.targetField = 1;count++;} while (count < 2);}}
public class SourceClass {class SourceInner {record SourceInnerRec() {/**
Method Javadoc
Static method to process TargetClass.TargetInner
@param inner TargetInner instance
@return TargetClass instance*/protected static TargetClass methodToMove(TargetClass.TargetInner inner) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callTargetClass target = new TargetClass() {};int var = target.targetField;
// Labeled statement + for statementouter:for (int i = 0; i < 3; i++) {if (i == 2) break outer;}
// VariableDeclarationExpression with numbers:2abstract class VarExprHandler {void handle() {int num1 = 0, num2 = 0; // Two variable declarations}}new VarExprHandler() {};
// Call same package other classnew SamePackageOtherClass().process(inner);
return target;}}}}