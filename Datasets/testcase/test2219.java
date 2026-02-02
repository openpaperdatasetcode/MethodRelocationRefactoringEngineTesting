package test;
class SourceClass extends SuperClass {protected int outerProtected;
private void process(TargetClass target) {// Local inner classclass LocalInner {}
// Anonymous inner classnew Runnable() {@Overridepublic void run() {}};
// Access target's inner classTargetClass.Inner inner = target.new Inner();
// Use super keywordsuper.protectedField = inner.field;
// Variable call and access outer protected fieldouterProtected = inner.getValue();}}
class SuperClass {protected int protectedField;}
class TargetClass {class Inner {int field;
int getValue() {// Anonymous inner class in target's inner classnew Runnable() {@Overridepublic void run() {}};return field;}}}