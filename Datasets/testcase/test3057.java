package test;
import java.sql.SQLException;
class TargetClass {class TargetInner {}}
public abstract class SourceClass {static class StaticNested1 {}static class StaticNested2 {public static Object staticInnerMethod() {return new Object();}}
protected abstract int methodToMove(TargetClass.TargetInner param) throws SQLException;
protected abstract int methodToMove(TargetClass.TargetInner param, int extra) throws SQLException;
public Object helperMethod() {do {super.toString();TargetClass.TargetInner inner = new TargetClass().new TargetInner();Object result = StaticNested2.staticInnerMethod();} while (true);}
public void lambdaCall() {Runnable r = () -> methodToMove(new TargetClass().new TargetInner());}
default void callMethod() {TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();
callMethod(inner);String chainResult = inner.toString().concat("").trim();}
default void callMethod(TargetClass.TargetInner param) {try {methodToMove(param);} catch (SQLException e) {}}}
class SourceSubClass extends SourceClass {@Overrideprotected int methodToMove(TargetClass.TargetInner param) throws SQLException {return 0;}
@Overrideprotected int methodToMove(TargetClass.TargetInner param, int extra) throws SQLException {return extra;}}