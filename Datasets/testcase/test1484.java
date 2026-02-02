package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
class TargetClass extends ParentClass {protected int targetField = 10;
public TargetClass() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
public class TargetInnerRec {public TargetInnerRec() {}
public TargetClass process(int value) {return new TargetClass();}}}
class ParentClass {}
class SourceClass {private TargetClass targetInstance = new TargetClass();
public class LocalInnerClass {public LocalInnerClass() {}}
@TestAnnotationpublic TargetClass process(String str) {LocalInnerClass local = new LocalInnerClass();TargetClass result = new TargetClass() {@Overridepublic String toString() {return super.toString() + str;}};int localVar = targetInstance.targetField;assert localVar == 10 : "Target field value mismatch";return result;}
@TestAnnotationpublic TargetClass process(int num) {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class: " + num);}};anonymous.run();TargetClass.TargetInnerRec inner = targetInstance.new TargetInnerRec();TargetClass result = inner.process(num);assert result.targetField == 10 : "Target inner class field check";return result;}}
// Test case classpublic class MoveMethodTest5145 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target1 = source.process("test");TargetClass target2 = source.process(5);assert target1 != null : "First overloaded method return check";assert target2 != null : "Second overloaded method return check";}}
