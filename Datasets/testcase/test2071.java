package test;
import otherpackage.ExternalClass;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.util.function.Supplier;
protected class SourceClass<T extends Number> {private static TargetClass<String> targetField = new TargetClass<>();
static class StaticNested {}
void createLocalInner() {class LocalInner {}}
private static Object methodToMove() {try {ExternalClass ext = new ExternalClass();for (int val : ext.values) {System.out.println(ext.field1 + ext.field2 + ext.field3);}
Supplier<List<String>> supplier = () ->targetField.getInner().getAccessor1().getAccessor2().getAccessor3();List<String> list = supplier.get();
SuperClass superObj = new SuperClass();int num = 1;switch (num) {case 1:targetField.variableCall();break;default:break;}
class LocalType {}System.out.println(super.toString());targetField.accessInstanceMethod();
Method refMethod = TargetClass.class.getMethod("variableCall");refMethod.invoke(targetField);
int result = SubClass.StaticNested.method();return new Object();} catch (Exception e) {return null;}}}
protected class TargetClass<T> {private InnerClass inner = new InnerClass();
InnerClass getInner() {return inner;}
class InnerClass {Accessor1 getAccessor1() {return new Accessor1();}
class Accessor1 {Accessor2 getAccessor2() {return new Accessor2();}
class Accessor2 {List<String> getAccessor3() {return new ArrayList<>();}}}}
void variableCall() {}
void accessInstanceMethod() {}}
class SuperClass {public SuperClass() {}}
class SubClass extends TargetClass<Integer> {static class StaticNested {public static int method() {return 0;}}}
package otherpackage;
public class ExternalClass {public int field1;public int field2;public int field3;public int[] values = {1, 2, 3};}