package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
protected class TargetClass extends SuperClass {public int targetField;
protected Object targetAccessor() {return super.superField + 1;}}
class SuperClass {protected int superField = 10;}
public class SourceClass {protected TargetClass targetField;private static String staticField = "static";
static class StaticNested1 {}static class StaticNested2 {}
@CustomAnnotationprotected List<String> sourceMethod() {class LocalType {String value;LocalType(String v) { value = v; }}
LocalType localVar = new LocalType(staticField);Object varCall = localVar.value;
switch (targetField.targetField) {case 1:varCall = super.superField;break;case 2:varCall = 1;break;default:break;}
try {Object accessorResult = targetField.targetAccessor();} catch (Exception e) {throw new RuntimeException(e);}
@CustomAnnotationList<String> result = new ArrayList<>();result.add((String) varCall);return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3006 {@Testpublic void testMoveMethod() {SourceClass source = new SourceClass();source.targetField = new TargetClass();source.targetField.targetField = 1;List<String> result = source.sourceMethod();assertNotNull(result);}}