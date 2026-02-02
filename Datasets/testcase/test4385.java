package test;
import java.util.List;
protected class SourceClass implements Runnable {// Member inner classes (matches source_class.feature)class SourceInnerOne {void innerMethodOne() {}}
class SourceInnerTwo {void innerMethodTwo(int val) {}}
// Outer private field (for access_outer_private)private int outerPrivateField = 5;
/**
Javadoc for the refactored method (matches method.features: method javadoc)
@param targetParam Target class instance containing inner class
@param listParam List-type parameter (matches method types parameter is:List)*/public synchronized void moveMethod(TargetClass targetParam, List<String> listParam) {// super keywords (matches method.features)super.toString();
// Access target's inner class (matches target class: target_inner_rec)TargetClass.TargetInner inner = targetParam.new TargetInner();
// Expression statement + variable call (matches method.features)int targetFieldVal = targetParam.targetField;listParam.add(String.valueOf(targetFieldVal));
// access_outer_private: access source's private fieldint sum = outerPrivateField + targetFieldVal;
// depends_on_inner_class: use source's inner class instancesSourceInnerOne innerOne = new SourceInnerOne();SourceInnerTwo innerTwo = new SourceInnerTwo();innerOne.innerMethodOne();innerTwo.innerMethodTwo(sum);
// access_instance_method: call target's instance methodtargetParam.targetInstanceMethod();}
@Overridepublic void run() {}}
public class TargetClass {// Target field (for variable call)int targetField = 10;
// Target inner class (matches target class: target_inner_rec)class TargetInner {void recursiveMethod(int count) {if (count <= 0) return;recursiveMethod(count - 1); // Recursive call (rec in target_inner_rec)}}
// Instance method (for access_instance_method)void targetInstanceMethod() {}
// Anonymous inner class (matches target_class.target_feature)Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {new TargetInner().recursiveMethod(3);}};}