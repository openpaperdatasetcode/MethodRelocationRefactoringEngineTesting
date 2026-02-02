package test;
import java.lang.annotation.*;
// Source is an annotation interface with empty modifier@interface SourceAnnotation {// Static nested class in source annotationstatic class StaticNested {// Inner class in sourceclass Inner {// Instance method in source_innerprotected void instanceMethod(TargetAnnotation.InnerRec targetInnerRec) {// Anonymous inner class in sourceRunnable r = new Runnable() {@Overridepublic void run() {}};
// Check for null and throw NPEif (targetInnerRec == null) {throw new NullPointerException("Target inner record cannot be null");}
// VariableDeclarationStatement with this.field access (3 targets)private int field1 = this.value1;private String field2 = this.value2;private boolean field3 = this.value3;
// Instance method from target in object initialization (1 instance)Object obj = targetInnerRec.instanceMethod(new Object());
// For statementfor (int i = 0; i < 5; i++) {variableCall();}
// Super constructor invocation in local classclass SubClass extends ParentClass {SubClass() {super();}}
// Type declaration statementTargetAnnotation.InnerRec typeDecl;
// PostfixExpression (1 instance)default int postfix = field1++;
variableCall();}
private int value1;private String value2;private boolean value3;private void variableCall() {}}}}
// Target is a protected annotation interface extending another annotationprotected @interface TargetAnnotation extends BaseAnnotation {// Inner record (simulated as nested class for annotation constraints)class InnerRec {// Instance method using superprivate Object instanceMethod(Object arg) {return super.baseMethod(arg);}}
// Anonymous inner class in target annotationRunnable ANONYMOUS = new Runnable() {@Overridepublic void run() {}};}
// Base annotation for target to extend@interface BaseAnnotation {default Object baseMethod(Object arg) {return arg;}}
class ParentClass {}
