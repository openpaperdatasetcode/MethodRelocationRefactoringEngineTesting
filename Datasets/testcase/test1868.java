package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
sealed enum SourceEnum permits SourceEnum.First, SourceEnum.Second {First, Second;
@Overridepublic void process(TargetEnum<?> target) {// First local inner classclass LocalProcessor1 {void validate() {if (target.value == null) {throw new IllegalArgumentException("Value is null");}}}new LocalProcessor1().validate();
// Second local inner class with boundsclass LocalProcessor2<T extends Number & Comparable<T>> {void handle(T num) {target.setNum(num);}}new LocalProcessor2<Integer>().handle(10);
// ConstructorInvocation with 2 super.field references (inner class)class InnerConstructor {private TargetEnum.Inner inner1;private TargetEnum.Inner inner2;
InnerConstructor() {inner1 = new TargetEnum.Inner(target.superField1);inner2 = new TargetEnum.Inner(target.superField2);}}new InnerConstructor();
// Super constructor invocation in anonymous subclassTargetEnum<String> subTarget = new TargetEnum<>("sub") {{super("base");}};
// Variable callString data = target.getInfo();
// Throw statementif (data.isEmpty()) {throw new IllegalStateException("Empty data");}
// Annotation with instance method (super call)@MyAnnotation(handler = TargetEnum.Inner::create)class AnnotationHolder {}
// Ternary operator with call method (instance reference)Runnable action = data.length() > 5 ? this::logLong : this::logShort;action.run();}
public void logLong() {System.out.println("Long data");}
public void logShort() {System.out.println("Short data");}}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {Class<?> handler();}
abstract enum TargetEnum<T> implements Processable {A("a"), B("b");
protected T value;protected int superField1 = 10;protected int superField2 = 20;
TargetEnum(T value) {this.value = value;}
// Member inner classpublic class Inner {private int data;
public Inner(int data) {this.data = data;}
protected TargetEnum<T> create() {super.process();return TargetEnum.A;}}
public String getInfo() {return value.toString();}
public void setNum(Number num) {}
@Overridepublic abstract void process();}
interface Processable {void process();}