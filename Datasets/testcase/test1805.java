package test;
import java.lang.reflect.Method;
public class SourceClass {public class MemberInner {// Overloading methodspublic void overloadedMethod(TargetClass target) {// ExpressionStatement with private modifier using target static fieldprivate class PrivateExprContainer {void execute() {System.out.println(TargetClass.STATIC_FIELD);}}new PrivateExprContainer().execute();
// Switch caseswitch (target.instanceField) {case 1:System.out.println("Case 1: " + target.instanceField);break;default:System.out.println("Default case");}
// Variable call + depends on static fieldint value = target.instanceField + TargetClass.STATIC_FIELD;System.out.println("Combined value: " + value);
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("getInstanceField");System.out.println("Reflected value: " + method.invoke(target));} catch (Exception e) {}}
public void overloadedMethod(TargetClass target, String extra) {System.out.println("Overloaded with extra: " + extra + ", Field: " + TargetClass.STATIC_FIELD);}
// Overriding method in instance code blocks (via anonymous subclass)Runnable overridingRunnable = new Runnable() {@Overridepublic void run() {// Lambda expression for overriding-related logic (2 parameters)TwoParamProcessor processor = (a, b) -> System.out.println("Processed: " + (a + b));processor.process(target.instanceField, TargetClass.STATIC_FIELD);}};}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();MemberInner inner = new MemberInner();inner.overloadedMethod(target);inner.overloadedMethod(target, "test");inner.overridingRunnable.run();}}.run();}
// Functional interface for lambda (2 parameters)@FunctionalInterfaceprivate interface TwoParamProcessor {void process(int a, int b);}}
/**
Target class with javadoc and protected modifier
Contains static field and instance field used by source class*/protected class TargetClass {public static final int STATIC_FIELD = 10;public int instanceField = 5;
public int getInstanceField() {return instanceField;}}