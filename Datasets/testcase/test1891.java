package test;
import java.util.ArrayList;import java.util.List;
@MyAnnotationpublic record SourceRecord(String data) {
private Object instanceMethod(ProtectedTargetRecord target) {List<Object> results = new ArrayList<>();
// VariableDeclarationStatement with 3 this.field referencespublic String field1 = this.data;public int field2 = this.data.length();public boolean field3 = this.data.isEmpty();results.add(field1 + "|" + field2 + "|" + field3);
// Variable callresults.add(target.value());results.add(target.nested().count());
// For statementfor (int i = 0; i < 3; i++) {results.add("loop:" + i);}
// Labeled statementouter:for (int i = 0; i < 2; i++) {for (int j = 0; j < 2; j++) {if (i == 1 && j == 1) break outer;results.add("i=" + i + ",j=" + j);}}
// Instance code blocks with 2 overriding source methods (super call)class OverrideHandler extends SourceOverride {@Overridepublic int process(ProtectedTargetRecord target) {return super.process(target) + target.value().length();}
@Overridepublic String format(String input) {return super.format(input) + "_override";}}OverrideHandler handler = new OverrideHandler();results.add(handler.process(target));results.add(handler.format(target.value()));
return results;}
static class SourceOverride {public int process(ProtectedTargetRecord target) {return target.nested().count();}
public String format(String input) {return input.toUpperCase();}}}
@interface MyAnnotation {}
protected record ProtectedTargetRecord(String value, Nested nested) {// Static nested classpublic static class Nested {private final int count;
public Nested(int count) {this.count = count;}
public int count() {return count;}}}