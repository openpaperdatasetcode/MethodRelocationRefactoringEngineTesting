package test;
interface MyInterface {}
public abstract sealed class SourceClass permits SubSource {int outerField;
static class StaticNested {class SourceInner {Object instanceMethod(TargetClass.TargetInner.TargetRec param) {try {variableCall = param.innerField;
switch (param.innerField) {case 1:break;default:return SourceClass.this.outerField;}
class LocalInner {void useOuter() {return SourceClass.this;}}new LocalInner().useOuter();
staticReturnStatement();return this;} catch (Exception e) {return null;}}
private static void staticReturnStatement() {TargetClass.TargetInner targetInner = new TargetClass().new TargetInner();return targetInner.this.field = 1;}
int variableCall;}}}
non-sealed abstract class TargetClass implements MyInterface {int field;
class TargetInner {class TargetRec {int innerField;
void method() {class LocalInner {}}}}}
final class SubSource extends SourceClass {}