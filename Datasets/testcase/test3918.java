package test;
import java.io.IOException;import java.util.function.Consumer;
// Parent interface for target interface to implementinterface BaseInterface {}
// Source interface: generic (type parameter), with local inner & anonymous inner classesinterface SourceInterface<T> {// Static field (target for OuterClass.this.x)String sourceField = "source-data";
// Member inner class (for source_inner_rec method position)class SourceInner {// Recursive method (source_inner_rec)protected Object recursiveMethod(TargetInterface<T> target, int depth) throws IOException {// Base case for recursionif (depth <= 0) {return null;}
// requires_throws (method feature) - declares IOExceptionif (depth == 2) {throw new IOException("Test IO exception");}
// OuterClass.this.x (access source interface's static field)String data = SourceInterface.this.sourceField;
// Local inner class (source feature)class LocalProcessor {void process(TargetInterface<T> t) {variableCall(); // Variable call (method feature)}}new LocalProcessor().process(target);
// Anonymous inner class (source feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println("Processing depth: " + depth);}};anonTask.run();
// call_method: inner_class type, pos: expressionConsumer<TargetInterface.TargetStaticNested<T>> consumer = TargetInterface.TargetStaticNested<T>::process;consumer.accept(new TargetInterface.TargetStaticNested<>());
// Recursive call (source_inner_rec)return recursiveMethod(target, depth - 1);}
// Variable call target methodprivate void variableCall() {}}
// Default method to trigger inner class recursiondefault Object triggerRecursion(TargetInterface<T> target) throws IOException {return new SourceInner().recursiveMethod(target, 3);}}
// Target interface: with implements & static nested class (target features)interface TargetInterface<T> extends BaseInterface {// Static nested class (target feature)static class TargetStaticNested<T> {public void process() {}}
T getTargetData();}