package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {private String outerPrivateField = "private";class MemberInner {}
{new Runnable() {}; // Anonymous inner class}
final List<String> instanceMethod(StrictfpTargetClass.MemberInner.InnerRec param) {// Access target fieldString fieldVal = param.targetField;
// SwitchStatement with super.field access (1 target)private int superVal = param.superField;switch (superVal) {case 1:variableCall();break;default:break;}
// Type declaration statementStrictfpTargetClass.MemberInner innerType;
// Access outer private memberString privateVal = this.outerPrivateField;
// Call inner class static method in exception handlingtry {StrictfpTargetClass.MemberInner.staticMethod();} catch (Exception e) {// Handle exception}
return new ArrayList<>();}
private void variableCall() {}}
strictfp class StrictfpTargetClass extends ParentClass {class MemberInner {class InnerRec {String targetField;int superField = StrictfpTargetClass.super.parentField; // Access super field}
protected static void staticMethod() {}}}
class ParentClass {protected int parentField;}