// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.function.Supplier;
protected class SourceClass {private String outerPrivate = "private_data";
// Member inner classpublic class SourceMemberInner {TargetClass getTarget() {return new TargetClass();}}
// Local inner classpublic void createLocalInner() {class LocalHandler {Object handle(TargetClass target) {return normalMethod(target);}}new LocalHandler().handle(new SourceMemberInner().getTarget());}
// Method with no parameters of target type (method types parameter is:none)public final Object normalMethod(TargetClass target) {// Super constructor invocation in anonymous subclassTargetClass subTarget = new TargetClass() {{super();}};
// Expression statementString data = target.publicField + outerPrivate;
// Variable callObject result = subTarget.getField();
// Access outer privateresult = result + "_" + outerPrivate;
// Lambda expression with call method (overloading, superTypeReference)Supplier<String> supplier = () -> target.process(data, 2);result = supplier.get();
return result;}}
// Target package: com.targetpackage com.target;
class TargetParent {protected String parentField = "parent_data";}
private class TargetClass extends TargetParent {String publicField = "target_field";
public TargetClass() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {publicField += "_init";}};init.run();}
public Object getField() {return publicField;}
// Overloaded methodsString process(String str) {return str + "_processed";}
String process(String str, int times) {return super.parentField + "_" + str.repeat(times);}}
