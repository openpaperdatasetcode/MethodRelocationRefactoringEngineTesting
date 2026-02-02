package test;
protected class TargetClass {}
protected class SourceClass {private int outerField = 1;
public void example() {class LocalInner1 {}class LocalInner2 {}}
class SourceInner {record SourceInnerRec(String recField) {protected TargetClass methodToMove(TargetClass target) {return target;}}}}
class SourceSubClass extends SourceClass.SourceInner.SourceInnerRec {public SourceSubClass(String recField) {super(recField);}
@Overrideprotected TargetClass methodToMove(TargetClass target) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Super constructor invocation (inner class)class InnerSub extends SourceClass {InnerSub() {super();}}new InnerSub();
// Synchronized statementsynchronized (this) {// Variable callString var = this.recField;
// ExpressionStatement (first)var = var.toUpperCase();
// ExpressionStatement with this.field and 1protected class ExprHandler {private int field;public void process() {this.field = 1;}}new ExprHandler().process();
// Generic method with superTypeReference.methodName(arguments) in switchswitch (var.length()) {case 2:TargetClass result = genericMethod(target, var);break;default:break;}}
// Uses outer thisSourceClass outer = SourceSubClass.this.new SourceClass();
return target;}
public <T extends TargetClass> TargetClass genericMethod(T target, String arg) {// SuperTypeReference.methodName(arguments)Object superRef = TargetClass.class.cast(target);return target;}}