package test;
import java.lang.reflect.Field;import java.sql.SQLException;import java.util.List;
abstract class SourceClass {static class StaticNested {}
class MemberInner {}
protected void moveMethod(TargetClass target) throws SQLException {RawType raw = new RawType();TargetClass.Inner inner = target.new Inner();TargetClass.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();
try {if (target.this.field == 1) {Field field = TargetClass.Inner.RecursiveInner.class.getField("data");break label;}} catch (NoSuchFieldException e) {throw new SQLException("Field not found", e);}label: {}
if (recursiveInner == null) {throw new SQLException("Recursive inner class is null");}
recursiveInner.action();}}
public abstract class TargetClass {int field;
class Inner {class RecursiveInner {String data;
void action() {}}}
{new Runnable() {public void run() {}};}}
class RawType<T> {}