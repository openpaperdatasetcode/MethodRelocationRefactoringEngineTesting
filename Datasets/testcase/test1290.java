package test.refactor.movemethod;
/**
Javadoc for TargetEnum (target_feature: javadoc)
Public enum class serving as target for move method refactoring*/public enum TargetEnum {INSTANCE;
public int targetField = 42; // Target field (per_condition: source contains target field)
public void targetInstanceMethod() {System.out.println("TargetEnum instance method");}}
// Source enum class (private, same package, member inner class, static nested class)private enum SourceEnum {VALUE;
private String sourceField = "sourceValue";
// Static nested class (source_class feature)public static class SourceStaticNestedClass {public static String staticNestedField = "staticNestedData";}
// Member inner class (source_class feature)public class SourceMemberInnerClass {// Abstract method to be refactored (public, base type return, method_position: source_inner)public abstract int methodToRefactor();}
// Concrete implementation of abstract refactored methodpublic class ConcreteInnerClass extends SourceMemberInnerClass {@Overridepublic int methodToRefactor() {// Variable callint targetFieldVal = TargetEnum.INSTANCE.targetField; // Access target field (per_condition)String nestedFieldVal = SourceStaticNestedClass.staticNestedField;System.out.println("Variable call: " + targetFieldVal + ", " + nestedFieldVal);
// Uses_outer_thisString outerFieldVal = SourceEnum.this.sourceField; // OuterClass.this.x (SourceEnum.this.sourceField)System.out.println("Uses outer this: " + outerFieldVal);
// No_new_exception (no checked exceptions thrown)return targetFieldVal; // Base type return (int)}}
// Method to create inner class instancepublic SourceMemberInnerClass createInnerInstance() {return new ConcreteInnerClass();}}
// Test classpublic class MoveMethodRefactorTest_5211 {public static void main(String[] args) {SourceEnum source = SourceEnum.VALUE;SourceEnum.SourceMemberInnerClass inner = source.createInnerInstance();int result = inner.methodToRefactor();System.out.println("Refactored method result (base type): " + result);}}