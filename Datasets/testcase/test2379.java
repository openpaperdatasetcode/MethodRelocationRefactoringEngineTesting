package test;
abstract class ParentRecord {}
protected record SourceClass(String data) extends ParentRecord {static class SourceStaticNested {int value;}
class SourceInner {/**
Accessor method to get processed value
@param targetRec target inner recursive parameter
@return base type result*/private int getValue(TargetClass.TargetInner.TargetInnerRec targetRec) {// Instance method in property assignmentOthersClass others = new OthersClass();int prop = others.m1().m2().m3(1);
// Assert statementassert targetRec.count > 0 : "Count must be positive";
// Do statementint i = 0;do {targetRec.count--;i++;} while (i < targetRec.count);
// Variable callString var = targetRec.name;int countVar = targetRec.count;
// Overload existsoverloadedMethod(var);overloadedMethod(countVar);
// Access instance fieldtargetRec.name = "updated";
return countVar + prop;}
private void overloadedMethod(String s) {}private void overloadedMethod(int i) {}}
void localInnerMethod() {class LocalInner {} // Local inner class}}
record TargetClass(int id) {{new Runnable() {}; // Anonymous inner class}
class TargetInner {class TargetInnerRec {String name;int count = 3;}}}
class OthersClass {OthersClass m1() { return this; }OthersClass m2() { return this; }int m3(int num) { return num * 2; }}