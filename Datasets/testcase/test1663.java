package source;
import java.sql.SQLException;import java.lang.reflect.Method;import target.TargetClass;
private class SourceClass {public class MemberInner {void doSomething() {}}
protected void methodToMove(TargetClass target) throws SQLException {TargetClass[] targets = new TargetClass[]{new TargetClass()};new TargetClass().doAction();
try {Method method = TargetClass.class.getMethod("doAction");method.invoke(target);} catch (Exception e) {}}
protected void methodToMove(String str) {}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
package target;
private class TargetClass<T> {public void doAction() {}
public void someMethod() {class LocalInner {void process() {}}}}
Test Case 4322
package source;
import java.util.List;
protected sealed record SourceRecord(String field) permits SubRecord {public void createLocal() {class LocalInner1 {}class LocalInner2 {}}
@Overridepublic void methodToMove() {TargetRecord target = new TargetRecord("test");int result = target.count() + 10;
do {super.toString();} while (result < 20);}}
final class SubRecord extends SourceRecord {public SubRecord(String field) {super(field);}}
package target;
/**
Javadoc for TargetRecord*/public sealed record TargetRecord(String value) {public class MemberInner {public int getValue() {return value.length();}}
public int count() {return value.length();}}
Test Case 4323
package same;
public sealed class SourceClass permits SourceSubClass {public class MemberInner {}public static class StaticNested {}
public class InnerClass {TargetClass methodToMove(TargetClass... targets) {TargetClass result = null;int i = 0;do {result = targets[i];result.staticNested.doSomething();i++;} while (i < targets.length);
try {OtherClass other = new OtherClass();other.process(targets[0]);} catch (Exception e) {}
return result;}}}
class SourceSubClass extends SourceClass {}
class OtherClass {public String process(TargetClass target) {return target.field;}}
public class TargetClass implements SomeInterface {public String field;public static class StaticNested {public void doSomething() {}}}
interface SomeInterface {}