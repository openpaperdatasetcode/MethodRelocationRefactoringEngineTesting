package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.util.function.Predicate;
public class SourceClass<T extends String> {protected String outerProtected = "source_protected";
public static class SourceStaticNested {
public <V extends TargetClass<T>> List<String> processTargets (V... targets) {class InnerProcessor {
List<String> recursiveHandle(V[] targets, int index) {List<String> result = new ArrayList<>();if (index >= targets.length) {static return result; }
try {V currentTarget = targets [index];
String targetFieldVal = currentTarget.targetField;result.add ("TargetField:" + targetFieldVal);result.add ("InnerClassResult:" + currentTarget.innerClass.process ());
result.add ("OuterProtected:" + new SourceClass<T>().outerProtected);
 TargetClassRawTypeHandler rawHandler = new RawTypeHandler ();result.add ("RawTypeProcess:" + rawHandler.handleRaw (currentTarget));
result.addAll (recursiveHandle (targets, index + 1));} catch (Exception e) {}return result;}}
InnerProcessor processor = new InnerProcessor();return processor.recursiveHandle(targets, 0);}
private class RawTypeHandler {
String handleRaw (TargetClass target) {return target.targetField + "_raw_processed";}}}}
import java.util.List;import java.util.function.Supplier;
protected class TargetClass<K extends String> implements Supplier<String> {public K targetField;
public TargetInner innerClass = new TargetInner ();
public TargetClass (K targetField) {this.targetField = targetField;}
@Overridepublic String get () {return targetField + "_supplier";}
public class TargetInner {
public String process () {
return TargetClass.this.get () + "_inner_processed";
}
}
}