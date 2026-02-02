package test.refactoring.movemethod;
public class TargetClass {public int publicField;private int privateField;
public TargetClass(int value) {this.privateField = value;}
public static Object staticMethod(int a, int b, int c) {return a + b + c;}
public int getPrivateField() {return privateField;}
class TargetInner {int innerValue;}}
private class SourceClass {protected String outerProtected = "protected_value";
private void process() {TargetClass target = new TargetClass(10);TargetClass.TargetInner inner = target.new TargetInner();Object varCall = inner.innerValue;
// Type declaration statementclass LocalType {int localField;}LocalType localVar = new LocalType();
// ExpressionStatement feature with obj.field and 2target.publicField = 2;inner.innerValue = target.getPrivateField() + 2;
// Static method feature with 3 parametersObject staticResult = TargetClass.staticMethod(1, 2, 3);localVar.localField = (Integer) staticResult;
// Do statementint count = 0;do {count++;target.publicField += count;} while (count < 2);
// Access instance fieldvarCall = target.publicField;}}
import org.junit.Test;
public class MoveMethodTest3056 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();// Using reflection to access private methodtry {java.lang.reflect.Method method = SourceClass.class.getDeclaredMethod("process");method.setAccessible(true);method.invoke(source);} catch (Exception e) {e.printStackTrace();}}}