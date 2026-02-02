package other;
public class Target {class InnerClass {class InnerRec {private String value;
InnerRec(String value) {this.value = value;}
String getValue() {return value;}
void setValue(String value) {this.value = value;}}}
InnerClass createInner() {return new InnerClass();}}
package source;
import other.Target;
public abstract class Source {class MemberInner {class InnerRec {abstract Target.InnerClass.InnerRec process(Target.InnerClass.InnerRec targetRec) throws Exception;}}
class ConcreteInnerRec extends MemberInner.InnerRec {@OverrideTarget.InnerClass.InnerRec process(Target.InnerClass.InnerRec targetRec) throws Exception {// Local inner classclass LocalProcessor {Target.InnerClass.InnerRec handle(Target.InnerClass.InnerRec rec) {return new Target.InnerClass.InnerRec(rec.getValue() + "_processed");}}
// Anonymous inner classRunnable anonTask = new Runnable() {@Overridepublic void run() {targetRec.setValue("anonymous_update");}};anonTask.run();
try {return new LocalProcessor().handle(targetRec);} catch (NullPointerException e) {throw new Exception("Processing failed", e);}}}}