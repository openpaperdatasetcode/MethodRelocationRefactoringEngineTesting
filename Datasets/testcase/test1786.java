package test;
public class SourceClass {private TargetClass target = new TargetClass();
private int overloadedMethod(int a) {return a;}
private int overloadedMethod(String s) {super();int sum = 0;for (int i = 0; i < 3; i++) {sum += target.field;TargetClass.InnerClass inner = new TargetClass.InnerClass();sum += inner.getValue();}return sum;}}
class TargetClass {int field = 5;
class InnerClass {int getValue() {return 3;}}}
class ParentClass {public int parentMethod() {return 0;}
{class InstanceBlockClass extends ParentClass {@Overridepublic int parentMethod() {return super.parentMethod() + 3;}}InstanceBlockClass obj = new InstanceBlockClass();obj.parentMethod();}}