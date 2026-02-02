package test;
protected class SourceClass {private int sourceField;
private Object instanceMethod(FinalTargetClass target) {class LocalInner {void process() {System.out.println(target.staticNested.field);}}LocalInner local = new LocalInner();
new Runnable() {public void run() {sourceField = 10;}}.run();
FinalTargetClass.StaticNested nested = new FinalTargetClass.StaticNested();variableCall(nested);
try {for (int i = 0; i < 2; i++) {if (i == 1) {this.sourceField = i;continue;}nested.process();}} catch (Exception e) {e.printStackTrace();}
return target;}
private void variableCall(FinalTargetClass.StaticNested nested) {nested.print();}}
final class FinalTargetClass {static class StaticNested {int field = 20;
void process() {}
void print() {System.out.println("Target static nested class");}}
StaticNested staticNested = new StaticNested();}