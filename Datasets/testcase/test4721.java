package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.sql.SQLException;
@Retention(RetentionPolicy.RUNTIME)@interface UseParentMethod {String value() default new SourceClass().getParentData("anno-arg");}
class ParentClass {private String parentField = "parent-data";
public synchronized Object getParentData(String arg) {return parentField + "-" + arg;}
public synchronized Object getParentData(int num) {return parentField + "-" + num;}}
public class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
class SourceMemberInner {void callProcess() throws SQLException {new SourceClass().process("inner-arg");}}
void useLocalInner() throws SQLException {class SourceLocalInner {void execute() throws SQLException {process("local-arg");}}new SourceLocalInner().execute();}
@UseParentMethodprotected void process(String arg) throws SQLException {TargetClass.RawTypeExample rawExample = new TargetClass.RawTypeExample();rawExample.setData("raw-value");
try {Object data1 = this.getParentData(arg);Object data2 = this.getParentData(100);
targetField.targetField = data1.toString();TargetClass.StaticNested.log(targetField.targetField);} catch (SQLException e) {throw e;}}}
protected class TargetClass {String targetField;
static class StaticNested {public static void log(String msg) {System.out.println("Log: " + msg);}}
class RawTypeExample {private Object data;
public void setData(Object data) {this.data = data;}
public Object getData() {return data;}}}