package test;
import java.lang.reflect.Constructor;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {// First local inner classpublic void method1(TargetClass.InnerRec rec) {class LocalHandler1 {TargetClass process() {return new SourceClass().instanceMethod(rec);}}new LocalHandler1().process();}
// Second local inner classpublic void method2(TargetClass target) {class LocalHandler2 {void handle() {instanceMethod(target.new InnerRec(2, "local2"));}}new LocalHandler2().handle();}
private TargetClass instanceMethod(TargetClass.InnerRec targetRec) {// Super constructor invocation in anonymous subclassTargetClass target = new TargetClass() {{super(targetRec.value());}
@Overridepublic void abstractMethod() {}};
// Variable calltarget.setData(targetRec.value() + "_processed");
// With boundsclass BoundedProcessor<T extends Number & Comparable<T>> {T increment(T num) {return (T) Integer.valueOf(num.intValue() + 1);}}BoundedProcessor<Integer> processor = new BoundedProcessor<>();int result = processor.increment(targetRec.id());target.setCount(result);
// Try statementtry {// Used by reflectionMethod method = TargetClass.InnerRec.class.getMethod("id");int id = (int) method.invoke(targetRec);target.setCount(id);} catch (Exception e) {// No new exception}
// Annotation with call method (others_class constructor, superTypeReference)@CustomAnnotation(value = OtherProcessor.createId(targetRec),handler = OtherProcessor::processRec)class AnnotatedHolder {}
return target;}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface CustomAnnotation {int value();java.util.function.Consumer<TargetClass.InnerRec> handler();}
abstract class TargetClass {protected String data;protected int count;
public TargetClass(String base) {this.data = base;}
public void setData(String data) {this.data = data;}
public void setCount(int count) {this.count = count;}
// Member inner classpublic class InnerRec {private final int id;private final String value;
public InnerRec(int id, String value) {this.id = id;this.value = value;}
public int id() {return id;}
public String value() {return value;}}
public abstract void abstractMethod();}
class OtherProcessor {// Constructor call in superTypeReference contextpublic static int createId(TargetClass.InnerRec rec) {Number num = new Integer(rec.id());return processNumber(num);}
private static int processNumber(Number num) {return num.intValue() * 2;}
// Super type reference methodpublic static void processRec(TargetClass.InnerRec rec) {System.out.println("Processing: " + rec.id() + ":" + rec.value());}}