package test.refactoring.movemethod;
// Source class: normal class, protected modifier, same package with targetprotected class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: first static nested classpublic static class SourceStaticNested1 {}
// Source feature: second static nested classpublic static class SourceStaticNested2 {}
// Source inner class (method_position: source_inner_rec)abstract class SourceInnerClass {// Constructor with super constructor invocationpublic SourceInnerClass() {super(); // Super constructor invocation}
// Method to be refactored: abstract, public, return TargetClass Typepublic abstract TargetClass refactorTargetMethod();}
// Concrete implementation of source inner classclass ConcreteSourceInner extends SourceInnerClass {@Overridepublic TargetClass refactorTargetMethod() {// Variable call (target class field and its inner class)TargetClass tempTarget = targetField;TargetClass.TargetMemberInner inner = tempTarget.new TargetMemberInner();inner.innerMethod();
// No new exception thrownreturn tempTarget;}}}
// Target interface for implements featureinterface TargetInterface {void interfaceMethod();}
// Target class: normal class, public modifier, implements interface + member inner class (target_feature)public class TargetClass implements TargetInterface {// Target feature: member inner class (target_inner_rec)public class TargetMemberInner {public void innerMethod() {}}
@Overridepublic void interfaceMethod() {}}