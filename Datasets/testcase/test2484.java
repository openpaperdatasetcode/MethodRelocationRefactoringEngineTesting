package sourcepackage;
import targetpackage.TargetRecord;import java.util.ArrayList;import java.util.List;
public record SourceRecord<T>(T content) {// Member inner classpublic class SourceInner {
// Nested inner class (source_inner_rec)
public class NestedInner<V> {
// Varargs method
protected Object process(TargetRecord<T>.TargetInner... inners) {
List<Object> results = new ArrayList<>();
// Local inner class in sourceclass Processor {Object handle(TargetRecord<T>.TargetInner inner) {
return inner.getCombined();
}
}
Processor processor = new Processor();
// Variable callObject varCall = inners.length > 0 ? inners[0].getInnerValue() : null;
// Assert statementassert inners != null : "Inner array must not be null";
// Do statement with target method call in while conditionint index = 0;do {if (index < inners.length) {// Call target instance method with super.methodName()Object result = inners[index].getParentValue();results.add(result);results.add(processor.handle(inners[index]));}index++;} while (index < inners.length);
return results;}}}}
package targetpackage;
// Protected target recordprotected record TargetRecord<T>(T id) {public TargetRecord {// Local inner class in targetclass Validator {boolean isValid() {return id != null;}}if (!new Validator().isValid()) {throw new IllegalArgumentException("ID cannot be null");}}
public class TargetInner {
private U innerValue;
public TargetInner(U innerValue) {this.innerValue = innerValue;}
public U getInnerValue() {return innerValue;}
public Object getCombined() {return id() + ":" + innerValue;}
// Method using super (implicitly calls record's id() method)public Object getParentValue() {return super.toString() + "_" + id();}}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.SourceRecord;import targetpackage.TargetRecord;import java.lang.reflect.Constructor;import java.util.List;
public class MoveMethodTest3185 {@Test@SuppressWarnings("unchecked")public void testVarargsMethod() throws Exception {// Create source recordSourceRecord<String> source = new SourceRecord<>("source_content");SourceRecord<String>.SourceInner<Integer> inner = source.new SourceInner<>();SourceRecord<String>.SourceInner<Integer>.NestedInner<Double> nested = inner.new NestedInner<>();
// Access protected TargetRecord via reflectionConstructor<TargetRecord<String>> targetConstructor =(Constructor<TargetRecord<String>>) TargetRecord.class.getDeclaredConstructor(Object.class);targetConstructor.setAccessible(true);TargetRecord<String> target = targetConstructor.newInstance("target_id");
// Create target inner instancesTargetRecord<String>.TargetInner<Integer> inner1 = target.new TargetInner<>(100);TargetRecord<String>.TargetInner<Integer> inner2 = target.new TargetInner<>(200);
// Process and verifyObject result = nested.process(inner1, inner2);assertTrue(result instanceof List);List<Object> resultList = (List<Object>) result;assertEquals(4, resultList.size()); // 2 targets × 2 results each}}