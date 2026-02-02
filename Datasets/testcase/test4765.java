package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
public class SourceClass {protected int outerProtected;public static class StaticNested {}public class MemberInner {}
Object recursiveMethod(TargetClass target, int depth) throws Exception {if (depth <= 0) {return this;}
OtherClass.process(target);
if (target.field1 > target.field2) {}
try {Method method = TargetClass.class.getMethod("getList");method.invoke(target);} catch (Exception e) {throw e;}
outerProtected = target.field1;
MemberInner inner = new MemberInner();inner.toString();
return recursiveMethod(target, depth - 1);}}
package other;
import target.TargetClass;
public class OtherClass {public static void process(TargetClass target) {private class LocalType {int val1 = target.field1;int val2 = target.field2;}}}
package target;
import java.util.List;import java.util.ArrayList;import source.SourceClass;
public class TargetClass {public int field1;public int field2;
public List<String> getList() {class TargetLocalInner {}return new ArrayList<>();}
@MyAnnotation(value = new SourceClass.MemberInner()::toString)public void annotatedMethod() {}}
package target;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyAnnotation {Supplier<String> value();}