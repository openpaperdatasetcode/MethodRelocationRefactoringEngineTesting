package test;
import java.util.Objects;
protected record TargetClass(int id) {class Inner {class RecursiveInner {int getInnerValue() {return id * 2;}}}}
record SourceClass(String data) {protected int outerProtectedField = 100;
private int instanceMethod(TargetClass target) {labeledBlock: {TargetClass.Inner targetInner = target.new Inner();TargetClass.Inner.RecursiveInner targetRecInner = targetInner.new RecursiveInner();int result = 0;
synchronized (target) {private int localVar = target.id;this.data = "updated_data";
do {String calledStr = OtherClass.privateStaticMethod(target, localVar);result += calledStr.length();localVar--;
if (localVar < 0) {break labeledBlock;}} while (localVar < 5);}
try {result += accessInstanceMethod(targetRecInner);result += outerProtectedField;} catch (Exception e) {result = 0;}return result;}}
private int accessInstanceMethod(TargetClass.Inner.RecursiveInner recInner) {return recInner.getInnerValue();}
void methodWithLocalInner() {class LocalInner {}}
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(data);}};}
class OtherClass {private static String privateStaticMethod(TargetClass target, int param) {return target.id() + "_" + param;}}