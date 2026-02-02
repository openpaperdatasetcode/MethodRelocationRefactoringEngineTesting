package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass {private TargetClass targetField;private int outerPrivateField = 10;
// Member inner classclass SourceMemberInnerClass {}
// Static nested classpublic static class SourceStaticNestedClass {}
// Instance initializer block (instance code blocks){public int callMethod() {TargetClass.TargetMemberInnerClass inner = targetField.new TargetMemberInnerClass();// Instance reference method referenceRunnable runnable = inner::targetInnerMethod;runnable.run();return inner.targetInnerMethod();}}
// Constructor with parameter list containing abstract method invocationpublic SourceClass() {super();OtherClass other = new OtherClass();// Abstract method invocation in constructor parameter list (obj.m1().m2().m3())List<String> abstractResult = other.m1().m2().m3();this.targetField = new TargetClass(abstractResult);}
// Target method: instance, default access, returns TargetClass typeTargetClass targetClassMethod() {// Super constructor invocation (implicit in constructor, explicit super keywords here)super.toString();// Expression statementint localVar = targetField.getTargetFieldValue() + outerPrivateField;// Variable callTargetClass result = targetField;// Access outer private field (outerPrivateField)System.out.println("Outer private field: " + outerPrivateField);// Super keywordsSystem.out.println(super.getClass().getName());// Used by reflection (simulated usage)try {Method reflectMethod = SourceClass.class.getDeclaredMethod("targetClassMethod");reflectMethod.invoke(this);} catch (Exception e) {// No new exception}return result;}
// Abstract method (private, in source class)private abstract List<String> abstractMethod();
// Other class for method chain invocationpublic static class OtherClass {public MiddleClass1 m1() {return new MiddleClass1();}
public static class MiddleClass1 {public MiddleClass2 m2() {return new MiddleClass2();}}
public static class MiddleClass2 {public List<String> m3() {return List.of("test");}}}}
// Target class: normal class, default modifier, contains member inner classclass TargetClass {private List<String> targetField;
// Target inner class (target_inner_rec)class TargetMemberInnerClass {public int targetInnerMethod() {return 42;}}
public TargetClass(List<String> targetField) {this.targetField = targetField;}
public int getTargetFieldValue() {return targetField.size();}}