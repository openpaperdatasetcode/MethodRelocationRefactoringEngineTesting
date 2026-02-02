package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
protected class SourceClass<T extends CharSequence> {// Member inner class (source_feature)public class SourceMemberInner {public void process(T value) {System.out.println(value);}}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
// Method types parameter: genericpublic TargetClass<T>.TargetInner methodToMove(TargetClass<T> target) {super(); // Super constructor invocation// Variable call + contains target parameter (per_condition)target.toString();TargetClass<T>.TargetInner inner = target.new TargetInner();
// Expression statementinner.setInnerField(target.getData() + "_processed");
// Inner class with SynchronizedStatement (ClassName.field: 3)class SyncInner {public void processStaticFields() {// SynchronizedStatement with 3 ClassName.fieldsynchronized (TargetClass.STATIC_FIELD1) {synchronized (TargetClass.STATIC_FIELD2) {synchronized (TargetClass.STATIC_FIELD3) {inner.appendData(TargetClass.STATIC_FIELD1);inner.appendData(TargetClass.STATIC_FIELD2);inner.appendData(TargetClass.STATIC_FIELD3);}}}}}new SyncInner().processStaticFields();
// Exception handling statements with target static method calltry {List<String> staticResult1 = TargetClass.staticMethod1(target);List<String> staticResult2 = TargetClass.staticMethod2(target, 10);List<String> staticResult3 = TargetClass.staticMethod3(target, "suffix");inner.setStaticResults(List.of(staticResult1, staticResult2, staticResult3));} catch (Exception e) {// No new exception}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("staticMethod1", TargetClass.class);method.invoke(null, target);} catch (Exception e) {// No new exception}
return inner;}}
final class TargetClass<T extends CharSequence> {// Static fields (ClassName.field: 3)public static final String STATIC_FIELD1 = "static1";public static final String STATIC_FIELD2 = "static2";public static final String STATIC_FIELD3 = "static3";
private T data;
public TargetClass(T data) {this.data = data;}
// Static nested class (target_feature)public static class TargetStaticNested {}
public class TargetInner {private T innerField; // Source contains target's field (per_condition)private List<List<String>> staticResults;
public T getInnerField() {return innerField;}
public void setInnerField(T innerField) {this.innerField = innerField;}
public void appendData(String s) {this.innerField = (T) (innerField + s);}
public void setStaticResults(List<List<String>> staticResults) {this.staticResults = staticResults;}}
public T getData() {return data;}
// Target static methods (3)public static <T extends CharSequence> List<String> staticMethod1(TargetClass<T> target) {List<String> list = new ArrayList<>();list.add(target.getData().toString());return list;}
public static <T extends CharSequence> List<String> staticMethod2(TargetClass<T> target, int num) {List<String> list = new ArrayList<>();list.add(target.getData().toString() + "_" + num);return list;}
public static <T extends CharSequence> List<String> staticMethod3(TargetClass<T> target, String suffix) {List<String> list = new ArrayList<>();list.add(target.getData().toString() + suffix);return list;}}
class OthersClass {// Private others_class methodprivate String callMethod(TargetClass<? extends CharSequence>... targets) {// Varargs + new ClassName().method() in array initializationObject[] results = new Object[targets.length];for (int i = 0; i < targets.length; i++) {SourceClass<? extends CharSequence> source = new SourceClass<>();results[i] = source.methodToMove(targets[i]).getInnerField();}return String.join(",", (CharSequence[]) results);}}