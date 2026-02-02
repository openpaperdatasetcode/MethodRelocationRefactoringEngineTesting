package test.refactoring.movemethod;
import other.OtherPackageClass;import java.util.ArrayList;import java.util.List;
class BaseParent {}
class TargetParent extends BaseParent {protected String parentField = "parent_data";}
public class TargetClass extends TargetParent {public static class TargetInner {private int value1;private int value2;private int value3;
public TargetInner(int v1, int v2, int v3) {this.value1 = v1;this.value2 = v2;this.value3 = v3;}
public int getSum() {return value1 + value2 + value3;}}
public static void staticMethod(String message) {System.out.println("Static method: " + message);}}
class SubTargetClass extends TargetClass {@Overridepublic static void staticMethod(String message) {System.out.println("Overridden static method: " + message);}}
class SourceParent {protected SourceParent() {}}
class SourceClass extends SourceParent {static class SourceStaticNested {}
class SourceMemberInner {}
protected Object process(TargetClass.TargetInner inner) {// Super constructor invocationclass Derived extends SourceParent {Derived() {super();}}new Derived();
// Type declaration statementList<Object> values = new ArrayList<>();
// Expression statementObject varCall = inner.getSum();
// VariableDeclarationStatement with this.field (3) in different packageOtherPackageClass other = new OtherPackageClass();other.processTarget(new TargetClass.TargetInner(1, 2, 3));
// For statementfor (int i = 0; i < 3; i++) {values.add(inner.getSum() + i);}
// If/else conditions with sub class overriding methodif (inner.getSum() > 5) {SubTargetClass.staticMethod("Sum is large");} else {TargetClass.staticMethod("Sum is small");}
// Override violationclass BadRunnable implements Runnable {public void run(int count) {} // Invalid override}
return values;}}
package other;
import test.refactoring.movemethod.TargetClass;
public class OtherPackageClass {public void processTarget(TargetClass.TargetInner inner) {// Variable declarations using this.field (3 times)int val1 = inner.value1;int val2 = inner.value2;int val3 = inner.value3;System.out.println("Other package: " + (val1 + val2 + val3));}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3138 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass.TargetInner inner = new TargetClass.TargetInner(2, 2, 2);Object result = source.process(inner);
assertTrue(result instanceof List);assertEquals(3, ((List<?>) result).size());}}