import java.util.ArrayList;import java.util.List;
// Parent class for TargetEnum (target_class: extends)class TargetParent {protected String superField = "parent_data";}
// Parent interface for SourceEnum (source_class: implements)interface DataProcessor {List<String> process(String input);}
// TargetEnum: enum, protected, with type parameter, extends TargetParent, local inner class (meets target_class specs)protected enum TargetEnum<T extends CharSequence> extends TargetParent {VALUE1, VALUE2;
// Target inner class for recursion (target_inner_rec)public class TargetInner {private T data;
public TargetInner(T data) {this.data = data;}
public T getData() {return data;}
public void setData(T data) {this.data = data;}
// Recursive methodpublic List<String> recursiveProcess(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}result.add(data.toString());result.addAll(recursiveProcess(depth - 1));return result;}}
// Local inner class (target_feature)public List<String> useLocalInner(TargetInner inner) {class LocalHandler {List<String> handle() {return inner.recursiveProcess(2);}}return new LocalHandler().handle();}}
// SourceEnum: enum, non-sealed, extends Enum, implements DataProcessor, with member + anonymous inner class (meets source_class specs)non-sealed enum SourceEnum implements DataProcessor {INSTANCE;
// Source contains target field (per_condition)private TargetEnum<String> sourceTargetField;
// Member inner class (source_feature)public class SourceInner {// Varargs method: meets method specs (varargs, List<String> return, public, source_inner)public List<String> processTargets(TargetEnum.TargetInner<String>... inners) {List<String> result = new ArrayList<>();if (inners == null) {return result;}
// Expression statement (method_feature)sourceTargetField = TargetEnum.VALUE1;
loop:for (TargetEnum.TargetInner<String> inner : inners) {// BreakStatement: private modifier effect, pos=diff_package_others, access super.field (1)if (inner.getData().contains(sourceTargetField.superField)) {break loop;}
// Variable call (method_feature)inner.setData(inner.getData() + "_processed");
// Access instance method (method_feature)result.addAll(sourceTargetField.useLocalInner(inner));result.addAll(inner.recursiveProcess(1));}
return result; // no_new_exception}}
// Anonymous inner class (source_feature)private final Runnable anonymousProcessor = new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetEnum<String> target = TargetEnum.VALUE2;TargetEnum.TargetInner<String> targetInner = target.new TargetInner("test_data");List<String> output = inner.processTargets(targetInner);System.out.println(output);}};
@Overridepublic List<String> process(String input) {SourceInner inner = new SourceInner();TargetEnum.TargetInner<String> innerObj = TargetEnum.VALUE1.new TargetInner(input);return inner.processTargets(innerObj);}
public void start() {anonymousProcessor.run();}}
// Test classpublic class MoveMethodTest {public static void main(String[] args) {SourceEnum.INSTANCE.start();List<String> result = SourceEnum.INSTANCE.process("input");assert !result.isEmpty() : "Processing failed";}}