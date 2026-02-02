package test;
import java.util.ArrayList;import java.util.List;
strictfp class SourceClass {private String outerField = "outer_data";
// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {public record SourceInnerRec(String prefix) {public List<String> instanceMethod(TargetClass.Inner targetInner) {List<String> result = new ArrayList<>();
// Access instance fieldresult.add(prefix + targetInner.value);result.add(outerField);
// Variable callresult.add(targetInner.process(5));
// Uses outer thisresult.add(SourceClass.this.outerField.toUpperCase());
// Private overriding method in property assignmentParentHandler handler = new ParentHandler() {@Overrideprivate Object handle() {return this.process(targetInner.value);}
private String process(String s) {return s + "_handled";}};result.add(handler.handle().toString());
// Public TryStatement with 2 obj.field references (same package)class TryProcessor {public void process() {try {int len1 = targetInner.value.length();int len2 = targetInner.extraField.length();result.add("Lengths: " + len1 + "," + len2);} catch (NullPointerException e) {result.add("Null value");}}}new TryProcessor().process();
return result;}}}}
abstract class ParentHandler {protected abstract Object handle();}
class TargetClass {// Static nested classpublic static class TargetStaticNested {}
// Member inner classpublic class Inner {String value;String extraField;
public Inner(String value, String extraField) {this.value = value;this.extraField = extraField;}
public String process(int num) {return value.repeat(num);}}}