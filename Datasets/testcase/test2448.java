package test.refactoring.movemethod;
import other.OtherPackageProcessor;
interface EnumExtension {}
sealed enum BaseEnum permits SourceEnum {}
protected enum TargetEnum {VALUE1(10),VALUE2(20);
public static class TargetNested {class NestedInner {public String data;
public NestedInner(String data) {this.data = data;}}}
protected int value;
TargetEnum(int value) {this.value = value;}}
non-sealed enum SourceEnum extends BaseEnum implements EnumExtension {INSTANCE;
protected String outerProtected = "protected_value";
static class SourceStaticNested {}
class SourceMemberInner {}
private Object process(TargetEnum.TargetNested.NestedInner nested) {// Expression statement (first occurrence)Object varCall = nested.data;
// Access outer protectedString combined = outerProtected + nested.data;
// Continue statement in loopint count = 0;for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}count++;}
// ExpressionStatement with obj.field in different packageOtherPackageProcessor.process(nested);
return combined + "_" + count;}}
package other;
import test.refactoring.movemethod.TargetEnum;
public class OtherPackageProcessor {public static void process(TargetEnum.TargetNested.NestedInner nested) {// Expression statement accessing obj.fieldnested.data = nested.data + "_processed";}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3139 {@Testpublic void testInstanceMethod() {SourceEnum source = SourceEnum.INSTANCE;TargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetNested nested = new TargetEnum.TargetNested();TargetEnum.TargetNested.NestedInner inner = nested.new NestedInner("test");
try {java.lang.reflect.Method method = SourceEnum.class.getDeclaredMethod("process", TargetEnum.TargetNested.NestedInner.class);method.setAccessible(true);Object result = method.invoke(source, inner);assertEquals("protected_value_test_processed_2", result);} catch (Exception e) {fail("Test failed: " + e.getMessage());}}}