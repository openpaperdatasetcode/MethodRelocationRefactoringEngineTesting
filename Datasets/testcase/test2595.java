package same;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
protected class SourceClass<T> {static class StaticNested {}
class InnerRec<S> {@SuppressWarnings("override")List<String> process(TargetClass<T> target) throws IllegalStateException {class LocalHandler {}LocalHandler handler = new LocalHandler();
// Constructor from parent class in LambdaSupplier<TargetClass<T>> targetSupplier = () -> {class SubTarget extends TargetClass<T> {SubTarget(T data) {super(data);}}return new SubTarget(target.getData());};
TargetClass<T> newTarget = targetSupplier.get();List<String> result = new ArrayList<>();
labeledBlock: {switch (target.getInner().getType()) {case "TEXT":result.add(target.getInner().getValue().toString());break;case "NUMERIC":result.add(String.valueOf(target.getInner().getValue()));break;default:throw new IllegalStateException("Unknown type");}result.addAll(newTarget.getInner().processItems());}
return result;}}}
package same;
import java.util.List;import java.util.ArrayList;
class TargetClass<T> {private final T data;Inner<T> inner = new Inner<>();
TargetClass(T data) {this.data = data;}
T getData() {return data;}
Inner<T> getInner() {return inner;}
class Inner {
private U value;
private String type;
String getType() {return type;}
U getValue() {return value;}
List<String> processItems() {return new ArrayList<>();}}}