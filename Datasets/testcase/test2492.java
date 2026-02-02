package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
class OtherClass {protected Object processLevel1(String input) {return input + "_level1";}
protected Object processLevel2(Object input) {return input + "_level2";}
protected Object processLevel3(Object input) {return input + "_level3";}}
class TargetClass {private String data;private int depth;
public TargetClass(String data, int depth) {this.data = data;this.depth = depth;}
public String getData() {return data;}
public int getDepth() {return depth;}
public void setData(String data) {this.data = data;}}
private class SourceClass {// Member inner classclass SourceInner {// Nested inner class (source_inner_rec)public class NestedInner {private int currentDepth = 0;
// Varargs method returning TargetClass typepublic TargetClass process(TargetClass... targets) {if (targets == null || targets.length == 0) {return new TargetClass("default", 0);}
// Local inner classclass TargetProcessor {// Recursive methodpublic int calculateDepth(TargetClass target) {currentDepth++;// Recursion termination conditionif (currentDepth >= target.getDepth()) {return currentDepth;}// Recursive call (this.methodName(arguments))return this.calculateDepth(target);}}TargetProcessor processor = new TargetProcessor();
// Variable callObject varCall = targets[0].getData();
// Expression statementtargets[0].setData(targets[0].getData() + "_processed");
// Requires try-catchtry {// Exception handling with recursive methodfor (TargetClass target : targets) {int calculated = processor.calculateDepth(target);target.setData(target.getData() + "depth" + calculated);}} catch (StackOverflowError e) {System.err.println("Recursion depth exceeded: " + e.getMessage());return new TargetClass("error", -1);}
// For loop with others_class overloading method chain (obj.m1().m2().m3())OtherClass other = new OtherClass();List<Object> chainResults = new ArrayList<>();for (TargetClass target : targets) {Object result = other.processLevel3(other.processLevel2(other.processLevel1(target.getData())));chainResults.add(result);}
return targets[0];}}}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Constructor;
public class MoveMethodTest3195 {@Testpublic void testVarargsMethod() throws Exception {// Create private SourceClass via reflectionConstructor<SourceClass> sourceConstructor = SourceClass.class.getDeclaredConstructor();sourceConstructor.setAccessible(true);SourceClass source = sourceConstructor.newInstance();
SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass target1 = new TargetClass("test1", 2);TargetClass target2 = new TargetClass("test2", 1);
TargetClass result = nested.process(target1, target2);assertEquals("test1_processed_depth_2", result.getData());}}