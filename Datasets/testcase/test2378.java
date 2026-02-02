package test;
abstract class SourceClass {protected int outerProtectedField = 5;
{new Runnable() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}
private int methodToMove(TargetClass target) {// NullPointerExceptionif (target == null) {throw new NullPointerException("Target cannot be null");}
// ForStatement with target featuresfor (int i = 0; i < 3; i++) {target.field = i;privateMethod(target.field);}
// Constructor invocationTargetClass anotherTarget = new TargetClass();
// Labeled statementprocessLabel: {if (target.field < 0) {break processLabel;}target.localInnerMethod();}
// Type declaration statementclass ProcessType {int calculate(int val) {return val * 2;}}ProcessType processor = new ProcessType();
// Super keywordsclass SubSource extends SourceClass {SubSource() {super();}}
// Variable callint var = target.field;TargetClass localTarget = target;localTarget.field = var + 1;
// Access outer protected fieldint result = var + outerProtectedField;
return processor.calculate(result);}
private void privateMethod(int val) {}}
protected class TargetClass {int field;
void localInnerMethod() {class TargetLocalInner {} // Local inner class}}