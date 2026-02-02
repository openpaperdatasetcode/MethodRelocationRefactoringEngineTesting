package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface BaseEnum {}
public enum TargetEnum implements BaseEnum {VALUE1("one"),VALUE2("two");
private final String data;
TargetEnum(String data) {this.data = data;}
class TargetInner {class NestedInner {private int count;
public NestedInner(int count) {this.count = count;}
public String getCombined() {return data + "_" + count;}}}
public String getData() {return data;}}
non-sealed enum SourceEnum {INSTANCE;
class SourceInner {public final Object process(TargetEnum.TargetInner.NestedInner... inners) {// Type declaration statementList<String> results = new ArrayList<>();Object varCall = null;
// Constructor invocationTargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInner inner = target.new TargetInner();TargetEnum.TargetInner.NestedInner defaultNested = inner.new NestedInner(0);
// For statementfor (TargetEnum.TargetInner.NestedInner nested : inners) {if (nested == null) {varCall = defaultNested.getCombined();continue;}results.add(nested.getCombined());}
// Overload exists demonstrationprocess(results);
// Exception handling with source method calltry {varCall = SourceEnum.INSTANCE.new SourceInner().processWithReflection(inners[0]);} catch (Exception e) {results.add("error");}
// Return statementif (results.isEmpty()) {return defaultNested.getCombined();}return results;}
private List<String> process(List<String> items) {// Overloaded methodreturn new ArrayList<>(items);}
private Object processWithReflection(TargetEnum.TargetInner.NestedInner nested) throws Exception {// Used by reflectionMethod method = TargetEnum.TargetInner.NestedInner.class.getMethod("getCombined");return method.invoke(nested);}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3119 {@Testpublic void testVarargsMethod() {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();TargetEnum target = TargetEnum.VALUE2;TargetEnum.TargetInner targetInner = target.new TargetInner();TargetEnum.TargetInner.NestedInner nested1 = targetInner.new NestedInner(1);TargetEnum.TargetInner.NestedInner nested2 = targetInner.new NestedInner(2);
Object result = inner.process(nested1, nested2);assertTrue(result instanceof List);assertEquals(2, ((List<?>) result).size());}}