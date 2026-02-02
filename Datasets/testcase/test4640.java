package test;
interface TestInterface {}sealed class ParentTarget permits TargetClass {}
private class SourceClass implements TestInterface {class InnerSource {@Deprecatedprotected void instanceMethod(TargetClass.InnerRec innerRec) {super();TargetClass target = new TargetClass();int var = innerRec.field;
do {if (innerRec.field == 1) {Object result = innerRec.overrideMethod();} else {innerRec.overrideMethod(1);}var++;} while (var <= innerRec.field);
ParentTarget superRef = target;superRef.toString();innerRec.instanceMethodCall();}}}
non-sealed class TargetClass extends ParentTarget {class InnerRec {int field;
public Object overrideMethod() {return field;}
public Object overrideMethod(int num) {return super.toString();}
void instanceMethodCall() {class LocalInner {}new LocalInner();}}}