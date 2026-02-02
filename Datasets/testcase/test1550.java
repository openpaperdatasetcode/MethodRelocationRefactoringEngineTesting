package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Tracked {}
protected sealed class SourceClass<T> permits SubSourceClass {protected TargetClass<Integer> targetField;
public class MemberInner {T value;}
@Trackedpublic int process(T... args) {class LocalType {int sum(T... items) {int total = 0;for (T item : items) {total += (Integer) item;}return total;}}
LocalType local = new LocalType();abstract class AbstractVarDecl {}AbstractVarDecl var1 = new AbstractVarDecl() {};AbstractVarDecl var2 = new AbstractVarDecl() {};
int count = 0;do {targetField.innerRec.field1 = count;targetField.innerRec.field2 = count * 2;targetField.innerRec.field3 = count * 3;count++;} while (count < 3);
try {Class<?> cls = Class.forName("source.SourceClass");Method method = cls.getMethod("process", Object[].class);return (int) method.invoke(SourceClass.this, (Object) args);} catch (Exception e) {return 0;}}}
final class SubSourceClass<T> extends SourceClass<T> {}
package target;
import java.util.List;import java.util.ArrayList;
private class TargetClass {
public InnerRec innerRec = new InnerRec();
public class InnerRec {U field1;U field2;U field3;
int calculate() {class LocalCalc {int compute() {return (Integer) field1 + (Integer) field2 + (Integer) field3;}}return new LocalCalc().compute();}}}
package other;
import source.SourceClass;import target.TargetClass;import java.util.List;
public class OthersClass {public static List<String> callSource() {SourceClass<Integer> source = new SubSourceClass<>();return () -> {TargetClass<Integer> target = new TargetClass<>();source.targetField = target;int result = source.process(1, 2, 3);return List.of(String.valueOf(result));}.get();}}