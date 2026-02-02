package source;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.sql.SQLException;import target.TargetClass;
class SourceClass {class MemberInner {/**
Processes target inner records and returns string list
@param targetParam TargetClass parameter containing inner records
@return List<String> processed results
@throws SQLException if database operation fails*/private List<String> processMethod(TargetClass targetParam) throws SQLException {List<String> result = new ArrayList<>();int i = 0;while (i < 5) {TargetClass.Inner inner = targetParam.new Inner(targetParam.data);result.add(inner.getValue());i++;}
List<? extends CharSequence> boundedList = new ArrayList<>();result.addAll((List<String>) boundedList);
try {Method method = TargetClass.Inner.class.getMethod("getValue");result.add((String) method.invoke(targetParam.new Inner("reflection")));} catch (Exception e) {}
SubClass<String> sub = new SubClass<>();String initVal = new SubClass<Integer>().genericMethod(10);result.add(initVal);
return result;}}
Runnable anonymous = new Runnable() {public void run() {new MemberInner().processMethod(new TargetClass("anon"));}};}
package target;
import java.sql.SQLException;
public class TargetClass {String data;
public TargetClass(String data) {this.data = data;}
class Inner extends SuperClass {protected Inner(String value) {super(value);this.objField = value;}
String getValue() {return data;}}}
package target;
public class SuperClass {protected String objField;
protected SuperClass(String value) {this.objField = value;}}
package source;
import target.TargetClass;
public class SubClass<T> {public String genericMethod(T param) {return param.toString();}}