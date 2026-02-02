package sourcepackage;
import targetpackage.BaseClass;import targetpackage.TargetClass;import targetpackage.SubTargetClass;import java.util.function.Supplier;
public class OtherClass extends BaseClass {public OtherClass(String data) {super(data);}
@Overridepublic Object getInfo() {return "OtherClass: " + data;}}
strictfp class SourceClass extends BaseClass {// Member inner classclass SourceInner {}
// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("Source initialized");}};
public SourceClass(String data) {super(data);}
@Overridepublic void process(TargetClass target) {// Constructor invocation with method calls in parameter listSourceInner inner = new SourceInner(() -> new OtherClass("constructor_param").getInfo(), // others_class instance method (super.methodName())new SubTargetClass().getStaticData() // sub_class static method (new ClassName().method()));
// StringLiteral (1 occurrence)String literal = "processed";
// Variable callObject varCall = target.getData();
// Access and modify target fieldtarget.data = target.data + "_" + literal;}
// Constructor for inner class with functional parametersclass SourceInner {SourceInner(Supplier<Object> infoSupplier, String staticData) {System.out.println("Inner initialized with: " + infoSupplier.get() + ", " + staticData);}}}
package targetpackage;
public abstract class BaseClass {protected String data;
public BaseClass(String data) {this.data = data;}
public abstract void process(TargetClass target);public abstract Object getInfo();}
package targetpackage;
public class TargetClass {String data;
public TargetClass(String data) {this.data = data;// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {System.out.println("Target created: " + data);}};logger.run();}
public String getData() {return data;}}
package targetpackage;
public class SubTargetClass extends TargetClass {public SubTargetClass() {super("sub_target");}
public String getStaticData() {return "static_sub_data";}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.SourceClass;import targetpackage.TargetClass;
public class MoveMethodTest3184 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass("source_data");TargetClass target = new TargetClass("target_data");
source.process(target);assertEquals("target_data_processed", target.getData());}}