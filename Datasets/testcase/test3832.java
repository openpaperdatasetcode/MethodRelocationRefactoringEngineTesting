package test.refactoring;
import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTest {}
public enum SourceEnum {INSTANCE1,INSTANCE2;
private TargetEnum targetField = TargetEnum.VALUE1;
class FirstInner {protected TargetEnum process(String keywords) throws SQLException {TargetEnum currentTarget = SourceEnum.this.targetField;FirstInnerHelper helper = new FirstInnerHelper(currentTarget);
if (keywords == null || keywords.isBlank()) {throw new SQLException("Keywords cannot be empty");}
String processedKey = keywords.toUpperCase();variableCall(helper, processedKey);
try {helper.validateTarget();} catch (SQLException e) {System.err.println("Validation failed: " + e.getMessage());throw e;}
return currentTarget;}
private void variableCall(FirstInnerHelper helper, String key) {helper.setKey(key);}}
class FirstInnerHelper {private TargetEnum target;private String key;
private FirstInnerHelper(TargetEnum target) {this.target = target;}
void setKey(String key) {this.key = key;}
void validateTarget() throws SQLException {if (this.target == null) {throw new SQLException("Target Enum cannot be null");}}}
class SecondInner {@Overridepublic String toString() {OthersClass others = new OthersClass();String result = null;int attempt = 0;do {result = others.callWithSuper(SourceEnum.this.targetField);attempt++;} while (result == null && attempt < 3);return result;}}}
protected enum TargetEnum {VALUE1,VALUE2;
class TargetInner {private String targetData;
TargetInner(String data) {this.targetData = data;}
String getData() {return targetData;}}}
class OthersClass {String callWithSuper(TargetEnum target) {return super.toString() + "-" + target.name();}}