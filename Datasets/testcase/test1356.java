package test.refactoring.movemethod;
import java.io.IOException;
/**
Interface for source class implementation
*/
interface TestInterface {}
/**
Permitted class for source class
*/
sealed interface PermittedInterface permits SourceClass {}
/**
Target class with static nested class/
class TargetClass {
/*
Static nested class in target class
*/
static class StaticNestedClass {}
/**
Protected overloading method 1
@param str string parameter
*/
protected void targetMethod(String str) {}
/**
Protected overloading method 2 (overloading feature)
@param str string parameter
@param num int parameter
*/
protected void targetMethod(String str, int num) {}
}
/**
Private source class with required features
/
private class SourceClass implements TestInterface, PermittedInterface {
/*
Member inner class (source_inner position for target method)/
class MemberInnerClass {
/*
Local inner class inside member inner class
*/
void localInnerContainer() {
class LocalInnerClass {}
LocalInnerClass local = new LocalInnerClass();
}
/**
Javadoc for overriding final void method
@param target target class parameter (per_condition: contains target parameter)
*/
@FunctionalInterface // Annotation feature
@Override
public final void targetMethod(TargetClass target) {
String literal = "test string"; // StringLiteral parameter
try {
do {
// Variable call + super.methodName(arguments) + do-while position
super.targetMethod(literal);
target.targetMethod(literal, 123);
} while (literal.length() > 0);
} catch (NullPointerException e) {
// Requires try-catch feature
e.printStackTrace();
}
}
}
}
/**
Test class for Move Method refactoring
*/
public class MoveMethodRefactoringTest5323 {
public static void main(String[] args) {
SourceClass source = new SourceClass();
SourceClass.MemberInnerClass inner = source.new MemberInnerClass();
TargetClass target = new TargetClass();
inner.targetMethod(target);
}
}