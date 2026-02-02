// Source package: testpackage test;
import com.other.OtherProcessor;import java.util.ArrayList;import java.util.List;
record SourceRecord<T>(T content) {// Static nested classpublic static class SourceStatic {public static U convert(U input) {
return input;
}
}
// Member inner classpublic class SourceInner {
private U data;
public SourceInner(U data) {this.data = data;}
public U getData() {return data;}}
// Varargs method with Target class parametersfinal Object varargsMethod(AbstractTargetRecord<?>... targets) {List<Object> results = new ArrayList<>();
// Variable callfor (AbstractTargetRecord<?> target : targets) {results.add(target.value());results.add(target.inner().detail());}
// Empty statement;
// With boundsclass BoundedHandler<U extends Number & Comparable> {
U sum(U a, U b) {
return (U) Integer.valueOf(a.intValue() + b.intValue());
}
}
results.add(new BoundedHandler<Integer>().sum(3, 5));
// Exception throwing statements with 2 final generic inner_class methods (super call)try {SourceInner<String> inner = new SourceInner<>("test");GenericProcessor<String> processor = new GenericProcessor<>(inner);processor.process("throw");} catch (IllegalArgumentException e) {// No new exceptionresults.add("caught");}
// Private ConstructorInvocation with 3 obj.field references (diff package)OtherProcessor processor = new OtherProcessor();results.add(processor.createTarget(targets[0]));
return results;}
private class GenericProcessor extends ParentProcessor {
private SourceInner inner;
public GenericProcessor(SourceInner inner) {
this.inner = inner;
}
@Overridepublic final void process(U input) {super.process(input);if ("throw".equals(input)) {throw new IllegalArgumentException("Throwing as requested");}}
@Overridepublic final List transform(U input) {
List list = super.transform(input);
list.add(inner.getData());
return list;
}
}
}
abstract class ParentProcessor {
public void process(U input) {}
public List transform(U input) {
return new ArrayList<>();
}
}
// Target package: testpackage test;
abstract record AbstractTargetRecord<T>(T value, Inner<T> inner) {// Member inner classpublic record Inner(U detail) {}
}
// Other package: com.otherpackage com.other;
import test.AbstractTargetRecord;
public class OtherProcessor {public AbstractTargetRecord<String> createTarget(AbstractTargetRecord<?> base) {class TargetCreator {private AbstractTargetRecord<String> create() {// 3 obj.field referencesString val1 = base.value().toString();String det1 = base.inner().detail().toString();AbstractTargetRecord.Inner<String> inner = new AbstractTargetRecord.Inner<>(det1 + "_new");return new ConcreteTargetRecord(val1 + "_created", inner);}}return new TargetCreator().create();}}
// Concrete implementation of abstract recordfinal class ConcreteTargetRecord extends AbstractTargetRecord<String> {public ConcreteTargetRecord(String value, Inner<String> inner) {super(value, inner);}}