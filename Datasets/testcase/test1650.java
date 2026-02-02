package test;
non-sealed enum SourceEnum {A, B;
// Member inner class containing the target methodpublic class SourceInner {private String innerField = "inner_data";
// Final instance method returning base typefinal int instanceMethod(AbstractTargetEnum.Inner targetInner) {int result = 0;
// Variable callresult += targetInner.getValue();result += targetInner.process(A);
// Uses outer thisresult += SourceEnum.this.ordinal();result += SourceEnum.this.name().length();
// Local inner classclass LocalProcessor {int process() {return targetInner.getValue() * 2;}}result += new LocalProcessor().process();
// SuperConstructorInvocation with 1 obj.field reference (same package target)AbstractTargetEnum.Inner extendedInner = new AbstractTargetEnum.Inner(targetInner) {{super(targetInner.value); // obj.field reference}};result += extendedInner.getValue();
// While loop with varargs source method and source call methodint i = 0;while (i < 3) {// 3-argument varargs method (ClassName.methodName)AbstractTargetEnum processed = SourceEnum.processVarargs(targetInner,String.valueOf(i),innerField);
// Source private instance method call (this.methodName)AbstractTargetEnum updated = this.enhance(processed);result += updated.ordinal();
i++;}
return result;}
// Private source method for call_methodprivate AbstractTargetEnum enhance(AbstractTargetEnum target) {return target;}}
// Public varargs source method (3 parameters)public static AbstractTargetEnum processVarargs(AbstractTargetEnum.Inner inner,String... params) {return AbstractTargetEnum.X;}}
abstract enum AbstractTargetEnum {X(5), Y(10);
protected int value;
AbstractTargetEnum(int value) {this.value = value;// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {value *= 2;}};initializer.run();}
public abstract int process(SourceEnum source);
// Target inner classpublic class Inner {protected int value;
public Inner(int value) {this.value = value;}
public Inner(Inner other) {this.value = other.value;}
public int getValue() {return value;}
public int process(SourceEnum source) {return value + source.ordinal();}}}