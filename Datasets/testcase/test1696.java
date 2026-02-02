package test;
import java.lang.reflect.Method;
class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {/**
Varargs method with do-while and reflection*/strictfp Object varargsMethod(AbstractTargetClass.StaticNested... args) {super.toString();variableCall();
// DoStatement accessing ClassName.fieldint count = 0;do {if (count >= args.length) {continue;}String fieldVal = AbstractTargetClass.staticField;count++;} while (count < 5);
// Access instance method of target inner classfor (AbstractTargetClass.StaticNested arg : args) {arg.instanceMethod();}
// Used by reflectiontry {Method method = InnerRec.class.getMethod("varargsMethod", AbstractTargetClass.StaticNested[].class);} catch (NoSuchMethodException e) {}
// Override violation (assuming method is final in parent)class LocalInner extends ParentClass {@Overridepublic final void finalMethod() {} // Compile error expected (override violation)}
return new Object();}
private void variableCall() {}}}}
abstract class AbstractTargetClass {static String staticField;
static class StaticNested {void instanceMethod() {}}}
class ParentClass {public final void finalMethod() {}}