package test;
class ParentSourceClass {protected int parentInstanceMethod() {return 10;}}
class TargetClass {String field1;int field2;boolean field3;
public static class TargetStaticNested {public void nestedMethod(TargetClass target) {target.field1 += "_nestedUpdated";target.field2 += 5;target.field3 = !target.field3;}}
public TargetClass() {this.field1 = "initField1";this.field2 = 0;this.field3 = false;}}
class TargetSubClass extends TargetClass {@Overridepublic String toString() {return field1 + "_subClass";}}
protected class SourceClass extends ParentSourceClass {private TargetClass targetField1 = new TargetClass();private TargetClass targetField2 = new TargetClass();private TargetClass targetField3 = new TargetClass();
public TargetClass overridingMethod() {TargetClass target = new TargetClass();TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();TargetSubClass subTarget = new TargetSubClass();
labeledBlock1: {privateLabeled: {targetField1.field1 = "updatedField1";targetField2.field2 = 20;targetField3.field3 = true;break privateLabeled;}staticNested.nestedMethod(targetField1);}
labeledBlock2: {int switchVal = super.parentInstanceMethod();switch (switchVal) {case 10:variableCall(subTarget);break;default:break labeledBlock2;}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {staticNested.nestedMethod(target);}};anonymous.run();
return subTarget;}
private void variableCall(TargetClass target) {target.field1 += "_varUpdated";}
public static class SourceStaticNested {public void useOverridingMethod() {SourceClass source = new SourceClass();TargetClass result = source.overridingMethod();}}}