import java.util.Objects;
class SourceClass {private TargetClass targetField = new TargetClass();
/**
Overloaded method to process TargetClass instance
@param target TargetClass parameter to handle
@return Processed Object result*/public final Object overloadedMethod(TargetClass target) {super();
Runnable lambda = () -> {TargetClass.StaticNested nested = new TargetClass.StaticNested();int baseVal = nested.m1().m2().m3();};lambda.run();
variableCall(target);return target;}
/**
Overloaded method with String parameter
@param str String parameter
@return String wrapped in Object
*/
public final Object overloadedMethod(String str) {
return str;
}
private void variableCall(TargetClass target) {target.StaticNested.staticMethod(10);}
public class MemberInnerClass {int callInTernary(boolean flag) {return flag ? TargetClass.StaticNested.staticMethod(5) : TargetClass.StaticNested.staticMethod(3);}}
public static class StaticNestedClass {TargetClass getTargetInstance() {return new TargetClass();}}}
class TargetClass extends SuperTargetClass {public static class StaticNested {private int value;
public StaticNested m1() {this.value += 1;return this;}
public StaticNested m2() {this.value += 2;return this;}
public int m3() {return this.value;}
public static int staticMethod(int param) {return param * 2;}}}
class SuperTargetClass {}