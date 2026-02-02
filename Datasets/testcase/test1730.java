package test;
import java.lang.reflect.Field;import java.util.List;
final class SourceClass implements Runnable {static class StaticNested {}
/**
Varargs method with reflection and overload features
@param targets array of TargetClass instances
@return Object instance*/private Object varargsMethod(TargetClass... targets) {class LocalInner {}
// Access target parameter fieldsfor (TargetClass target : targets) {String fieldVal = target.targetField;}
// Break statementloop:for (int i = 0; i < targets.length; i++) {if (targets[i] == null) {break loop;}}
// Synchronized statementif (targets.length > 0) {synchronized (targets[0]) {variableCall();}}
// Overloaded methods existoverloadedMethod();overloadedMethod(1);overloadedMethod("string");
// Used by reflectiontry {Field field = TargetClass.class.getDeclaredField("targetField");field.setAccessible(true);} catch (NoSuchFieldException e) {// Handle exception}
return new Object();}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}private void overloadedMethod(String str) {}
@Overridepublic void run() {}}
/**
Javadoc documentation for TargetClass
Contains target field and anonymous inner class*/public class TargetClass {String targetField;
{// Anonymous inner classnew Runnable() {@Overridepublic void run() {}};}}