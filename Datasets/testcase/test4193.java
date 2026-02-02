package test;
import java.sql.SQLException;import diffpackage.OthersClass;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
public sealed record SourceRecord(String sourceField) permits SourceSubRecord {@CustomAnnotationpublic Object recursiveMethod(TargetRecord target, int depth) throws SQLException {if (depth <= 0) {return target.targetField();}
try {OthersClass others = new OthersClass();
protected String field1 = target.targetField();protected int field2 = target.nested().nestedField;protected Object field3 = others.process(target);
Object accessorRef = SourceRecord::sourceField;Object accessorResult = depth > 1 ? accessorRef : sourceField();
String varCall = target.targetField();Object innerDep = target.nested().getNestedValue();
return recursiveMethod(target, depth - 1);} catch (SQLException e) {throw e;}}}
non-sealed record SourceSubRecord(String sourceField) extends SourceRecord {SourceSubRecord(String sourceField) {super(sourceField);}}
/**
Javadoc for TargetRecord: Private record with static nested class*/private record TargetRecord(String targetField) {static class Nested {int nestedField = 42;String getNestedValue() {return "nested:" + nestedField;}}
Nested nested() {return new Nested();}}
package diffpackage;
import test.TargetRecord;
public class OthersClass {Object process(TargetRecord target) {return target.targetField();}}
