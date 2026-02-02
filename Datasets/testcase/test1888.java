// Source package: testpackage test;
import com.other.OtherProcessor;import java.lang.reflect.Field;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
private record SourceRecord<T>(T data) extends SourceParent implements Processable {// Static nested classpublic static class SourceStatic {public static String STATIC_FIELD = "static_data";}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {new SourceRecord<>("init").varargsMethod(new TargetRecord<>("test").new InnerRec(1, "anon"));}};
Object varargsMethod(TargetRecord<?>.InnerRec... targetRecs) {// Type declaration statementList<Object> results = new ArrayList<>();OtherProcessor processor = new OtherProcessor();
// Variable callfor (TargetRecord<?>.InnerRec rec : targetRecs) {results.add(rec.value());}
// Access instance fieldresults.add(targetRecs[0].id());
// With boundsclass BoundedHandler<U extends Number & Comparable> {
U process(U input) {
return input;
}
}
results.add(new BoundedHandler<Integer>().process(5));
// otherObject.process(this);processor.process(this);
// Used by reflectiontry {Field field = TargetRecord.InnerRec.class.getField("id");results.add(field.get(targetRecs[0]));} catch (Exception e) {// No new exception}
// Expression with others_class overriding method referenceFunction<TargetRecord<?>.InnerRec, List<String>> mapper = OtherProcessor::mapRec;results.add(mapper.apply(targetRecs[0]));
// Private ConstructorInvocation with ClassName.field (diff package)results.add(processor.createWithStaticField(targetRecs[0]));
return results;}
@Overridepublic void process() {}}
class SourceParent {protected String parentData = "parent_data";}
interface Processable {void process();}
// Target package: testpackage test;
public record TargetRecord<T>(T value) {// Member inner classpublic class InnerRec {private final int id;private final String value;
public InnerRec(int id, String value) {this.id = id;this.value = value;}
public int id() {return id;}
public String value() {return value;}}
public static String STATIC_FIELD = "target_static";}
// Other package: com.otherpackage com.other;
import test.SourceRecord;import test.TargetRecord;import java.util.ArrayList;import java.util.List;
class OtherProcessor {public void process(SourceRecord<?> source) {System.out.println("Processing source: " + source.data());}
// Overriding method (overrides parent)public static List<String> mapRec(TargetRecord<?>.InnerRec rec) {List<String> list = new ArrayList<>();list.add(rec.id() + ":" + rec.value());return list;}
// Private ConstructorInvocation with ClassName.fieldpublic TargetRecord.InnerRec createWithStaticField(TargetRecord<?>.InnerRec base) {class PrivateCreator {private TargetRecord.InnerRec create() {return new TargetRecord<>("").new InnerRec(base.id() + 100,base.value() + "_" + TargetRecord.STATIC_FIELD);}}return new PrivateCreator().create();}}
class ProcessorParent {public static List<String> mapRec(TargetRecord<?>.InnerRec rec) {return new ArrayList<>();}}