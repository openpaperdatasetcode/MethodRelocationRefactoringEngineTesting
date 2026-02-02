package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public record SourceRecord(int id, String name) {public static int staticField = 10;
private TargetRecord createTarget(TargetRecord... targets) {@TestAnnotationprivate String localVar = this.name() + SourceRecord.staticField;TargetRecord.Nested nested = new TargetRecord.Nested();List<String> data = ParentClass.callNestedMethod(nested);data.add(localVar);data.add(String.valueOf(this.id()));return new TargetRecord(data, targets.length);}
static class NestedOne {}
static class NestedTwo {}}
record TargetRecord(List<String> data, int count) {static class Nested {String getValue() {return "nested";}}}
class ParentClass {protected static List<String> callNestedMethod(TargetRecord.Nested nested) {List<String> list = new ArrayList<>();list.add(nested.getValue());return list;}}