package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
sealed abstract class SourceClass permits SubClass {class SourceInner {@MethodAnnotation // has_annotationpublic static List<String> methodToMove(TargetClass target) {// Anonymous inner class 1Runnable anon1 = new Runnable() {@Overridepublic void run() {}};// Anonymous inner class 2Runnable anon2 = new Runnable() {@Overridepublic void run() {}};
// Variable call (access target's member inner class field)int val = target.memberInner.field;List<String> result = new ArrayList<>();result.add(String.valueOf(val));
return result;}}}
abstract class TargetClass {MemberInner memberInner = new MemberInner();
class MemberInner {int field = 10;}}
final class SubClass extends SourceClass {private int callMethod() {try {throw new RuntimeException();} catch (Exception e) {// Call method in exception throwing statementsTargetClass target = new TargetClass() {}; // constructorreturn SourceClass.SourceInner::methodToMove; // ClassName::methodName}}}
