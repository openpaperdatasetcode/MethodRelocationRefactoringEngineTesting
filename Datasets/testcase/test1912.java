package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
sealed abstract class SourceClass implements Runnable permits SubSource {private TargetClass target = new TargetClass();
class SourceMemberInner {void useTargetInner() {target.new TargetMemberInner().new TargetInnerRec().doAction();}}
void method() {class LocalInner {Supplier<TargetClass.TargetMemberInner.TargetInnerRec> getInnerRec() {return () -> target.new TargetMemberInner().new TargetInnerRec();}}
new LocalInner().getInnerRec().get();new SourceMemberInner().useTargetInner();
target.field = "test";; // Empty statement
if (target.field == null) {throw new NullPointerException("Field is null");}
try {Method innerMethod = TargetClass.TargetMemberInner.TargetInnerRec.class.getMethod("doAction");innerMethod.invoke(target.new TargetMemberInner().new TargetInnerRec());} catch (Exception e) {}
new TargetClass();}
@Overridepublic void run() {}}
abstract class SubSource extends SourceClass {}
/**
Target abstract class with member inner class and recursive inner class*/abstract class TargetClass {String field;
/**
First-level member inner class
/
class TargetMemberInner {
/*
Recursive inner class (target_inner_rec)
*/
class TargetInnerRec {
void doAction() {
System.out.println(field);
}
}
}
}