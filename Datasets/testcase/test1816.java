package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass<T> {static class StaticNested {}
protected class SourceInner {protected List<String> varargsMethod(TargetClass... targets) {List<String> result = new ArrayList<>();
// ConstructorInvocation with transient modifier using 3 target inner fieldstransient class TransientConstructor {TransientConstructor(TargetClass.TargetInner inner) {String combined = inner.this.fieldA + inner.this.fieldB + inner.this.fieldC;result.add(combined);}}
// Enhanced for statementfor (TargetClass target : targets) {new TransientConstructor(target.new TargetInner());result.add(target.toString());}
// Super constructor invocation in anonymous subclassRunnable runner = new Runnable() {{super();}
@Overridepublic void run() {// Variable call and depends on inner classTargetClass.TargetInner inner = targets[0].new TargetInner();result.add(inner.getFieldA());}};runner.run();
// otherObject.process(this)new Processor().process(this);
// Overloaded method callsprocessValue(10);processValue("string");
return result;}
// Overloaded methodprivate void processValue(int num) {// Implementation}
// Overloaded methodprivate void processValue(String str) {// Implementation}}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {new SourceInner().varargsMethod(new TargetClass() {});}}.run();}
static class Processor {void process(SourceInner inner) {// Process logic}}}
non-sealed abstract class TargetClass {public class TargetInner {String fieldA = "A";String fieldB = "B";String fieldC = "C";
String getFieldA() {return fieldA;}}}