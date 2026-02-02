package test;
import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface AnnWithAccessor {String value();Class<?> methodRef() default Object.class;}
abstract class SourceClass<T> {public class InnerFirst {public class InnerRec {@AnnWithAccessor(value = "test", methodRef = SourceClass.class)final Object process(TargetClass<String> targetParam, Object... args) {Object[] arr = {targetParam.getNested().getSubNested().getData(), 1, "a"};SourceClass.staticHelper(arr[0], arr[1], arr[2]);
if (args.length > 0 && args[0] instanceof String && args[1] instanceof Integer) {targetParam.nestedStatic.setValue((String) args[0]);}
TargetClass rawTarget = new TargetClass();new Runnable() {@Overridepublic void run() {rawTarget.nestedStatic.setValue("anonymous1");}}.run();
new Runnable() {@Overridepublic void run() {rawTarget.nestedStatic.setValue("anonymous2");}}.run();
try {Class<?> cls = Class.forName("test.SourceClass
InnerFirst
InnerRec");Method method = cls.getMethod("process", TargetClass.class, Object[].class);return method.invoke(this, targetParam, new Object[]{1, "test"});} catch (Exception e) {return null;}}}}
protected static Object staticHelper(Object o1, Object o2, Object o3) {return o1.toString() + o2 + o3;}
protected Object callMethod(TargetClass<String> target) {InnerFirst.InnerRec innerRec = new SourceClass<>().new InnerFirst().new InnerRec();return innerRec::process;}}
/**
Generic target class with static nested class
@param type parameter
*/
public class TargetClass {
public NestedStatic nestedStatic = new NestedStatic();
public static class NestedStatic {private U value;
public SubNested getSubNested() {return new SubNested();}
public void setValue(U value) {this.value = value;}
public U getValue() {return value;}}
public static class SubNested {public String getData() {return "subNestedData";}}
public NestedStatic getNested() {return nestedStatic;}}