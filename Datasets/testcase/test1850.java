package test;
import java.sql.SQLException;
abstract class SourceClass {// Static nested classpublic static class SourceStaticNested {}
public record SourceInnerRec(int id) {protected int normalMethod(TargetClass target) throws SQLException {// Local inner classclass LocalProcessor {int process() {return target.field + id;}}int result = new LocalProcessor().process();
// Constructor invocationTargetClass.StaticNested nested = new TargetClass.StaticNested(target.field);
// Synchronized statementsynchronized (this) {result += nested.calculate();}
// Variable callresult += target.getIncrement();
// Access instance methodresult += target.compute(result);
// Assert statementassert result > 0 : "Result must be positive";
// SQLExceptionif (target.field < 0) {throw new SQLException("Negative field value");}
return result;}}}
strictfp class TargetClass {int field;
public TargetClass(int field) {this.field = field;}
// Static nested classpublic static class StaticNested {private int value;
public StaticNested(int value) {this.value = value;}
public int calculate() {return value * 2;}}
public int getIncrement() {return 5;}
public int compute(int num) {return num / 2;}}