package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnot {}
class ParentSourceClass {}
public class SourceClass extends ParentSourceClass {class SourceInner {/**
Overloading method 1 (target class: target_inner)*/@TestAnnotprivate TargetClass.TargetInner methodToMove(TargetClass target) throws IOException {// Overloading method reference in for statementfor (int i = 0; i < 1; i++) {Runnable ref = target::overloadMethod;ref.run();}
// Expression statement + variable callTargetClass.TargetInner inner = target.new TargetInner();inner.toString();String targetField = inner.targetField; // Source contains target's field
// Raw typeList rawList = new ArrayList();rawList.add(inner);
return inner;}
/**
Overloading method 2
*/
protected Object methodToMove(TargetClass target, String arg) {
return target.new TargetInner();
}
}
public void createLocalInner() {class LocalInnerSource {}}}
/**
Javadoc for TargetClass (target_feature: javadoc)*/public class TargetClass {class TargetInner {public String targetField = "targetVal"; // Per_condition: source contains this field
{// Anonymous inner class (target_feature)new Runnable() {};}}
// Overloading method for referencepublic void overloadMethod() {}}
class SubClass extends SourceClass {default void callMethod(TargetClass target) {int i = 0;while (i < 1) {// Call outerInstance.new InnerClass().methodName() + overloadingTargetClass.TargetInner inner = target.new TargetInner();new SourceClass().new SourceInner().methodToMove(target, "arg");i++;}}}