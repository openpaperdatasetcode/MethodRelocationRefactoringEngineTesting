package test;
import java.lang.reflect.Method;
public record SourceRecord(String data) {static class StaticNested1 {}static class StaticNested2 {}
class InnerClass {/**
Normal method with required features
@param target Target record instance
@return Base type result*/protected int process(TargetRecord target) {// Expression statementtarget.instanceField = 100;
variableCall(target);new StaticNested1();new StaticNested2();
// Used by reflectiontry {Method method = TargetRecord.TargetInner.class.getMethod("doTask");method.invoke(target.innerClass);} catch (Exception e) {}
return target.instanceField;}
private void variableCall(TargetRecord target) {target.innerClass.helper();}}
public int callMethod(TargetRecord target) {int count = 0;// While position for call_methodwhile (count < 2) {count += new InnerClass().process(target);count += overloadProcess(target, count);}return count;}
// Overloading with super.methodName(arguments)public int overloadProcess(TargetRecord target, int offset) {super.toString();return target.instanceField + offset;}}
non-sealed record TargetRecord(String data) {public int instanceField = 0;
class TargetInner {void doTask() {}void helper() {}}
TargetInner innerClass = new TargetInner();
protected int process() {return instanceField;}
public int process(int offset) {super.hashCode();return instanceField + offset;}}