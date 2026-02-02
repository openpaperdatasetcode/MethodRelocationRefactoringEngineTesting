package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
protected sealed class SourceClass permits SourceSubClass {private transient TargetClass targetField = new TargetClass();
static class SourceStaticNested {static TargetClass createTarget() {return new TargetClass();}}
protected TargetClass recursiveMethod(int depth) {if (depth <= 0) {return targetField;}
class SourceLocalInner {TargetClass processTarget() {transient boolean hasField = (SourceClass.this.targetField.thisField != null);
if (hasField) {TargetClass.TargetInnerRec innerRec = new TargetClass.TargetInnerRec(SourceClass.this.targetField.thisField,data -> new ArrayList<>(List.of(data)));return innerRec.getTarget();}return SourceClass.this.recursiveMethod(depth - 1);}}
TargetClass varCall = new SourceLocalInner().processTarget();Runnable lambda = () -> System.out.println(varCall.thisField);lambda.run();
return new TargetClass(varCall,() -> SourceStaticNested.createTarget());}}
final class SourceSubClass extends SourceClass {}
package target;
import java.util.List;
class TargetClass implements DataProcessor {String thisField;
public TargetClass() {}
protected TargetClass(TargetClass other, TargetSupplier supplier) {this.thisField = other.thisField;supplier.get().thisField = "newData";}
class TargetInnerRec {private String data;
public TargetInnerRec(String data, DataMapper mapper) {this.data = data;List<String> mapped = mapper.map(data);}
TargetClass getTarget() {class TargetLocalInner {TargetClass get() {return TargetClass.this;}}return new TargetLocalInner().get();}}
@Overridepublic void process(String data) {this.thisField = data;}
@FunctionalInterfacepublic interface TargetSupplier {TargetClass get();}
@FunctionalInterfacepublic interface DataMapper {List<String> map(String data);}}
interface DataProcessor {void process(String data);}