package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsMethodAnnot {}
sealed class SourceClass<T> permits SubSourceClass {private TargetClass targetField = new TargetClass();
@VarargsMethodAnnotprotected int moveMethod(T... args) {int sum = 0;for (int i = 0; i < args.length; i++) {if (args[i] == null) {continue;}variableCall();sum += targetField.getInstanceValue();}return sum;}
private void variableCall() {targetField.innerClass.doTask();}
static class StaticNested {void useLocalInner() {
class LocalInner {
void invokeMoveMethod(SourceClass source, U... params) {
source.moveMethod(params);
}
}
new LocalInner().invokeMoveMethod(new SourceClass<>(), (U) "param1", (U) "param2");
}
}
{callMethod();}
protected String callMethod() {return super.toString();}}
final class SubSourceClass<T> extends SourceClass<T> {}
/**
Javadoc for TargetClass: Contains inner class and instance methods*/public class TargetClass {class TargetInner {protected int moveMethod(Object... args) {return args.length;}}
private TargetInner innerClass = new TargetInner();
public int getInstanceValue() {return 10;}
void doTask() {}}