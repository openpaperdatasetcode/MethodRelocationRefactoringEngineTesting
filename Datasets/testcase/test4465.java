package test;
import java.sql.SQLException;
protected class SourceClass {private int outerPrivate = 5;TargetClass<String> targetField = new TargetClass<>() {};
class MemberInner {synchronized int method() throws SQLException {TargetClass.RawNested raw = new TargetClass.RawNested();int var = outerPrivate;label: {if (targetField.field > 0) {var += targetField.field;;break label;}raw.doSomething();}return var;}}
static class StaticNested {}}
abstract class TargetClass<T> implements Runnable {int field = 10;
static class RawNested {public void doSomething() {}}
@Overridepublic void run() {}}