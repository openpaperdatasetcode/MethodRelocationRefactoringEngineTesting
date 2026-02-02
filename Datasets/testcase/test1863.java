package test;
import java.sql.sql.SQLException;
private sealed class SourceClass permits SourceSubClass {// Member inner classprotected class SourceMemberInner {int getValue() {return 5;}}
protected Object instanceMethod(TargetClass target) throws SQLException {// Local inner classclass LocalProcessor {Object process() {return target.field + new SourceMemberInner().getValue();}}Object result = new LocalProcessor().process();
// 3 abstract SuperFieldAccessabstract class SuperAccess1 {int access() {return target.superField1;}}abstract class SuperAccess2 {int access() {return target.superField2;}}abstract class SuperAccess3 {int access() {return target.superField3;}}result = (Integer) result + new SuperAccess1() {}.access() + new SuperAccess2() {}.access() + new SuperAccess3() {}.access();
// Variable callresult = result + "_" + target.getField();
// Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}result = result + "_" + i;}
// SQLExceptionif (target.field < 0) {throw new SQLException("Negative field value");}
return result;}}
final class SourceSubClass extends SourceClass {}
class TargetClass extends TargetParent {int field = 10;
{// Local inner classclass FieldValidator {boolean isValid() {return field > 0;}}new FieldValidator().isValid();}
String getField() {return String.valueOf(field);}}
class TargetParent {protected int superField1 = 1;protected int superField2 = 2;protected int superField3 = 3;}