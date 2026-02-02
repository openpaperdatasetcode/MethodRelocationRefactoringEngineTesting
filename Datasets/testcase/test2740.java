package test.same;
import test.other.OtherClass;import java.util.List;
private class SourceClass<T> extends ParentClass<T> {protected T outerProtectedField;static class StaticNested {}
@Overrideprotected void method(TargetClass<String> target) {target.thisField = 3;Object var = target;
for (String s : target.list) {var = s;}
try {new OtherClass().process(target);} catch (Exception e) {throw new RuntimeException();}
overloadMethod(1);overloadMethod("str");}
private void overloadMethod(int i) {}private void overloadMethod(String s) {}
Runnable anon = new Runnable() {public void run() {}};}
class TargetClass<T> {T thisField;List<T> list;
Runnable anon = new Runnable() {public void run() {}};}
abstract class ParentClass<T> {protected abstract void method(TargetClass<T> target);}
package test.other;
import test.same.TargetClass;
class OtherClass {private void process(TargetClass<String> target) {new test.same.SourceClass<String>().method(target);}}