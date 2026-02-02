// Source class: protected normal class, with member inner & static nested classesprotected class SourceClass {// Target class field (satisfies per_condition: source contains target field)TargetClass targetField = new TargetClass();
// Static nested class (source feature)static class SourceStaticNested {void staticNestedMethod() {}}
// Member inner class (source feature)class SourceInnerClass {void innerClassMethod() {new SourceStaticNested().staticNestedMethod();}}
// Method to test Move Method: instance type, Object return, default accessObject methodToMove() {// Constructor invocation (method feature)SourceInnerClass innerObj = new SourceInnerClass();SourceStaticNested staticObj = new SourceStaticNested();
// Super constructor invocation (method feature)class SubClass extends SourceStaticNested {SubClass() {super(); // Call super class constructor}}new SubClass();
// EmptyStatement with target_feature (obj.field & 1) (method feature)if (targetField.targetInner.field == 1) {; // Empty statement}
// Depends_on_inner_class (method feature): use source inner classinnerObj.innerClassMethod();
// Variable call (method feature)variableCall();
return targetField;}
// Variable call target methodprivate void variableCall() {}}
// Target class: public normal class, with member inner class (target feature)public class TargetClass {// Member inner class (target feature)class TargetInner {int field = 1; // Field for target_feature (obj.field & 1)}
// Inner class instance (for source method access)TargetInner targetInner = new TargetInner();}