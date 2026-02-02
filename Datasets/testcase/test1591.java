package test;
import java.sql.SQLException;import java.util.List;
abstract class ParentClass {abstract int process(TargetClass target) throws SQLException;}
strictfp class SourceClass extends ParentClass {protected String outerProtected = "protected_data";
public static class StaticNested {public void log(String message) {System.out.println(message);}}
public class MemberInner {public String getInfo() {return outerProtected;}}
@Overrideint process(TargetClass target) throws SQLException {// Access outer protected fieldtarget.inner.setValue(outerProtected);
// Access instance method of targetList<String> dataList = target.getDataList();
// Enhanced for statementint count = 0;for (String data : dataList) {// Variable call - access target's inner class fieldif (data.equals(target.inner.value)) {count++;}}
// While statementint index = 0;while (index < dataList.size()) {StaticNested.log(dataList.get(index));index++;}
// Throw SQLException if needed (no new exception type)if (count == 0) {throw new SQLException("No matching data found");}
return count;}}
class TargetClass {public MemberInner inner = new MemberInner();
public class MemberInner {public String value;
public void setValue(String val) {this.value = val;}}
public List<String> getDataList() {return List.of("sample", "protected_data", "test");}}