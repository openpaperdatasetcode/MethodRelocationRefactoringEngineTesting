package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface DataHandler {int process(TargetClass target);}
public class TargetClass {private String data;private int count;
public TargetClass(String data, int count) {this.data = data;this.count = count;}
public static class TargetStaticNested {private String nestedData;
public TargetStaticNested(String nestedData) {this.nestedData = nestedData;}
public int calculate() {return nestedData.length();}}
public String getData() {return data;}
public int getCount() {return count;}}
// Private source class implementing DataHandlerprivate class SourceClass implements DataHandler {// Member inner classclass SourceInner {public List<String> generate(TargetClass target) {List<String> items = new ArrayList<>();items.add(target.getData() + "_" + target.getCount());return items;}}
// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("SourceClass initialized");}};
// Static code block with inner class constructor callstatic {TargetClass outerInstance = new TargetClass("static_init", 0);TargetClass.TargetStaticNested staticNested = outerInstance.new TargetStaticNested("static_nested");System.out.println("Static block result: " + staticNested.calculate());}
@Overridepublic final int process(TargetClass target) {// Type declaration statementint total = 0;SourceInner inner = new SourceInner();
// Variable callObject varCall = target.getData();
// Labeled statementprocessing: {total += target.getCount();if (total < 0) {break processing;}total *= 2;}
// If/else conditions with inner class instance methodif (target.getCount() > 5) {List<String> results = new SourceInner().generate(target);total += results.size();} else {List<String> results = inner.generate(target);total += results.size() * 2;}
// Used by reflectiontry {Method method = SourceInner.class.getMethod("generate", TargetClass.class);List<String> reflectedResults = (List<String>) method.invoke(inner, target);total += reflectedResults.size();} catch (Exception e) {e.printStackTrace();}
return total;}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Constructor;
public class MoveMethodTest3191 {@Testpublic void testOverridingMethod() throws Exception {// Create private SourceClass via reflectionConstructor<SourceClass> sourceConstructor = SourceClass.class.getDeclaredConstructor();sourceConstructor.setAccessible(true);SourceClass source = sourceConstructor.newInstance();
TargetClass target = new TargetClass("test_data", 3);
int result = source.process(target);assertEquals(9, result); // 3*2 + 2 (from else) + 1 (reflection) = 6+2+1=9}}