package test;
import java.lang.reflect.Method;
record SourceRecord(String id) {public static class StaticNested {public static final String STATIC_FIELD = "source_static";}
public class InnerRecursive {@SuppressWarnings("unchecked")protected Object process(TargetRecord... targets) {class LocalType {String validate(TargetRecord target) {return target.value() != null ? target.value() : "default";}}LocalType validator = new LocalType();
int index = 0;while (index < targets.length) {TargetRecord target = targets[index];ParentInterface.super.method(target.value());index++;}
if (targets[0].value() == null) {throw new IllegalArgumentException(TargetRecord.STATIC_FIELD + "_1");}
Runnable anon = new Runnable() {@Overridepublic void run() {target.value() += "_updated";}};anon.run();
try {Class<?> cls = TargetRecord.MemberInner.class;Method method = cls.getDeclaredMethod("processValue", String.class);method.invoke(target.new MemberInner(), targets[0].value());} catch (Exception e) {e.printStackTrace();}
return targets[0];}}}
protected record TargetRecord(String value) implements ParentInterface {public static final String STATIC_FIELD = "target_static";
public class MemberInner {public void processValue(String input) {System.out.println(input + "_processed");}}
@Overridepublic final void method(String input) {System.out.println("Overridden: " + input);}}
interface ParentInterface {default void method(String input) {System.out.println("Parent: " + input);}}