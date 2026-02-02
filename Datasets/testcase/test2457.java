package test.refactoring.movemethod;
public class TargetClass {public static int staticField = 10;
public class TargetInner {private String name;
public TargetInner(String name) {this.name = name;}
public String getName() {return name;}}}
private class SourceClass {protected int outerProtected = 5;static class SourceStaticNested {}class SourceMemberInner {}
@strictfppublic TargetClass process(TargetClass target) {// Constructor invocationTargetClass.TargetInner inner = target.new TargetInner("test");
// Variable callObject varCall = inner.getName();
// Access outer protectedint sum = outerProtected + TargetClass.staticField;
// Depends on static fieldassert sum == 15 : "Sum should be 15"; // Assert statement
return target;}
// Overloading method (no type parameters)@strictfppublic TargetClass process(String name) {TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner(name);return process(target); // Call overloaded method}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3150 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Test first overloaded methodTargetClass result1 = source.process(target);assertNotNull(result1);
// Test second overloaded methodTargetClass result2 = source.process("overload_test");assertNotNull(result2);}}