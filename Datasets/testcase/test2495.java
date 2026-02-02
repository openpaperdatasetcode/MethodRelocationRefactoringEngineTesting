package test.refactoring.movemethod;
import java.util.function.Consumer;
class BaseClass<T> {protected T baseValue;
public BaseClass(T baseValue) {this.baseValue = baseValue;}}
class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public static class TargetStaticNested {
private U nestedValue;
public TargetStaticNested(U nestedValue) {this.nestedValue = nestedValue;}
private void process(U input, Consumer consumer) {
consumer.accept((U) (nestedValue + "_" + input));
}
}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
strictfp class SourceClass<T extends CharSequence> extends BaseClass<T> {private String outerPrivate = "outer_private_data";
// Member inner classpublic class SourceInner {public void log(T value) {System.out.println("Inner log: " + value);}}
// Static nested classpublic static class SourceStaticNested {
public static <V> void transform(V input) {
System.out.println("Transformed: " + input);
}
}
public SourceClass(T baseValue) {super(baseValue);}
// Varargs methodpublic void process(TargetClass... targets) {if (targets == null || targets.length == 0) {return;}
// Variable callObject varCall = targets[0].getData();
// Access outer privateString privateData = outerPrivate;
// Override violation (raw type in generic context)class BadConsumer implements Consumer {@Overridepublic void accept(Object o) {System.out.println("Consumed: " + o);}}
// Functional interface with target generic method callTargetClass.TargetStaticNested<String> staticNested = new TargetClass.TargetStaticNested<>("base");Consumer<String> stringConsumer = s -> System.out.println("Processed: " + s);
// Loop through varargsfor (TargetClass target : targets) {// Call target's private generic method using instance referencestaticNested.process(target.getData() + "_" + privateData, stringConsumer);
// Break statementif (target.getData().length() > 10) {break;}
// Modify target fieldtarget.setData(target.getData() + "_processed");}}}
import org.junit.Test;
public class MoveMethodTest3199 {@Testpublic void testVarargsMethod() {SourceClass<String> source = new SourceClass<>("source_base");TargetClass target1 = new TargetClass("short");TargetClass target2 = new TargetClass("longer_than_10");
source.process(target1, target2);}}