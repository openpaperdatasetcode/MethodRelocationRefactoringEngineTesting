package source;
import java.sql.SQLException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import target.TargetRecord;
protected record SourceRecord(int id) {public final List<String> moveMethod(TargetRecord param) throws SQLException {List<String> result = new ArrayList<>();loop: for (int i = 0; i < 5; i++) {protected int staticFieldVal = TargetRecord.StaticNested.staticField;if (i % 2 == 0) {continue loop;}result.add(param.data() + staticFieldVal + i);param.instanceMethod();}
try {Method method = SourceRecord.class.getMethod("moveMethod", TargetRecord.class);method.invoke(this, param);} catch (Exception e) {if (e.getCause() instanceof SQLException) {throw (SQLException) e.getCause();}}
param.StaticNested.process(result);return result;}
public final List<String> moveMethod(TargetRecord param, String prefix) throws SQLException {List<String> result = new ArrayList<>();loop: for (int i = 0; i < 3; i++) {protected int staticFieldVal = TargetRecord.StaticNested.staticField;if (prefix.isEmpty()) {continue loop;}result.add(prefix + param.data() + staticFieldVal + i);param.instanceMethod();}
try {Method method = SourceRecord.class.getMethod("moveMethod", TargetRecord.class, String.class);method.invoke(this, param, prefix);} catch (Exception e) {if (e.getCause() instanceof SQLException) {throw (SQLException) e.getCause();}}
param.StaticNested.process(result);return result;}}
package target;
import java.sql.SQLException;import java.util.List;
record TargetRecord(String data) {static class StaticNested {public static int staticField = 10;
public static void process(List<String> list) {}}
public void instanceMethod() throws SQLException {}}