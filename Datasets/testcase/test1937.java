package test;
abstract class ParentClass {protected abstract Object abstractMethod(TargetClass target);}
final class SourceClass extends ParentClass {class Inner {class InnerRec extends ParentClass {@Overridestrictfp int method(TargetClass target) {if (target == null) {throw new NullPointerException();}
// Array initializer with number 1int[] arr = {1};
// Anonymous inner class in sourceRunnable r = new Runnable() {public void run() {target.innerField = "anonymous";}};r.run();
// Local inner classclass LocalInner {void checkField() {if (target.this.field == 1) {System.out.println("Field is 1");}}}new LocalInner().checkField();
// ReturnStatement with this.field=1if (target.innerField != null) {return target.this.field = 1;} else {return super.abstractMethod(target);}}
@Overrideprotected Object abstractMethod(TargetClass target) {return super.abstractMethod(target);}}}}
protected class TargetClass {int field;String innerField;
{// Anonymous inner class in targetRunnable r = new Runnable() {public void run() {field = 1;}};r.run();}}