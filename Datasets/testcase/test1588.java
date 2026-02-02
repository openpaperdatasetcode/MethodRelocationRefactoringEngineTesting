package test;
import java.sql.SQLException;
abstract class SourceClass {public static class StaticNested {public String process(String data) {return "processed: " + data;}}
protected <T> Object process(TargetClass<T> target) throws SQLException {// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {// Variable call - access target's inner class fieldSystem.out.println("Inner data: " + target.inner.data);}};
// Expression statementtarget.inner.data = (T) "initial_data";
// Method parameter is Target classtarget.processInner(target.inner);
// Check for SQL exception conditionif (target.inner.data == null) {throw new SQLException("Inner data cannot be null");}
task.run();return target.inner.data;}}
protected class TargetClass<T> {public Inner<T> inner = new Inner<>();
public class Inner<T> {public T data;}
public void processInner(Inner<T> inner) {// Local inner class in targetclass LocalProcessor {void modify(Inner<T> in) {in.data = (T) (in.data + "_modified");}}new LocalProcessor().modify(inner);}}