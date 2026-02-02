package test;
import java.lang.reflect.Constructor;
class Parent<T> {}
private
Source class documentation*/private class SourceClass<T extends Number> {class MemberInner {class InnerRec {public Object moveMethod(TargetClass<T>.Inner targetInner) throws Exception {class LocalInner {}LocalInner li = new LocalInner();
variableCall(targetInner);targetInner.overload(1);targetInner.overload("str");
String result = targetInner.m1().m2().m3();
Constructor<?> constructor = TargetClass.Inner.class.getConstructor();Object instance = constructor.newInstance();
return instance;}
private void variableCall(TargetClass<T>.Inner inner) {}}}}
/**
Target class documentation/
public class TargetClass<T extends CharSequence> {
/*
Static nested class with javadoc
*/
static class StaticNested {}
class Inner {final Inner m1() { return this; }final Inner m2() { return this; }final String m3() { return ""; }
void overload(int i) {}void overload(String s) {}}}