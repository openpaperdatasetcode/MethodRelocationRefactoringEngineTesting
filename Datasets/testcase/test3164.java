package test;
class TargetClass {String targetField;class TargetInner {} // member inner class}
protected class SourceClass {static int staticField = 100; // static field for dependency
public SourceClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
public void createLocalInner() {// Local inner classclass LocalInner {}new LocalInner();}
Object methodToMove(TargetClass target) {// Variable callString var = target.targetField;TargetClass.TargetInner inner = target.new TargetInner();
// Depends on static fieldint staticVal = SourceClass.staticField;
// Uses outer thisSourceClass outer = SourceClass.this;outer.createLocalInner();
return var + staticVal;}}