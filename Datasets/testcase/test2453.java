package test.refactoring.movemethod;
import java.lang.reflect.Constructor;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
public class TargetClass {public class TargetInner {private String content;
public TargetInner(String content) {this.content = content;// Local inner class in targetclass ContentValidator {boolean isValid() {return content != null && !content.isEmpty();}}if (!new ContentValidator().isValid()) {throw new IllegalArgumentException("Invalid content");}}
public String getContent() {return content;}}
protected TargetClass createInstance() {return new TargetClass();}}
class SourceClass {static class SourceStaticNested {}
Object process(TargetClass.TargetInner... inners) {// Requires try-catchList<String> results = new ArrayList<>();try {// Variable callfor (TargetClass.TargetInner inner : inners) {results.add(inner.getContent());}
// ExpressionMethodReference (2 occurrences)Function<TargetClass.TargetInner, String> getter = TargetClass.TargetInner::getContent;results.add(getter.apply(inners[0]));
Function<String, Integer> lengthFunc = String::length;results.add(lengthFunc.apply(inners[0].getContent()).toString());
// Used by reflectionMethod method = TargetClass.TargetInner.class.getMethod("getContent");results.add((String) method.invoke(inners[0]));
// Exception handling with source constructor and super method calltry {Constructor<TargetClass> constructor = TargetClass.class.getConstructor();TargetClass target = constructor.newInstance();TargetClass newTarget = target.createInstance(); // Calls super methodresults.add("Created: " + newTarget.getClass().getSimpleName());} catch (Exception e) {results.add("Reflection error: " + e.getMessage());}
// Override violationclass BadComparable implements Comparable {@Overridepublic int compareTo(Object o) { // Missing generic typereturn 0;}}
} catch (Exception e) {results.add("Error: " + e.getMessage());}
return results;}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3146 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass.TargetInner inner1 = target.new TargetInner("first");TargetClass.TargetInner inner2 = target.new TargetInner("second");
Object result = source.process(inner1, inner2);assertTrue(result instanceof List);assertEquals(6, ((List<?>) result).size());}}