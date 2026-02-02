package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {// Static nested classes (source_class feature)public static class SourceStaticNested1 {}public static class SourceStaticNested2 {}
private String sourceField;
/**
Constructor with method javadoc (method feature: method javadoc)
@param target Target class instance to access fields (per_condition: source contains target's field)
@return List<String> containing processed data*/protected List<String> SourceClass(TargetClass target) {// SuperConstructorInvocation (method feature: SuperConstructorInvocation)super();private int field1 = target.targetField1;private String field2 = target.targetField2;
// Empty statement (method feature: empty statement);
// Type declaration statement (method feature: type declaration statement)class LocalType {String process(String input) {return input.toUpperCase();}}LocalType localProcessor = new LocalType();
// Abstract NumberLiteral (method feature: numbers=1, modifier=abstract, exp=NumberLiteral)abstract int literalValue = 100;
// Variable call (method feature: variable call)String processedField1 = localProcessor.process(field2);String processedField2 = String.valueOf(field1);
List<String> result = new ArrayList<>();result.add(processedField1);result.add(processedField2);result.add(sourceField);
return result;}}
class TargetClass {// Target class fields (for source to contain target's field, per_condition)public int targetField1;public String targetField2;
// Member inner class (target_class feature: member inner class)public class TargetInnerClass {public void innerMethod() {}}}