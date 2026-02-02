import java.util.Objects;
// TargetClass: normal, package-private, no additional features (meets target_class specs)class TargetClass {// Inner class (target_inner for method's target class)public class InnerClass {private int count;private String data;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public void increment() {count++;}
public int getCount() {return count;}}}
// SourceClass: normal, public, generic, with static nested + anonymous inner class (meets source_class specs)public class SourceClass<T> {// Static nested class (source_feature)public static class StaticNested {// Member inner class (source_inner for method_position)public class NestedInner {// Varargs method: meets method specs (varargs, void return, final, source_inner)public final void processTargets(TargetClass.InnerClass... targets) {// Ensure method contains target parameter (per_condition)if (targets == null || targets.length == 0) {return;}
loopLabel:for (TargetClass.InnerClass target : targets) {// If statement (method_feature)if (target == null) {continue;}
// Synchronized statement (method_feature)synchronized (target) {// Expression statement (method_feature)target.setData("processed_" + target.getCount());
// Variable call (method_feature)target.increment();
// Call source method in if/else (call_method specs: pos=if/else conditions)int length = (target.getData().length() > 10)? SourceClass::calculateLength.apply(target.getData()): SourceClass::calculateLength.apply("short_data");
// Break statement (method_feature)if (length > 15) {break loopLabel;}}}}}}
// Anonymous inner class (source_feature)private final Runnable processor = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();StaticNested.NestedInner inner = new StaticNested().new NestedInner();inner.processTargets(target.new InnerClass(), target.new InnerClass()); // No exception (no_new_exception)}};
// Method for call_method (type=source, default modifier, normal, ClassName::methodName)static int calculateLength(String data) {return data == null ? 0 : data.length();}
// Functional interface for method reference@FunctionalInterfaceprivate interface LengthCalculator {int apply(String data);}
public void startProcessing() {processor.run();}
public static void main(String[] args) {new SourceClass<String>().startProcessing();}}