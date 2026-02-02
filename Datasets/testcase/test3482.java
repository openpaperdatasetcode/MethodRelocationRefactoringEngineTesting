package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
interface MyInterface {}
class SourceClass {TargetClass targetField;
class MemberInner {class InnerRec {private abstract TargetClass moveMethod();}}
void someMethod() {class LocalInner {{protected List<String> overrideMethod() {TargetClass obj = new TargetClass();return obj.m1().m2().m3();}}}}
public void callMethod(SourceClass instance) {if (instance == null) {return;} else {instance.callMethod(instance);instance.targetField.someAction();}}
private void variableCall() throws IOException {targetField.localInnerAction();}}
class TargetClass implements MyInterface {TargetClass m1() { return this; }TargetClass m2() { return this; }List<String> m3() { return new ArrayList<>(); }
void someAction() {}
void localInnerAction() {class LocalInner {private TargetClass moveMethod() {return new TargetClass();}}}}