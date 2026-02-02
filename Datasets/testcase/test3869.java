package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
non-sealed enum SourceEnum<T> implements MyInterface<T> permits SubSourceEnum {INSTANCE;
TargetEnum targetField = TargetEnum.VALUE;private int outerPrivateField = 42;
class FirstInnerClass {}class SecondInnerClass {void innerMethod() {}}
/**
Method Javadoc*/@MyAnnotationdefault void methodToMove(String... args) {FirstInnerClass inner = new FirstInnerClass();SecondInnerClass anotherInner = new SecondInnerClass();anotherInner.innerMethod();
int localVar = outerPrivateField;variableCall();
for (String s : args) {new TargetEnum.ConstructorArg(s);}}
private void variableCall() {}}
enum SubSourceEnum implements SourceEnum<String> {}
interface MyInterface<T> {}
enum TargetEnum {VALUE;
static class ConstructorArg {ConstructorArg(String s) {}}}