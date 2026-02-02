package test;
class SourceClass {class InnerRec {protected void instanceMethod(int baseTypeParam, TargetClass target) {super.toString();
if (target == null) {throw new NullPointerException("TargetClass cannot be null");}
int targetField = target.field;TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.useTargetField(targetField);
DependedInner depended = new DependedInner();depended.processTarget(target);
Runnable anon = new Runnable() {public void run() {System.out.println("Processed target field: " + targetField);}};anon.run();}
private class DependedInner {void processTarget(TargetClass target) {System.out.println("Depended inner uses target: " + target.field);}}}}
public class TargetClass {int field;
static class StaticNested {void useTargetField(int field) {System.out.println("Static nested uses field: " + field);}}}