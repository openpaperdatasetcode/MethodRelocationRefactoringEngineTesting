package test;
import java.lang.reflect.Method;
private class SourceClass<T> {protected String outerProtectedField = "source-protected-data";private static TargetClass<T> staticTargetField;
class SourceInner {void initTarget() {SourceClass.staticTargetField = new TargetClass<>();}}
static class SourceStaticNested {void callStaticProcess() {try {SourceClass.process(SourceClass.staticTargetField.new TargetInner());} catch (ReflectiveOperationException e) {// No new exception}}}
static <T> void process(TargetClass<T>.TargetInner targetInner) throws ReflectiveOperationException {if (targetInner == null) {throw new NullPointerException("TargetInner cannot be null");}
Method getMethod = TargetClass.TargetInner.class.getMethod("getData");T data = (T) getMethod.invoke(targetInner);
targetInner.setData((T) new SourceClass<T>().outerProtectedField);TargetClass.TargetStaticNested.log(targetInner.getData().toString());}}
public class TargetClass<T> {class TargetInner {private T data;
public T getData() {return data;}
public void setData(T data) {this.data = data;}}
static class TargetStaticNested {public static <T> void log(String msg) {System.out.println("Target Log: " + msg);}}}