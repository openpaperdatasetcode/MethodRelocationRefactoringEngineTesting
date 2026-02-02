// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.List;import java.util.function.Supplier;
abstract class SourceClass implements Processable {private String outerPrivate = "private_data";private static int staticCounter = 0;
// Member inner classpublic class SourceInner {/**
Processes TargetClass instance from different package
@param target TargetClass instance to process*/private void instanceMethod(TargetClass target) {// Type declaration statementTargetClass.MemberInner inner = target.new MemberInner();int count = 0;
// While statementwhile (count < 3) {// Variable callinner.setValue(target.publicField + count);count++;}
// Static WhileStatement with obj.field (diff package)class StaticProcessor {static void process(TargetClass t) {int i = 0;while (i < t.publicField) {staticCounter++;i++;}}}StaticProcessor.process(target);
// Access outer privateinner.setData(outerPrivate);
// Depends on static fieldtarget.publicField += staticCounter;
// Uses outer thisString outerInfo = SourceClass.this.toString();inner.setData(outerInfo);
// Functional interface with call method (target constructor + super call)Supplier<List<String>> supplier = () -> {TargetClass subTarget = new TargetClass() {{super(target.publicField);}};return subTarget.getList();};List<String> result = supplier.get();}}
// Local inner classpublic void outerMethod(TargetClass target) {class LocalHandler {void handle() {new SourceInner().instanceMethod(target);}}new LocalHandler().handle();}}
interface Processable {}
// Target package: com.targetpackage com.target;
import java.util.ArrayList;import java.util.List;
abstract class TargetClass {public int publicField;
public TargetClass(int field) {this.publicField = field;}
// Member inner classpublic class MemberInner {private int value;private String data;
public void setValue(int value) {this.value = value;}
public void setData(String data) {this.data = data;}}
protected List<String> getList() {return new ArrayList<>();}}