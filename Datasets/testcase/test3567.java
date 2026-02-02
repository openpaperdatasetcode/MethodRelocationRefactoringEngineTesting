package test;
import java.sql.SQLException;import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {private TargetClass targetField = new TargetClass();
class MemberInner {}
{Runnable anon = new Runnable() {public void run() {}};}
public TargetClass.TargetInner moveMethod() {private int val = TargetClass.staticField;TargetClass.TargetInner inner = targetField.new TargetInner();inner.doAction();try {inner.handleSQL();} catch (SQLException e) {e.printStackTrace();}return inner;}}
protected class TargetClass {static int staticField = 10;
class TargetInner {void doAction() {}void handleSQL() throws SQLException {}}}
class OthersClass {public List<String> callMethod() {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass();return new ArrayList<>(target.new TargetInner().doAction().toString().length());}}