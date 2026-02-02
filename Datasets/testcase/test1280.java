package test.refactoring.movemethod;
import java.sql.SQLException;
public abstract class SourceClass implements DataProcessor {protected String sourceField = "source_data";
/**
Processes target parameter with required features
@param targetParam Target class instance
@return Processed base type result
@throws SQLException If database operation fails*/public int process(TargetClass targetParam) throws SQLException {class SourceInner {int handle() throws SQLException {labeledBlock: {OtherSamePackageClass other = new OtherSamePackageClass();other.process(SourceClass.this);
if (targetParam.getStaticNested().getField() == null) {break labeledBlock;}
Object rawValue = targetParam.getStaticNested().process(sourceField);int castValue = (int) rawValue;sourceField = sourceField.toUpperCase();}
return sourceField.length();}}
return new SourceInner().handle();}
class MemberInnerSource {void useTarget(TargetClass target) throws SQLException {process(target);}}}
abstract class TargetClass {private StaticNestedTarget staticNested = new StaticNestedTarget();
public StaticNestedTarget getStaticNested() {return staticNested;}
public static class StaticNestedTarget {private String nestedField = "target_nested";
public String getField() {return nestedField;}
public Object process(String input) {return input.length();}}}
class OtherSamePackageClass {public void process(SourceClass source) {System.out.println("Processing source: " + source.sourceField);}}
interface DataProcessor {int process(TargetClass target) throws SQLException;}
abstract class ParentClass {private String parentField;
public Object getParentField() {return parentField;}
public void setParentField(String parentField) {this.parentField = parentField;}
public Object callSuperAccessor() {return super.toString();}}
@interface CustomAnnotation {String value() default "";String parentAccessor() default "#{parentClass.callSuperAccessor()}";}
@CustomAnnotation(parentAccessor = "#{ParentClass.callSuperAccessor()}")class ChildClass extends ParentClass {// Implements parent class accessor usage}
public class MoveMethodTest5187 {public static void main(String[] args) {SourceClass source = new SourceClass() {};TargetClass target = new TargetClass() {};
try {int result = source.process(target);System.out.println("Process result: " + result);
SourceClass.MemberInnerSource inner = source.new MemberInnerSource();inner.useTarget(target);} catch (SQLException e) {e.printStackTrace();}}}