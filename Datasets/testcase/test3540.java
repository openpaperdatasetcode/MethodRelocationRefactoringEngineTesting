package test;
interface TargetInterface {}
class TargetParent {protected int superField = 1;}
protected class SourceClass {private TargetClass targetField = new TargetClass();
class MemberInner {protected Object moveMethod() {SamePackageOthers.validate(targetField);assert targetField.super.field == 1 : "Super field validation failed";
variableCall();int x = 10;x *= 2; // Expression statement
return new Object();}
protected Object moveMethod(String param) {assert targetField.super.field == 1 : "Super field validation failed";variableCall();return param;}
private void variableCall() {targetField.staticNested.doTask();}}
static class StaticNested {}}
protected class TargetClass extends TargetParent implements TargetInterface {public static class StaticNested {void doTask() {}}
StaticNested staticNested = new StaticNested();
protected Object moveMethod() {return this;}
protected Object moveMethod(int param) {return param;}}
class SamePackageOthers {public static void validate(TargetClass target) {assert target.superField == 1 : "Super field check in same package";}}