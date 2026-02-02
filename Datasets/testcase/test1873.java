// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import com.target.TargetSubclass1;import com.target.TargetSubclass2;import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class SourceClass {protected String outerProtected = "protected_data";
// Static nested classpublic static class SourceStaticNested {static TargetClass getDefault() {return new TargetSubclass1();}}
protected static TargetClass staticMethod(TargetClass target) {// Local inner classclass LocalProcessor {void process(TargetClass t) {// Variable callt.setValue(outerProtected);}}new LocalProcessor().process(target);
// Access outer protectedtarget.setExtra(outerProtected + "_extra");
// Collection operations with 2 abstract others_class methods (method reference)List<TargetClass> targets = new ArrayList<>();targets.add(target);targets.add(new TargetSubclass2());
Consumer<TargetClass> consumer1 = OtherClass::processOne;Consumer<TargetClass> consumer2 = OtherClass::processTwo;
targets.forEach(consumer1.andThen(consumer2));
return targets.get(0);}}
// Other package: com.otherpackage com.other;
import com.target.TargetClass;
public abstract class OtherClass {public static void processOne(TargetClass target) {target.log("Processed one");}
public static void processTwo(TargetClass target) {target.log("Processed two");}}
// Target package: com.targetpackage com.target;
sealed class TargetClass permits TargetSubclass1, TargetSubclass2 {protected String value;protected String extra;
public void setValue(String val) {this.value = val;}
public void setExtra(String ext) {this.extra = ext;}
public abstract void log(String message);}
final class TargetSubclass1 extends TargetClass {@Overridepublic void log(String message) {System.out.println("Sub1: " + message);}}
final class TargetSubclass2 extends TargetClass {@Overridepublic void log(String message) {System.out.println("Sub2: " + message);}}