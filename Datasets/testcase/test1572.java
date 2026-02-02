package source;
import target.TargetClass;import java.sql.SQLException;
protected class SourceClass<T> {protected TargetClass process(TargetClass target) throws SQLException {// First local inner classclass LocalProcessor<T> {T processData(T input) {return input;}}
// Second local inner classclass RawTypeHandler {TargetClass handleRaw(TargetClass rawTarget) {return rawTarget;}}
// Type declaration statementLocalProcessor<String> stringProcessor = new LocalProcessor<>();RawTypeHandler rawHandler = new RawTypeHandler();
try {// Variable call and overload usagetarget.setValue(stringProcessor.processData("test"));target.setValue(123); // Overloaded method
// Raw type usageTargetClass rawTarget = new TargetClass();rawHandler.handleRaw(rawTarget);
return target;} catch (SQLException e) {// Requires throws declarationthrow e;}}}
package target;
import java.sql.SQLException;
public class TargetClass {private Object value;
// Overloaded methodspublic void setValue(String val) {this.value = val;}
public void setValue(int val) {this.value = val;}
public Object getValue() throws SQLException {// Local inner class in targetclass ValueValidator {boolean isValid() {return value != null;}}
if (!new ValueValidator().isValid()) {throw new SQLException("Value is null");}return value;}}