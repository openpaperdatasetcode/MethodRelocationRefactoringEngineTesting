package test;
import java.util.function.Supplier;
public class SourceClass<T extends Number & Comparable<T>> {private TargetClass<T> targetField = new TargetClass<>();
class MemberInner1 {class InnerRec {/**
Method Javadoc: Normal method with generic bounds, uses target inner class
@return Object instance*/public Object moveMethod() {char lit1 = 'A';char lit2 = 'B';int count = 3;
while (count-- > 0) {Object result = new OthersClass().instanceMethod(targetField);variableCall();}
new TargetClass<T>() {{super();}};
OtherProcessor.process(this);targetField.accessInstanceMethod();return new Object();}
private void variableCall() {targetField.new TargetInner().doAction();}}}
class MemberInner2 {}}
private class TargetClass<T> {class TargetInner {public Object moveMethod() {return new Object();}
void doAction() {}}
public void accessInstanceMethod() {}
{new Runnable() {@Overridepublic void run() {}}.run();}}
class OthersClass {protected <T> Object instanceMethod(TargetClass<T> target) {return target.new TargetInner();}}
class OtherProcessor {static <T> void process(SourceClass<T>.MemberInner1.InnerRec innerRec) {}}
class SubClass<T extends Integer> extends SourceClass<T> {@Overridepublic synchronized int callMethod() {SourceClass<T> outer = new SourceClass<>();SourceClass<T>.MemberInner1 inner1 = outer.new MemberInner1();SourceClass<T>.MemberInner1.InnerRec innerRec = inner1.new InnerRec();
Object[] array = { innerRec.moveMethod() };return array.length;}}
