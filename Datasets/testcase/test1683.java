package test;
import java.sql.SQLException;
protected record SourceClass(String val) {static class StaticNested {}
{new Runnable() {};}
/**
Test method for move refactoring*/final void instanceMethod(TargetClass target) throws SQLException {super();String f1 = target.field1;int f2 = target.field2;boolean f3 = target.field3;
Object result = (val != null) ? ParentClass.method1() : ParentClass.method2();variableCall();SourceClass.this.val();
Runnable r = () -> new TargetClass.StaticNested().method();}
private void variableCall() {}}
strictfp record TargetClass(String field1, int field2, boolean field3) {static class StaticNested {private void method() {}}}
class ParentClass {private static Object method1() { return new Object(); }private static Object method2() { return new Object(); }}