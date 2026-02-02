package test;
public class SourceClass {public record SourceInnerRec() {@MyAnnotationpublic int instanceMethod(TargetClass target) {// Type declaration statementTargetClass.MemberInner inner = new TargetClass.MemberInner();
// Constructor invocationTargetClass newTarget = new TargetClass();
// Access target instance fieldint value = target.targetField;
// Variable callvalue += inner.getInnerField();
// Assert statementassert value > 0 : "Value must be positive";
return value;}}
public void outerMethod() {// First local inner classclass LocalInnerOne {void useInnerRec() {TargetClass target = new TargetClass();int result = new SourceInnerRec().instanceMethod(target);System.out.println(result);}}
// Second local inner classclass LocalInnerTwo {void invokeOne() {new LocalInnerOne().useInnerRec();}}
new LocalInnerTwo().invokeOne();}}
public class TargetClass {int targetField = 5;
public class MemberInner {int innerField = 3;
int getInnerField() {return innerField;}}}
@interface MyAnnotation {}
