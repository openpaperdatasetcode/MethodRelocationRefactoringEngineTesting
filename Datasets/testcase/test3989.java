package test;
import java.io.IOException;
/**
Javadoc for TargetClass: Generic class with protected access modifier,
contains anonymous inner class and overridden methods
@param <T> Type parameter for generic functionality*/protected class TargetClass<T> {private T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
public T getTargetField() {return targetField;}
public Object targetMethod() {return null;}
void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}}
class SubTargetClass<T> extends TargetClass<T> {public SubTargetClass(T targetField) {super(targetField);}
@Overridepublic Object targetMethod() {return getTargetField();}}
final class SourceClass<S> {class SourceInnerFirst {}class SourceInnerSecond {}
strictfp void overloadedMethod(TargetClass<S> target) throws IOException {S varCall = target.getTargetField();Object methodCall = target.targetMethod();}
strictfp void overloadedMethod(TargetClass<S> target, String extraParam) throws IOException {S varCall = target.getTargetField();Object methodCall = target.targetMethod();System.out.println(extraParam);}
static {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new SubTargetClass<>("testValue");try {source.overloadedMethod(target);Object result = TargetClass.InnerHelper.callInnerMethod(target);} catch (IOException e) {}}
static class InnerHelper {static Object callInnerMethod(TargetClass<?> target) {return target.targetMethod();}}}