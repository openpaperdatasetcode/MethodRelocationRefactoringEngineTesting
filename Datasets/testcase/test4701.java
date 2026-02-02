package test;
protected class SourceClass extends SuperClass {protected TargetClass<String> targetField;private int sourceField;
public SourceClass(TargetClass<String> target) {this.targetField = target;}
/**
Calculates sum of integers
@param nums varargs of integers
@return sum as int
*/
public int sum(int... nums) {
int total = 0;
for (int num : nums) {
total += num + sourceField;
}
return total + targetField.targetField;
}
class LocalInnerClass {public void callMethod() {try {Object result = new TargetClass.MemberInnerClass().superCall(5);} catch (Exception e) {e.printStackTrace();}}}
static class StaticNestedClass {public void doSomething() {SourceClass source = new SourceClass(new TargetClass<>());int res = source.sum(1, 2, 3);}}}
class TargetClass<T> implements SomeInterface {int targetField;
class MemberInnerClass extends AnotherClass {public Object superCall(int arg) {return super.someMethod(arg);}}
@Overridepublic void interfaceMethod() {}}
class SuperClass {}
interface SomeInterface {void interfaceMethod();}
class AnotherClass {public Object someMethod(int x) {return x * 2;}}