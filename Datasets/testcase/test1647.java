package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
private class SourceClass {class SourceInner {private TargetClass methodToMove(TargetClass.TargetParam param) { // contains target parameter (per_condition)// super constructor invocationTargetClass target = new TargetClass() {@Overridevoid init() {super.init();}};
// variable callTargetClass.MemberInner inner = target.new MemberInner();int val = inner.getCount(); // accessor (public modifier)
// expression statementinner.setCount(1); // 1 (base type)
// this.methodName(arguments)this.process(param);
// functional interfaces with accessor featuresSupplier<Integer> supplier = () -> inner.getCount(); // inner_class access
// with_boundsclass Bounded<T extends Number> {T value;}Bounded<Integer> bounded = new Bounded<>();bounded.value = 5;
// used_by_reflectiontry {Method method = TargetClass.MemberInner.class.getMethod("getCount");} catch (Exception e) {}
// return statementreturn target;}
private void process(TargetClass.TargetParam param) {}}}
private class TargetClass {static class TargetParam {}
class MemberInner { // member inner class (target_feature)private int count;
public int getCount() { // accessorreturn count;}
public void setCount(int count) { // accessorthis.count = count;}
private int superCall() {return super.hashCode(); // super.methodName()}}
void init() {}}