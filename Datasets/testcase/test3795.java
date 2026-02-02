import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
// Parent class for SourceEnum (source_class: extends)class EnumParent {protected String parentField = "parent_data";}
// TargetClass: enum, package-private, with local inner class (meets target_class specs)enum TargetEnum {VALUE1, VALUE2;
public String targetField1 = "field1";public String targetField2 = "field2";
public String getCombined() {return targetField1 + "_" + targetField2;}
// Local inner class (target_feature)public List<String> processLocal() {class LocalHandler {List<String> handle() {List<String> res = new ArrayList<>();res.add(VALUE1.getCombined());res.add(VALUE2.getCombined());return res;}}return new LocalHandler().handle();}}
// SourceClass: enum, public, extends EnumParent, with local inner + static nested class (meets source_class specs)public enum SourceEnum extends EnumParent {INSTANCE;
// Static nested class (source_feature)public static class SourceStaticNested {// Inner class for method_position: source_innerpublic class SourceInner {// Overloading method 1List<String> process(TargetEnum target) {return process(target, 1);}
// Overloading method 2 (meets method specs)List<String> process(TargetEnum target, int count) {List<String> result = new ArrayList<>();if (target == null) return result;
// SynchronizedStatement: private modifier effect, pos=same_package_others, access 2 obj.fieldsynchronized (target) {// Expression statementtarget.targetField1 += "_modified";
// Variable callresult.add(target.getCombined());}
// Local inner class (source_feature)class ProcessHelper {void appendData() {for (int i = 0; i < count; i++) {// Variable callresult.add(target.targetField2 + "_" + i);}}}new ProcessHelper().appendData();
// Call method in collection operations (call_method pos)List<String> processed = result.stream().map(str -> new InnerProcessor().processStr(str)).collect(Collectors.toList());
return processed; // no_new_exception}}
// Inner class for call_method (type=inner_class)public class InnerProcessor {// Overriding Object.toString (call_method: overriding)@Overridepublic String toString() {return "Processor";}
// Method for call_method (this.methodName)public String processStr(String s) {return this.modify(s);}
private String modify(String s) {return s.toUpperCase();}}}
public List<String> startProcessing(TargetEnum target) {SourceStaticNested nested = new SourceStaticNested();SourceStaticNested.SourceInner inner = nested.new SourceInner();return inner.process(target); // per_condition: contains target parameter}
public static void main(String[] args) {SourceEnum.INSTANCE.startProcessing(TargetEnum.VALUE1);}}