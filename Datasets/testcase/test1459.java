package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
// Source class: record, public, same package, has two static nested classespublic record SourceRecord(String sourceField) {// First static nested class (source feature)public static class FirstStaticNested {}
// Second static nested class (source feature - duplicate as required)public static class SecondStaticNested {}
// Static nested class (source_inner: method's original position)public static class SourceInnerClass {// Target method: instance, List<String>, final, source_inner position// per_condition: contains target parameter (TargetRecord)public final List<String> moveTargetMethod(TargetRecord targetParam) throws IOException { // requires_throwsList<String> result = new ArrayList<>();String var = sourceField; // variable call (source record component)
// Expression statementresult.add(var);result.add(targetParam.targetField()); // variable call (target record component)
// Expression statement (additional to meet feature)targetParam.toString();
return result;}}}
// Target class: record, public, no extra target_featurespublic record TargetRecord(String targetField) {}