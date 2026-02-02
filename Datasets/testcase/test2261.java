package test;
import java.util.ArrayList;
private class SourceClass {protected int outerProtected = 5;static class FirstStaticNested {}static class SecondStaticNested {record SourceInnerRec() {final Object overloadedMethod(TargetClass target) {// VariableDeclarationStatement with super.field and value 1public int superFieldVar = target.superField + 1;
// Raw type usageArrayList rawList = new ArrayList();
// For statementfor (int i = 0; i < 1; i++) {int val = target.targetField;}
// Switch statementswitch (target.targetField) {case 1:break;default:break;}
// Ternary operator with synchronized constructor callOthersClass obj = (target.targetField > 0)? new OthersClass(1): new OthersClass(2);
// Access outer protected fieldint total = outerProtected + superFieldVar;
return total;}
final Object overloadedMethod(int num) {return null;}}}}
/**
TargetClass with javadoc and local inner class*/private class TargetClass extends SuperClass {int targetField;
void createLocalInner() {class TargetLocalInner {int innerField = targetField;}}}
class SuperClass {int superField = 1;}
class OthersClass {// Synchronized constructorsynchronized OthersClass(int param) {super();}}