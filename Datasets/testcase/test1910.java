package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
abstract class SourceClass {private TargetClass target = new TargetClass();
List<String> method() throws SQLException {target.field = new ArrayList<>();String str = "test";str.length();
class LocalInner {void useTarget() {target.memberInner.method();}}new LocalInner().useTarget();
Runnable r = new Runnable() {public void run() {target.field.add("anonymous");}};r.run();
return target.field;}}
protected abstract class TargetClass {List<String> field;
class MemberInner {void method() {}}MemberInner memberInner = new MemberInner();}