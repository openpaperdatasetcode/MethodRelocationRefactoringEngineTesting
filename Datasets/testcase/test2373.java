package test;
import java.io.IOException;
class SourceClass {class SourceInner {public int innerMethod(int num) {return num * 2;}}
{new Runnable() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}
protected static void staticMethod(TargetClass target) throws IOException {// SwitchStatement with target featuresswitch (target.field) {case 1:privateMethod(target);break;default:break;}
// Instance method in ternary operatorSourceInner inner = new SourceInner();int result = (target.field > 3) ? inner.innerMethod(3) : super.hashCode();
// Do statementint i = 0;do {target.field++;i++;} while (i < 3);
// Labeled statementloopLabel:for (int j = 0; j < 5; j++) {if (j == target.field) {break loopLabel;}}
// this.methodName(arguments)SourceClass instance = new SourceClass();instance.localInnerMethod();
// Variable callint var = target.field;TargetClass anotherTarget = new TargetClass();anotherTarget.field = var;
if (var < 0) {throw new IOException("Invalid field value"); // Requires throws}}
private static void privateMethod(TargetClass target) {target.field = 1;}}
protected class TargetClass {int field;}