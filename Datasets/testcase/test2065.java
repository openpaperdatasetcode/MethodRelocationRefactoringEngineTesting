package test;
sealed class ParentClass permits SourceClass {public abstract void process(TargetClass.Inner inner);}
private class SourceClass extends ParentClass {private int outerPrivateField;
record SourceInnerRec() {@Overridepublic void process(TargetClass.Inner inner) {// Local inner classclass LocalInner {}new LocalInner();
// Anonymous inner classnew Object() {};
// Super constructor invocation (implicit in record)super.toString();
// Type declaration statementTargetClass target = inner.getTarget();int fieldVal = target.instanceField;
// Access outer private fieldint privateVal = SourceClass.this.outerPrivateField;
// OtherObject.process(this)OtherClass.process(this);
// Break statementfor (int i = 0; i < 5; i++) {if (i == privateVal) {break;}}
// Call method in property assignmentinner.property = OtherClass.protectedMethod(inner);}}}
public class TargetClass {int instanceField;
class Inner {Object property;TargetClass getTarget() {return TargetClass.this;}}}
class OtherClass {static void process(Object obj) {}
protected static Object protectedMethod(TargetClass.Inner inner) {return inner.getTarget().toString();}}