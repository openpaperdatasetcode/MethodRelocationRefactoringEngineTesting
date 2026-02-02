package test;
import java.lang.reflect.Constructor;
abstract class SourceClass {protected int outerProtected = 5;
static class StaticNested<T extends Number> {void nestedMethod(TargetClass target) {target.value = outerProtected;}}
private <T extends TargetClass> void method(T param) {class LocalInner {LocalInner() {super();}}LocalInner local = new LocalInner();; // Empty statement
param.field = 1;new StaticNested<Integer>().nestedMethod(param);}
private void method(TargetClass param, String str) {try {Constructor<?> superConstructor = TargetClass.Parent.class.getConstructor(int.class);TargetClass.Parent parent = (TargetClass.Parent) superConstructor.newInstance(1);param.superField = parent.superField;} catch (Exception e) {}
param.doSomething();}}
class TargetClass extends TargetClass.Parent {int field;int value;int superField;
TargetClass() {super(1);}
void doSomething() {class LocalInner {void useField() {System.out.println(field);}}new LocalInner().useField();}
static class Parent {int superField;
public Parent(int value) {this.superField = value;}}}