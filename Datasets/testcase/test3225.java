package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
// Target interface (for target's implements feature)interface TargetInterface {void interfaceMethod();}
// Source class (public modifier, two member inner classes)public class SourceClass {private TargetClass targetField = new TargetClass(); // Per condition: source contains target field
// First member inner class (source feature)public class FirstMemberInner {}
// Second member inner class (source feature)public class SecondMemberInner {}
// Normal method feature (1, source, normal, obj.m1().m2().m3(), pos: exception handling statements)public Object normalFeatureMethod(FirstMemberInner inner) {return inner.toString().concat("feature").intern();}
// Static method (default access modifier, returns TargetClass Type)static TargetClass staticMethod() {// Super constructor invocation (target's parent class)TargetClass target = new TargetClass();TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();
// MethodReference (numbers=2, modifier=public)public Function<String, Integer> ref1 = String::length;public Function<TargetClass, String> ref2 = TargetClass::toString;
// Raw typeList rawList = new ArrayList();
// Variable calltarget.interfaceMethod();target.staticNestedMethod();innerRec.recursiveAction();
// Normal method feature in exception handlingtry {FirstMemberInner firstInner = new SourceClass().new FirstMemberInner();Object featureResult = firstInner.toString().concat("chain").intern();rawList.add(featureResult);} catch (Exception e) {// No new exception}
return target;}}
// Target class (default modifier, implements + static nested class)class TargetClass extends TargetParent implements TargetInterface {public int targetField = 5; // Field for per_condition
public TargetClass() {super(); // Super constructor invocation}
@Overridepublic void interfaceMethod() {}
public void staticNestedMethod() {TargetStaticNested.nestedMethod();}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public static void nestedMethod() {}}}
// Target parent class (for super constructor invocation)class TargetParent {}