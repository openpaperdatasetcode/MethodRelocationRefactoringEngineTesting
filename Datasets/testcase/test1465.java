package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.sql.SQLException;
// Source class: record, default modifier, same package, no extra featuresrecord SourceRecord(String sourceField) {// Source contains target's field (per_condition)private final TargetRecord targetField = new TargetRecord("target_value");
/**
Method Javadoc (feature: method javadoc)
Overloading method 1 - method type parameter is:none
@return List<String> result
@throws SQLException required feature*/public final List<String> moveTargetMethod() throws SQLException {List<String> result = new ArrayList<>();String var = sourceField; // variable call
// Expression statementresult.add(var);result.add(targetField.value()); // variable call (target record component)
// SQLException (feature)if (result.isEmpty()) {throw new SQLException("Result list is empty");}
// No new checked exception (only declares SQLException as required)return result;}
/**
Method Javadoc (feature: method javadoc)
Overloading method 2 - method type parameter is:none (overloading feature)
@param param input parameter
@return List<String> result
@throws SQLException required feature*/public final List<String> moveTargetMethod(String param) throws SQLException {List<String> result = new ArrayList<>();String var = sourceField + param; // variable call
// Expression statementresult.add(var);result.add(targetField.targetInnerRecClass.new TargetInnerRecClass().innerField);
// SQLException (feature)if (param == null) {throw new SQLException("Parameter cannot be null");}
// No new checked exceptionreturn result;}}
// Target class: record, public, has member inner class (target_feature)public record TargetRecord(String value) {// Member inner class (target_inner_rec: method's target position)public class TargetInnerRecClass {public String innerField = "target_inner_rec_field";}
// Target inner rec class instancepublic final TargetInnerRecClass targetInnerRecClass = new TargetInnerRecClass();}