package test;
import java.sql.SQLException;
private sealed class SourceClass permits SourceSubClass {protected String outerProtectedField = "protectedData";
public class FirstMemberInner {}public class SecondMemberInner {}
public SourceClass(TargetClass target) throws SQLException {FirstMemberInner inner1 = new FirstMemberInner();SecondMemberInner inner2 = new SecondMemberInner();
int targetField = target.targetIntField;String var = target.targetStrField;
Runnable functional = () -> {int result = inner1.StaticInner.staticMethod(target).m1().m2().m3();};
this.outerProtectedField = var;}
public SourceClass(int num) {}
public class FirstMemberInner {public static class StaticInner {public static MethodChain staticMethod(TargetClass target) {return new MethodChain(target.targetIntField);}}}
public static class MethodChain {private int value;
public MethodChain(int value) {this.value = value;}
public MethodChain m1() { return this; }public MethodChain m2() { return this; }public int m3() { return value; }}}
final class SourceSubClass extends SourceClass {public SourceSubClass(TargetClass target) throws SQLException {super(target);}
public SourceSubClass(int num) {super(num);}}
class TargetClass {int targetIntField;String targetStrField;
{new Runnable() {@Overridepublic void run() {System.out.println(targetIntField);}};}}