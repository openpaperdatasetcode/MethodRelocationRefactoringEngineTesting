package source;
import target.TargetRecord;import java.util.function.Consumer;
private record SourceRecord(String data) extends ParentRecord {public class InnerSource {public class DeepInner {public Object instanceMethod(TargetRecord target) {// Local inner class with abstract methodabstract class AbstractInner {public abstract void process(TargetRecord t);}
// For loop containing abstract method invocationfor (int i = 0; i < 1; i++) {AbstractInner inner = new AbstractInner() {@Overridepublic void process(TargetRecord t) {// Instance reference method callt.processData(t.data());}};inner.process(target);}
// Volatile SuperMethodReference (1)volatile Consumer<TargetRecord> superRef = super::parentMethod;superRef.accept(target);
// Variable call + access instance fieldString targetField = target.data();Object result = TargetRecord.format(targetField);
// Call method: overriding + ClassName.methodName in for loopfor (int j = 0; j < 1; j++) {result = target.overriddenMethod(targetField);}
// Anonymous inner classnew Runnable() {@Overridepublic void run() {System.out.println("Source anonymous: " + target.data());}}.run();
return result;}}}
public Object triggerRefactor(TargetRecord target) {return new InnerSource().new DeepInner().instanceMethod(target);}}
abstract class ParentRecord {public void parentMethod(TargetRecord target) {System.out.println("Parent method: " + target.data());}}
package target;
private record TargetRecord(String data) extends ParentTargetRecord {@Overridepublic String overriddenMethod(String input) {// Anonymous inner classnew Runnable() {@Overridepublic void run() {System.out.println("Target anonymous: " + data);}}.run();return input + "_overridden";}
public void processData(String input) {System.out.println("Processing: " + input);}
public static final String format(String input) {return input.toUpperCase();}}
abstract class ParentTargetRecord {public abstract String overriddenMethod(String input);}