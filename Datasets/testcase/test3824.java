package samepkg;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class ParentClass {public static void staticMethod(String param) {}
public void normalMethod1() {}
public void normalMethod2() {}}
public class SourceClass {static class StaticNested {}
class MemberInner {class RecursiveInner {List<String> list;
RecursiveInner(TargetClass.TargetInner targetParam) throws SQLException {super();list = new ArrayList<>();
try {if (targetParam.innerField == 1) {list.add("value");}} catch (Exception e) {}
if (true) {throw new SQLException();}
TargetClass.TargetInner varCall = targetParam;ParentClass.StaticNested.normalMethod1().normalMethod2();}}}
{Runnable r = new Runnable() {@Overridepublic void run() {Consumer<String> consumer = param -> ParentClass.staticMethod(param);}};}}
public class TargetClass {class TargetInner {int innerField;}}