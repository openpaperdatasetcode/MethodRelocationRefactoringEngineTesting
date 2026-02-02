package test;
import java.lang.annotation.*;import other.OthersClass;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessInfo {String value();}
protected class SourceClass<T> {public class MemberInnerInner {public T getValue(TargetClass<T> target) {return target.data;}}
@ProcessInfo(value = "#{OthersClass.getProcessor().process().getValue(target)}")public Object process(TargetClass<T> target) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {target.data = (T) "initialized";}};initializer.run();
MemberInner inner = new MemberInner();
// Variable call - access target's fieldObject result = inner.getValue(target);
// Overloaded methods existprocessOverload(target);processOverload(target, 1);
// Ternary operator with inner_class's synchronized instance methodboolean processed = target.data != null? (new target.InnerProcessor().process(target), true): false;
return result;}
// Overloaded methodsprivate void processOverload(TargetClass<T> target) {}private void processOverload(TargetClass<T> target, int flag) {}}
abstract class TargetClass<T> {public T data;
public TargetClass() {// Local inner class in targetclass DataHandler {void setup() {data = (T) "default";}}new DataHandler().setup();}
public class InnerProcessor {// Synchronized instance methodpublic synchronized void process(TargetClass<T> target) {target.data = (T) (target.data + "_processed");}}}
package other;
import test.TargetClass;
public class OthersClass {public static Processor getProcessor() {return new Processor();}
public static class Processor {public Handler process() {return new Handler();}
public static class Handler {public <T> Object getValue(TargetClass<T> target) {return target.data;}}}}