package test;
private class SourceClass {private String outerPrivateField = "privateData";
class FirstMemberInner {void innerMethod() {System.out.println(outerPrivateField);}}
class SecondMemberInner {SecondMemberInner() {this("default");}
SecondMemberInner(String arg) {System.out.println(arg);}}
public TargetClass varargsMethod(TargetClass target, Object... args) {FirstMemberInner firstInner = new FirstMemberInner();SecondMemberInner secondInner = new SecondMemberInner();dependsOnInner(firstInner);
synchronized (this) {for (int i = 0; i < args.length; i++) {if (i == 1) {System.out.println(target.staticNested.superField);continue;}expressionStatement(target);}}
try {variableCall(target.staticNested);System.out.println(this.outerPrivateField);} catch (Exception e) {e.printStackTrace();}
return target;}
private void dependsOnInner(FirstMemberInner inner) {inner.innerMethod();}
private void variableCall(TargetClass.StaticNested nested) {nested.nestedMethod();}
private void expressionStatement(TargetClass target) {target.staticNested.superField += "processed";}}
class TargetClass {static class StaticNested extends SuperClass {void nestedMethod() {}}
StaticNested staticNested = new StaticNested();}
class SuperClass {String superField = "superData";}