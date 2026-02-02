package test;
import java.util.ArrayList;
public class SourceClass<T> extends ArrayList<T> {private TargetClass<String> target = new TargetClass<>();
protected int methodToMove(T... args) {// variable callTargetClass.StaticNested nested = new TargetClass.StaticNested();nested.doSomething();
// SuperConstructorInvocation in same_package_othersOtherClass other = new OtherClass(target.targetField, 2);
// instance method in exception throwing statementstry {nested.getInstance().m1().m2().m3();} catch (Exception e) {throw new RuntimeException();}
// override_violation (TargetClass has same method)target.methodToMove(1);
// source contains target's field (per_condition)Object fieldVal = target.targetField;
return 0; // base type}}
class TargetClass {
String targetField;
// static nested class (target_feature)static class StaticNested {private NestedInstance getInstance() {return new NestedInstance();}
void doSomething() {}}
class NestedInstance {NestedInstance m1() { return this; }NestedInstance m2() { return this; }void m3() {}}
// override_violation (same method signature)protected int methodToMove(U... args) {return 0;}}
class OtherClass {// SuperConstructorInvocation targetprivate OtherClass(String field, int num) {super();}}