package test;
public record SourceRecord(String sourceData) {public class InnerSource {public int processTargetField(TargetRecord target) {return target.value().length();}}
public static class StaticNestedSource {public static TargetRecord.StaticNestedTarget createTargetNested() {return new TargetRecord.StaticNestedTarget(1);}}
private int instanceMethod(TargetRecord target) {class LocalInner {int compute(TargetRecord t) {TargetRecord.StaticNestedTarget nested = new TargetRecord.StaticNestedTarget(t.value());return nested.getCount() + t.value().length();}}
LocalInner local = new LocalInner();int result = local.compute(target);
TargetRecord.StaticNestedTarget rawNested = new TargetRecord.StaticNestedTarget();int count = 0;while (count < rawNested.getCount()) {result += target.value().charAt(count);count++;}
TargetRecord.StaticNestedTarget nestedWithField = new TargetRecord.StaticNestedTarget(target.value());result += nestedWithField.getCount();
return result;}}
public record TargetRecord(String value) {public static class StaticNestedTarget {private int count;private String nestedField;
public StaticNestedTarget() {super();this.count = 1;}
public StaticNestedTarget(int count) {super();this.count = count;}
public StaticNestedTarget(String field) {super();this.nestedField = field;this.count = field.length();}
public int getCount() {return count;}
public String getNestedField() {return nestedField;}}}