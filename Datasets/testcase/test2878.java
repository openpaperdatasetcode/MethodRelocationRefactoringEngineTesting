package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public class SourceClass {private int outerPrivateField = 3;
class MemberInner {}
private Object methodToMove(TargetClass... targets) {class LocalInnerInSource {}
@TestAnnotationint num = 3;num++; // PostfixExpression with number 3
if (targets.length == 0) {return null;}
targets[0].process();int val = outerPrivateField; // Access outer private
return new Object();}}
class TargetClass {void process() {class LocalInnerInTarget {}}}