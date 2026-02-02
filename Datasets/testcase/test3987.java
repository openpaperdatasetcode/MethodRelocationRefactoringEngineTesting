package test;
import java.lang.reflect.Method;
class DiffPackageOthers {private Object lock1 = new Object();private Object lock2 = new Object();}
private class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInnerRec {private DiffPackageOthers diffOthers = new DiffPackageOthers();
/**
Javadoc for varargsMethod: processes TargetClass instances and returns combined result*/public Object varargsMethod(TargetClass... targets) {char charLiteral = 'A';Object result = new Object();
synchronized (diffOthers.lock1) {for (TargetClass target : targets) {String expr = target.targetField + charLiteral;result = expr;}}
synchronized (diffOthers.lock2) {if (targets.length > 0) {TargetClass firstTarget = targets[0];Object varCall = firstTarget.targetField;result = varCall;}}
try {Method targetMethod = TargetClass.class.getMethod("getTargetField");result = targetMethod.invoke(targets[0]);} catch (Exception e) {}
return result;}}}
public class TargetClass {String targetField = "targetInstanceVal";
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}
String getTargetField() {return targetField;}}