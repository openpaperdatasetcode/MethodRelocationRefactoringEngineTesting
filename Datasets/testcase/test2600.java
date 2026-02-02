package test.same;
public class SourceClass extends ParentClass {private int outerPrivateField;
/**
Method Javadoc for overloading methods*/private void overloadMethod(TargetClass target) {super();TargetClass.MemberInner inner = target.new MemberInner();Object var = inner.targetField;
if (var == null) {throw new NullPointerException();}
try {int val = getValue(1);var = outerPrivateField + val;} catch (Exception e) {}}
private void overloadMethod(TargetClass target, String str) {}
public int getValue(int num) {return num;}
Runnable anon1 = new Runnable() {public void run() {}};
Runnable anon2 = new Runnable() {public void run() {}};}
class ParentClass {}
public class TargetClass {class MemberInner {Object targetField;}}