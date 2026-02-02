package samepkg;
import java.lang.reflect.Field;
interface SomeInterface {}
class SourceClass implements SomeInterface {class MemberInner {class RecursiveInner {/**
Method javadoc*/@Deprecatedpublic void instanceMethod() throws Exception {TargetClass<Integer> target = new TargetClass<>();int val = target.targetField;target.targetStaticField = val * 2;System.out.println(val);
Field field = TargetClass.class.getDeclaredField("targetField");Object fieldVal = field.get(target);
accessInstanceField = 10;}
private int accessInstanceField;}}
{Runnable r = new Runnable() {public void run() {}};}}
/**
Javadoc for TargetClass*/private class TargetClass<T> {int targetField;static int targetStaticField;
void methodWithLocal() {class LocalInner {}new LocalInner();}}