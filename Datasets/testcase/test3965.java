package test;
import java.lang.reflect.Method;
interface MyFunctionalInterface<T> {void execute(T t);}
final class SourceClass<T> {private String instanceField = "sourceInstanceField";static class SourceStaticNested {}
final TargetClass<T> recursiveMethod(TargetClass<T> target, int depth) {if (depth < 0) {throw new IllegalArgumentException("Depth cannot be negative");}if (depth == 0) {return target;}
class InnerClass {private void doStatementLogic() {String fieldVal = this.getClass().getSimpleName();do {fieldVal = target.innerField;} while (fieldVal == null);}}new InnerClass().doStatementLogic();
MyFunctionalInterface<T> anon = new MyFunctionalInterface<T>() {@Overridepublic void execute(T t) {System.out.println(instanceField);}};anon.execute(target.getData());
try {Method targetMethod = TargetClass.class.getMethod("getInnerClassInstance");Object innerInstance = targetMethod.invoke(target);} catch (Exception e) {}
String expr = instanceField + target.innerField;return recursiveMethod(target, depth - 1);}}
class TargetClass<T> {String innerField = "targetInnerField";private T data;
class TargetInnerClass {void useOuterField() {System.out.println(innerField);}}
public TargetClass(T data) {super();this.data = data;}
public TargetInnerClass getInnerClassInstance() {return new TargetInnerClass();}
public T getData() {return data;}}