package test.refactoring.movemethod;
import other.DiffPackageHandler;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsMethodAnnot {}
public class TargetClass {public class TargetInner {public int field1;public String field2;
public TargetInner(int field1, String field2) {this.field1 = field1;this.field2 = field2;}
public TargetInner copy() {return new TargetInner(field1, field2);}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageHandler {public static void processInner(TargetClass.TargetInner inner) {// SwitchStatement with this.field (2 occurrences)switch (inner.field1) {case 1:inner.field2 = "one";break;case 2:inner.field2 = "two";break;default:inner.field2 = "other";}}}
package test.refactoring.movemethod;
import other.DiffPackageHandler;
abstract class SourceClass {@VarargsMethodAnnotprivate TargetClass.TargetInner process(TargetClass.TargetInner... inners) {if (inners == null || inners.length == 0) {// Constructor invocationreturn new TargetClass().new TargetInner(0, "default");}
// Variable callTargetClass.TargetInner first = inners[0];Object varCall = first.field1;
// If statementif (first.field1 > 5) {first = first.copy();first.field1 = 5;}
// Process in different package with SwitchStatement featureDiffPackageHandler.processInner(first);
return first;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3148 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass() {};TargetClass target = new TargetClass();TargetClass.TargetInner inner1 = target.new TargetInner(2, "test");TargetClass.TargetInner inner2 = target.new TargetInner(6, "large");
try {java.lang.reflect.Method method = SourceClass.class.getDeclaredMethod("process", TargetClass.TargetInner[].class);method.setAccessible(true);TargetClass.TargetInner result = (TargetClass.TargetInner) method.invoke(source, (Object) new TargetClass.TargetInner[]{inner1, inner2});
assertEquals(2, result.field1);assertEquals("two", result.field2);} catch (Exception e) {fail("Test failed: " + e.getMessage());}}}