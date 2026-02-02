package test;
import java.util.function.Supplier;
private class SourceClass {static class StaticNested {}
class MemberInner {}
private TargetClass targetField = new TargetClass();
/**
Demonstrates method features including super method references
@return Object instance*/public Object methodToMove() {class LocalType {}LocalType local = new LocalType();
Supplier<Object> sup1 = SuperClass::new;Supplier<String> sup2 = SuperClass::getValue;Runnable run = SuperClass::print;
targetField.variableCall();Object result = TargetClass.StaticField;
return new Object();}}
class SuperClass {static String getValue() { return ""; }static void print() {}}
public class TargetClass {public static Object StaticField = new Object();
void variableCall() {class TargetInner {class TargetInnerRecursive {void use() {}}}new TargetInner().new TargetInnerRecursive().use();}}