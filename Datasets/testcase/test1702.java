package test;
import java.lang.annotation.*;import java.util.List;import java.util.stream.Collectors;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value();}
protected enum SourceEnum implements Runnable {INSTANCE;
class MemberInner1 {class MemberInner2 {int varargsMethod(TargetEnum.MemberInner... args) {super();variableCall();
// Abstract method in collection operationsList<TargetEnum.MemberInner> list = List.of(args);list.stream().map(SuperType::abstractMethod).collect(Collectors.toList());
// Access instance method of target inner classfor (TargetEnum.MemberInner arg : args) {arg.targetField = 0; // Access target fieldarg.instanceMethod();}
// Override violation (final method in parent)class BadInner extends ParentClass {@Overridepublic final void finalMethod() {} // Compile error expected}
return 0;}
private void variableCall() {}}}
@Overridepublic void run() {}}
/**
Javadoc for TargetEnum*/enum TargetEnum {VALUE;
class MemberInner {int targetField;
final void recursiveMethod() {recursiveMethod(); // Recursion}
void instanceMethod() {}}}
abstract class SuperType {abstract void abstractMethod(TargetEnum.MemberInner inner);}
class ParentClass {public final void finalMethod() {}}
class AnnotationUser {@MyAnnotation(value = "call: " + TargetEnum.VALUE.new MemberInner().recursiveMethod())void annotatedMethod() {}}