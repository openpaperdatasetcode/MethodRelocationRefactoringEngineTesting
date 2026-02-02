package test;
import other.ExternalClass;
protected interface TargetInterface extends Runnable {class TargetInner {protected int innerField = 3;
TargetInterface getTargetInstance() {return new TargetInterface() {@Overridepublic void run() {}};}}
@Overridevoid run();}
interface SourceInterface extends ParentInterface permits SourceInterface.SourceImpl {TargetInterface.TargetInner targetInnerField = new TargetInterface.TargetInner();
default void createLocalInner() {class SourceLocalInner {}}
default void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@RefactorTestprotected TargetInterface recursiveMethod(int depth) {if (depth <= 0) {return targetInnerField.getTargetInstance();}
try {ExternalClass.process(() -> {if (targetInnerField.innerField != 3) {throw new IllegalArgumentException("Super field mismatch");}targetInnerField.innerField = depth;});} catch (Exception e) {return targetInnerField.getTargetInstance();}
return recursiveMethod(depth - 1);}
class SourceImpl implements SourceInterface {}}
interface ParentInterface {}
@interface RefactorTest {}
package other;
import java.util.function.Runnable;
public class ExternalClass {public static void process(Runnable task) {task.run();}}