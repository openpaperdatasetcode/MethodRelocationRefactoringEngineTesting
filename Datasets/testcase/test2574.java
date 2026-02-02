package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
non-sealed class TargetClass {protected String outerProtectedField = "protected";
class TargetInner {class NestedInner {String getValue() {return outerProtectedField;}}}}
protected class SourceClass {private TargetClass targetField = new TargetClass();
protected List<String> sourceMethod() {class LocalInner1 {int count = 0;}
class LocalInner2 {List rawList = new ArrayList();}
LocalInner1 local1 = new LocalInner1();LocalInner2 local2 = new LocalInner2();Object varCall = local1.count;
TargetClass.TargetInner.NestedInner nested = targetField.new TargetInner().new NestedInner();
for (int i = 0; i < 5; i++) {local1.count++;if (i % 2 == 0) {continue;}local2.rawList.add(nested.getValue());; // empty statement}
if (local1.count > 3) {return (List<String>) local2.rawList;}
return new ArrayList<>();}
public String callMethod() {return ((Runnable) () -> TargetClass.InnerClass.methodName(targetField.new TargetInner())).toString();}
static class InnerClass {public static String methodName(TargetClass.TargetInner inner) {return inner.new NestedInner().getValue();}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3022 {@Testpublic void testMethod() {SourceClass source = new SourceClass();List<String> result = source.sourceMethod();assertNotNull(result);assertEquals("protected", source.callMethod());}}