package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
class SuperTarget {protected String superField1 = "base1";protected int superField2 = 100;protected boolean superField3 = false;
protected static Object baseStaticMethod(String data) {return data;}}
private abstract class Target<T> extends SuperTarget {abstract U process(T input);
void handleData(List<T> data) {class LocalHandler<T> {T transform(T item) {return item;}}}}
class OtherClass extends SuperTarget {@Overrideprotected static String baseStaticMethod(String data) { // Override violation (return type)return data.toUpperCase();}}
protected abstract class Source extends SuperTarget {private String outerPrivate = "private_value";
static class StaticNested {static <T> T convert(T input) {return input;}}
/**
Processes target inner components
@return list of processed strings*/public List<String> process() {super(); // Super constructor invocationList<String> result = new ArrayList<>();
// Type declaration statementTarget<String> target = new Target<>() {@OverrideU process(String input) {
return (U) input;
}
};
// ConstructorInvocation with 3 super fieldstry {Constructor<?> constructor = Target.class.getConstructor();Target<String> volatileTarget = (Target<String>) constructor.newInstance();volatileTarget.superField1 = superField1;volatileTarget.superField2 = superField2;volatileTarget.superField3 = superField3;} catch (Exception e) {// Handled in requires_try_catch}
// Empty statement;
// Access outer private fieldresult.add(outerPrivate);
// Used by reflectiontry {Method method = Target.class.getMethod("process", Object.class);method.invoke(target, "reflection_data");} catch (Exception e) {// Requires try-catch}
// Lambda expressions with static method calls (2)Runnable task1 = () -> {Object res1 = super.baseStaticMethod("lambda1");result.add(res1.toString());};Runnable task2 = () -> {Object res2 = OtherClass.baseStaticMethod("lambda2");result.add(res2.toString());};task1.run();task2.run();
return result;}}