package test;
interface ParentInterface {}
interface SourceClass<T> extends ParentInterface {int SOURCE_FIELD = 10;
static class StaticNested {TargetClass target = new TargetClass();}
default int method() {class LocalInner {int calculate(TargetClass target) {int var = target.targetField;if (var > SOURCE_FIELD) {var -= SOURCE_FIELD;}String[] arr = {target.privateInstanceMethod(var)};return Integer.parseInt(arr[0]);}}
TargetClass targetInstance = new TargetClass();LocalInner local = new LocalInner();return local.calculate(targetInstance);}}
public interface TargetClass {int targetField = 15;
default String privateInstanceMethod(int arg) {class TargetLocalInner {String process(int val) {return super.toString() + val;}}return new TargetLocalInner().process(arg);}}