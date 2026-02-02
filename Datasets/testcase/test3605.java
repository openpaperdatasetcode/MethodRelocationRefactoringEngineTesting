package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
interface SourceInterface {class MemberInner {public void overloadMethod(String s) {}public void overloadMethod(Integer i) {}}
default Object anonymousInnerCreator() {return new Runnable() {public void run() {}};}
protected Object instanceMethod(TargetInterface target, String[] arr) throws IOException {@MethodAnnotObject obj = arr[0];
MemberInner inner = new MemberInner();inner.overloadMethod("test");inner.overloadMethod(1);
target.process();variableCall(target);
return obj;}
private void variableCall(TargetInterface t) {t.toString();}}
/**
Javadoc for TargetInterface
Contains anonymous inner class*/public interface TargetInterface {default void process() {new Runnable() {public void run() {System.out.println("Target anonymous inner class");}}.run();}
<T extends CharSequence> T withBounds(T t);}