package test;
import java.sql.SQLException;import java.lang.reflect.Method;
public enum SourceClass {INSTANCE;
protected String outerProtected = "protected_value";
class MemberInner {void accessTarget(Target target) {target.innerField = outerProtected;}}
Object method(Target target) throws SQLException {if (target == null) {return null;}
// Local inner classclass LocalProcessor {void process() {target.count++;}}new LocalProcessor().process();
// Expression statement (variable call)target.field = "processed";
// Continue statement in loopfor (int i = 0; i < 5; i++) {if (i == 2) {continue;}target.count += i;}
// Access outer protected field in target interactionnew MemberInner().accessTarget(target);
// Reflection usagetry {Method method = Target.Inner.class.getMethod("update", String.class);method.invoke(target.new Inner(), outerProtected);} catch (Exception e) {throw new SQLException("Reflection error", e);}
return target.field;}}
private enum Target implements SomeInterface {VALUE1, VALUE2;
String field;int count;String innerField;
class Inner {void update(String value) {field = value;}}}
interface SomeInterface {}