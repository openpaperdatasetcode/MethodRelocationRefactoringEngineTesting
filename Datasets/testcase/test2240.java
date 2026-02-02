package test;
class Parent {public int method() {return 0;}}
protected class SourceClass extends Parent {private int outerPrivate = 5;
@Overridepublic int method() {class LocalType {}TargetClass target = new TargetClass();int val = target.targetField;
assert val > 0;
for (TargetClass t : new TargetClass[]{target}) {val += t.targetField;}
switch (val) {case 1:this.method();break;default:val += outerPrivate;}
if (val < 0) {throw new IllegalArgumentException();}
return val;}}
strictfp class TargetClass {int targetField;
{new Runnable() {public void run() {}};}}