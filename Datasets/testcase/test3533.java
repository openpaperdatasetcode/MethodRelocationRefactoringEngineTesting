package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;
protected class SourceClass extends ParentClass {public static class StaticNested {public static String getPrefix() {return "prefix_";}}
/**
Overriding method to process TargetClass instances
Collects field values and handles continue statements
@param target The TargetClass instance to process
@return List of processed strings*/@Overrideprivate List<String> process(TargetClass target) {super(); // Super constructor invocationList<String> results = new ArrayList<>();TargetClass.MemberInner inner = target.new MemberInner();
class LocalProcessor {void handleFields(TargetClass t) {for (int i = 0; i < 5; i++) {// ContinueStatement with obj.field = 3if (t.countField == 3) {continue;}results.add(StaticNested.getPrefix() + t.countField);}}}
LocalProcessor processor = new LocalProcessor();processor.handleFields(target);
// Variable callresults.add(inner.getInnerData());
return results;}}
abstract class ParentClass {public abstract List<String> process(TargetClass target);}
package target;
public class TargetClass {public int countField;
public TargetClass(int count) {this.countField = count;}
public class MemberInner {private String innerData = "inner_data";
public String getInnerData() {return innerData;}}}