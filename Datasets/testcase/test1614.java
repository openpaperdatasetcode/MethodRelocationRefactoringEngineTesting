package test;
import java.io.IOException;import java.util.List;
public sealed record SourceRecord<T>(T value) permits ExtendedSourceRecord<T> {public static class StaticNested {public class InnerRec {private <T> TargetRecord<T> process(TargetRecord<T> target) throws IOException {// Local inner classclass TargetHandler {TargetRecord<T> handle(TargetRecord<T> t) {// Variable call - access target's fieldt.data = (T) (t.data + "_handled");return t;}}TargetHandler handler = new TargetHandler();
// Public ConstructorInvocation with this.field (1 occurrence)TargetRecord<T> newTarget = new TargetRecord<>(target.data);
// Anonymous inner class in targetRunnable initializer = target.new Runnable() {@Overridepublic void run() {newTarget.data = target.data;}};initializer.run();
// Overloaded methods existtarget.update();target.update(1);
// Call target's default static method in array initializationObject[] array = { TargetRecord.staticProcess(target.data) };
if (array.length == 0) {throw new IOException("Array initialization failed");}
return handler.handle(newTarget);}}}}
record ExtendedSourceRecord<T>(T value, String extra) extends SourceRecord<T>(value) {}
protected record TargetRecord<T>(T content) {public T data;
// Overloaded methodspublic void update() {data = content;}
public void update(int version) {data = (T) (content + "_v" + version);}
// Default static methodstatic <T> Object staticProcess(T data) {return data.toString();}
// Anonymous inner class basepublic interface Runnable {void run();}}