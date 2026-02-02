package source;
import target.TargetEnum;import java.lang.reflect.Method;import java.sql.SQLException;
protected enum SourceEnum {INSTANCE;
@Overridepublic String toString() {return name();}
void process(TargetEnum target) throws SQLException {class LocalType {int value;}LocalType local = new LocalType();
private try {local.value = TargetEnum.ST.STATIC_FIELD;target.new InnerRec().handle(local.value);} catch (SQLException e) {throw e;}
try {Class<?> cls = Class.forName("source.SourceEnum");Method method = cls.getMethod("process", TargetEnum.class);method.invoke(this, target);} catch (Exception e) {// used_by_reflection}
target.new InnerRec().compute(1);target.new InnerRec().compute(2L);target.new InnerRec().compute("3");}
{// instance code blockTargetEnum target = TargetEnum.VALUE;target.new InnerRec().compute(0);}}
package target;
import java.sql.SQLException;
public enum TargetEnum {VALUE {@Overridepublic TargetEnum create() {return new TargetEnum() {};}};
public static int STATIC_FIELD = 100;
public class InnerRec {int handle(int num) throws SQLException {return num * 2;}
int compute(int a) { return a; }long compute(long b) { return b; }String compute(String c) { return c; }}
protected abstract TargetEnum create();}
package target;
public class ParentEnum {protected TargetEnum createInstance() {return new TargetEnum() {@Overridepublic TargetEnum create() {return VALUE;}}.create();}}