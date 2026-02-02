package test;
interface ParentInterface {String superField1 = "super1";String superField2 = "super2";String superField3 = "super3";TargetInterface recursionMethod(TargetInterface target);}
public interface TargetInterface extends ParentInterface {default void targetMethod() {new Runnable() {@Overridepublic void run() {System.out.println(superField1);}}.run();}}
interface SourceInterface extends ParentInterface {static class StaticNested {}
default int normalMethod(TargetInterface target) {new Runnable() {@Overridepublic void run() {SourceInterface.StaticNested staticNested = new SourceInterface.StaticNested();}}.run();
private labeled: {System.out.println(target.superField1);break labeled;}
System.out.println(superField1);System.out.println(superField2);System.out.println(superField3);
for (int i = 0; i < 1; i++) {TargetInterface result = SubClass::recursionMethod;variableCall(target);}
{OtherClass.overloadMethod((String s) -> System.out.println(s));OtherClass.overloadMethod((Integer i) -> System.out.println(i));}
return target.superField1.length();}
private default void variableCall(TargetInterface target) {target.targetMethod();}}
class SubClass implements SourceInterface {@Overridepublic TargetInterface recursionMethod(TargetInterface target) {if (target == null) return null;return recursionMethod(target);}}
strictfp class OtherClass {public static void overloadMethod(StringProcessor processor) {processor.process("string");}
public static void overloadMethod(IntProcessor processor) {processor.process(123);}
@FunctionalInterfaceinterface StringProcessor {void process(String s);}
@FunctionalInterfaceinterface IntProcessor {void process(Integer i);}}