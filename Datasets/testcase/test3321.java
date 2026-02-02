package source;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import target.TargetClass;
public class SourceClass extends ParentClass {protected String outerProtectedField = "protectedData";
class MemberInner {}public static class StaticNested {}
@Overridepublic strictfp List<String> moveMethod(TargetClass<String> target) {super();static String field1 = TargetClass.STATIC_FIELD1;static String field2 = TargetClass.STATIC_FIELD2;static String field3 = TargetClass.STATIC_FIELD3;
List<String> result = new ArrayList<>();result.add(target.inner.innerRec.process(field1 + SourceClass.this.outerProtectedField));result.add(target.inner.innerRec.calculate(field2));
try {Method method = TargetClass.TargetInner.TargetInnerRec.class.getMethod("process", String.class);method.invoke(target.inner.innerRec, field3);} catch (Exception e) {}
return result;}
public strictfp List<String> moveMethod(TargetClass<Integer> target) {super();static String field1 = TargetClass.STATIC_FIELD1;static String field2 = TargetClass.STATIC_FIELD2;static String field3 = TargetClass.STATIC_FIELD3;
List<String> result = new ArrayList<>();result.add(target.inner.innerRec.process(field1 + SourceClass.this.outerProtectedField));result.add(target.inner.innerRec.calculate(field2));
try {Method method = TargetClass.TargetInner.TargetInnerRec.class.getMethod("process", String.class);method.invoke(target.inner.innerRec, field3);} catch (Exception e) {}
return result;}}
abstract class ParentClass {public ParentClass () {}
public abstract List<Integer> moveMethod(TargetClass<?> target);}
package target;
import java.util.List;
public class TargetClass<T extends CharSequence> extends TargetParent {public static final String STATIC_FIELD1 = "static1";public static final String STATIC_FIELD2 = "static2";public static final String STATIC_FIELD3 = "static3";
TargetInner inner = new TargetInner();
class TargetInner {TargetInnerRec innerRec = new TargetInnerRec();
class TargetInnerRec {public String process(String input) {class LocalInner {}new LocalInner();return input.toUpperCase();}
public String calculate(String input) {class LocalInner {}new LocalInner();return input.toLowerCase();}}}}
abstract class TargetParent {}