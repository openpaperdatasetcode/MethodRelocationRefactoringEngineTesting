import java.lang.reflect.Method;import java.util.Objects;
// Target interface: package-private, with anonymous inner class (meets target_class specs)interface TargetInterface {String getData();void setData(String data);
// Anonymous inner class (target_feature)TargetInterface DEFAULT = new TargetInterface() {private String data = "default";
@Overridepublic String getData() {return data;}
@Overridepublic void setData(String data) {this.data = data;}};
default TargetInterface createNew() {return new TargetInterface() {private String data;
@Overridepublic String getData() {return data;}
@Overridepublic void setData(String data) {this.data = data;}};}}
// Source interface: package-private, with local inner + static nested class (meets source_class specs)interface SourceInterface {// Static nested class (source_feature)class StaticNested {// Inner class with recursive logic (source_inner_rec for method_position)protected class NestedInner {// Varargs method: meets method specs (varargs, base type return, protected, source_inner_rec)protected int processTargets(TargetInterface... targets) {// Ensure method contains target parameter (per_condition)if (targets == null || targets.length == 0) {return 0;}
// Type declaration statement (method_feature)int totalLength = 0;int index = 0;
// While loop (pos=while for method_feature)while (index < targets.length) {TargetInterface target = targets[index];Objects.requireNonNull(target);
// Variable call (method_feature)target.setData("processed_" + index);
// Method feature: instance, protected, 3 calls, target, new ClassName().method()TargetInterface new1 = new TargetInterface() {private String data;@Override public String getData() { return data; }@Override public void setData(String d) { data = d; }};new1.setData(target.getData() + "_1");
TargetInterface new2 = new TargetInterface() {private String data;@Override public String getData() { return data; }@Override public void setData(String d) { data = d; }};new2.setData(new1.getData() + "_2");
TargetInterface result = new TargetInterface() {private String data;@Override public String getData() { return data; }@Override public void setData(String d) { data = d; }};result.setData(new2.getData() + "_3");
totalLength += result.getData().length();index++;}
// Used by reflection (method_feature)try {Method getDataMethod = TargetInterface.class.getMethod("getData");totalLength += ((String) getDataMethod.invoke(targets[0])).length();} catch (Exception e) {// No new exception (method_feature)}
return totalLength; // Base type return}
// Recursive helper methodprotected int recursiveProcess(TargetInterface... targets) {if (targets.length == 0) {return 0;}// Local inner class (source_feature)class LocalProcessor {int process() {return processTargets(targets) + recursiveProcess(trimArray(targets));}
TargetInterface[] trimArray(TargetInterface[] arr) {if (arr.length <= 1) {return new TargetInterface[0];}TargetInterface[] newArr = new TargetInterface[arr.length - 1];System.arraycopy(arr, 1, newArr, 0, newArr.length);return newArr;}}return new LocalProcessor().process();}}}
// Default method to trigger processingdefault int startProcessing(TargetInterface... targets) {StaticNested.NestedInner inner = new StaticNested().new NestedInner();return inner.recursiveProcess(targets);}}
// Concrete class implementing SourceInterface for testingclass SourceImpl implements SourceInterface {}
// Test entrypublic class InterfaceTest {public static void main(String[] args) {SourceInterface source = new SourceImpl();TargetInterface t1 = TargetInterface.DEFAULT;TargetInterface t2 = TargetInterface.DEFAULT.createNew();int result = source.startProcessing(t1, t2); // No new exceptionSystem.out.println("Total length: " + result);}}