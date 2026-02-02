package test;
import java.sql.SQLException;
/**
Target class with javadoc and member inner class*/class TargetClass {int field = 1;
/**
Member inner class of TargetClass
*/
class Inner {
void processField() {
field++;
}
}
}
abstract class ParentClass {public abstract Object compute(TargetClass target) throws SQLException;}
abstract class SourceClass extends ParentClass {class MemberInner {}
/**
Overrides parent class method to process TargetClass and its inner class
@param target the TargetClass parameter
@return processed Object result
@throws SQLException if database operation fails*/@Overridestrictfp public Object compute(TargetClass target) throws SQLException {class LocalInner {Object handleTarget() {TargetClass.Inner targetInner = target.new Inner();targetInner.processField();return target.field;}}
new MemberInner();Object result = new LocalInner().handleTarget();result = OthersClass.staticMethod(this, target.field);
return result;}
private Object helperMethod(int num) {return num * 2;}}
class OthersClass {static Object staticMethod(SourceClass source, int value) {return source.helperMethod(value);}}