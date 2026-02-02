package test;
import java.util.ArrayList;import java.util.List;
abstract class AbstractHelper {protected abstract Object abstractMethod(int num, SourceClass source);}
class SourceClass {protected String outerProtectedField = "protected";
static class StaticNested1 {}static class StaticNested2 {}
// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();
Object methodToMove() {// access_outer_protectedString protectedVal = outerProtectedField;
// variable callStaticNested1 nested1 = new StaticNested1();StaticNested2 nested2 = new StaticNested2();TargetClass target = targetField;
// if statementif (target != null) {target.doSomething();}
// switch caseint caseVal = 1;switch (caseVal) {case 1:break;default:break;}
// numbers:1, modifier:protected, exp:PostfixExpressionint num = 1;num++;
// raw_typeList rawList = new ArrayList();
// abstract method feature (pos:for)AbstractHelper helper = new AbstractHelper() {@Overrideprotected Object abstractMethod(int num, SourceClass source) {return new SourceClass().methodToMove(); // new ClassName().method()}};for (int i = 0; i < 3; i++) {helper.abstractMethod(3, this); // 3, source, abstract}
// call_method: inner_class, public, instance, instanceReference.methodName(arguments), pos:whileTargetClass.LocalInner localInner = target.new LocalInner();int count = 0;while (count < 1) {localInner.innerMethod(count);count++;}
// Access target's field (per_condition verification)Object targetFieldVal = target.targetData;
return new Object();}}
public class TargetClass {Object targetData = "targetField";
// target_feature: local inner classpublic class LocalInner {public Object innerMethod(int arg) { // instance, instanceReference.methodName(arguments)return arg;}}
void doSomething() {}}