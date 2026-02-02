package test;
import java.util.ArrayList;
interface MyInterface {}
protected class SourceClass implements MyInterface {private int outerPrivateField;
private int outerPrivateMethod() {return outerPrivateField;}
class FirstInner {}
class SecondInner {public Object methodToMove(TargetClass target) {{int val = new OtherClass().m1().m2().m3();}
TargetClass instance = new TargetClass();ArrayList rawList = new ArrayList();rawList.add(target);
int i = 0;while (i < outerPrivateMethod()) {i++;}
if (target.superField > 0) {return target.superField;}
return target;}
public Object methodToMove(String str) {return null;}}}
class ParentOfTarget {int superField;}
public class TargetClass extends ParentOfTarget {}
class OtherClass {OtherClass m1() { return this; }OtherClass m2() { return this; }int m3() { return 0; }}