package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
interface DataProcessor {List<String> process();}
// Final target class with anonymous inner classfinal class TargetClass {private String baseData;
public TargetClass(String baseData) {this.baseData = baseData;// Anonymous inner class in targetConsumer<String> logger = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println("Target initialized with: " + s);}};logger.accept(baseData);}
public class TargetInner implements DataProcessor {private String innerData;
public TargetInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}
@Overridepublic List<String> process() {return List.of(baseData, innerData);}}}
class SourceClass {private String outerPrivate = "outer_private_data";
class SourceInner {class NestedInner implements DataProcessor {private TargetClass.TargetInner targetInner;
public NestedInner(TargetClass.TargetInner targetInner) {this.targetInner = targetInner;}
@Overridepublic List<String> process() {List<String> results = new ArrayList<>();
// Variable callObject varCall = targetInner.getInnerData();results.add(varCall.toString());
// Expression statementtargetInner.setInnerData(targetInner.getInnerData() + "_modified");results.add(targetInner.getInnerData());
// Access outer private fieldresults.add(outerPrivate);
// Local inner class in sourceclass ResultEnhancer {void addBaseInfo() {// Access target inner's processed dataresults.addAll(targetInner.process());}}new ResultEnhancer().addBaseInfo();
// Anonymous inner class in sourceRunnable finalizer = new Runnable() {@Overridepublic void run() {results.add("processing_complete");}};finalizer.run();
return results;}}}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3187 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();
TargetClass target = new TargetClass("base_data");TargetClass.TargetInner targetInner = target.new TargetInner("inner_data");
SourceClass.SourceInner.NestedInner processor = inner.new NestedInner(targetInner);List<String> result = processor.process();
assertEquals(6, result.size());assertTrue(result.contains("inner_data"));assertTrue(result.contains("inner_data_modified"));assertTrue(result.contains("outer_private_data"));assertTrue(result.contains("base_data"));assertTrue(result.contains("processing_complete"));}}
