package test;
import java.lang.reflect.Field;
abstract class AbstractSource<T extends Number> {public abstract int process(TargetClass<T> target);}
final class SourceClass<T extends Number> extends AbstractSource<T> {static class StaticNested {}
// Anonymous inner classprivate final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {process(new TargetClass<>(3));}};
@Overridepublic int process(TargetClass<T> target) {labeledBlock: {int count = 0;// WhileStatement matching target_featurewhile (count < 1) {if (target.this.field.intValue() != 3) break labeledBlock;
access_instance_field(target);variableCall(target);System.out.println(TargetClass.staticField); // Depends on static field
count++;}}
// Used by reflectiontry {Field field = TargetClass.class.getDeclaredField("field");field.setAccessible(true);return ((Number) field.get(target)).intValue();} catch (Exception e) {return 0;}}
private void variableCall(TargetClass<T> target) {target.staticNested.doTask();new StaticNested();}
private void access_instance_field(TargetClass<T> target) {System.out.println(target.field);}}
protected class TargetClass<T extends Number> {public static int staticField = 100;public T field;
public TargetClass(T field) {this.field = field;}
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public int process() {return field.intValue() + staticField;}}