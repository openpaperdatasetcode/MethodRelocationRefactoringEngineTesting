package test.refactoring.movemethod;
import test.other.OtherPackageSuperClass;
private class SourceClass {private TargetClass targetField = new TargetClass();
int calculate(int value) {class SourceInner {int process() {OtherPackageSuperClass otherSuper = new OtherPackageSuperClass();int result = 0;try {result += otherSuper.superField1 + otherSuper.superField2 + otherSuper.superField3;result *= targetField.compute(value);} catch (Exception e) {result = 0;}return result;}}return new SourceInner().process();}
long calculate(long value) {class SourceInnerOverload {long process() {OtherPackageSuperClass otherSuper = new OtherPackageSuperClass();long result = 0;try {result += otherSuper.superField1 + otherSuper.superField2 + otherSuper.superField3;result *= targetField.compute(value);} catch (Exception e) {result = 0;}return result;}}return new SourceInnerOverload().process();}
private void callOverloadedInDoWhile() {int count = 0;do {calculate(count);calculate((long) count * 2);count++;} while (count < 3);}}
final class TargetClass extends OtherPackageSuperClass {public int compute(int num) {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Processing int: " + num);}};runnable.run();return num * 2;}
public long compute(long num) {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Processing long: " + num);}};runnable.run();return num * 3;}}
package test.other;
public class OtherPackageSuperClass {protected int superField1 = 1;protected int superField2 = 2;protected int superField3 = 3;
protected void superMethod() {}}
package test.refactoring.movemethod;
public class MoveMethodTest5178 {public static void main(String[] args) {SourceClass source = new SourceClass();System.out.println(source.calculate(5));System.out.println(source.calculate(10L));
// Call private method via reflectiontry {var method = SourceClass.class.getDeclaredMethod("callOverloadedInDoWhile");method.setAccessible(true);method.invoke(source);} catch (Exception e) {e.printStackTrace();}}}