package test;
private class SourceClass {class SourceInner1 {}
class SourceInner2 {class SourceInnerRec {protected TargetClass methodToMove(TargetClass.TargetInner targetInner) {// Super constructor invocationclass SubSource extends SourceInner2 {SubSource() {super();}}SubSource sub = new SubSource();
// Expression statementTargetClass target = targetInner.getOuter();targetInner.setValue("test");
// Variable callString var = targetInner.value;TargetClass.TargetInner anotherInner = target.new TargetInner();
// Overload existsoverloadedMethod(target);overloadedMethod(targetInner);
return target;}
protected void overloadedMethod(TargetClass target) {}protected void overloadedMethod(TargetClass.TargetInner inner) {}}}}
private class TargetClass {class TargetInner {String value;
TargetClass getOuter() {return TargetClass.this;}
void setValue(String val) {this.value = val;}}}
class SubClass extends TargetClass {protected void assignProperty() {// Property assignment with lambdaRunnable runner = (parameters) -> {TargetInner inner = new TargetInner();inner.setValue("lambda");};runner.run();}}