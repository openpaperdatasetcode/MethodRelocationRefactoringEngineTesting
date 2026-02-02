package same;
import java.util.ArrayList;import java.util.List;import other.DiffPackageOthers;
public class Source {protected Target sourceTargetField = new Target();protected String outerProtectedField = "source_protected_value";
class SourceMemberInner1 {}class SourceMemberInner2 {protected List<String> instanceMethod() {private void privateContinueInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();for (int i = 0; i < 1; i++) {if (diffObj.getSuperField() == null) {continue;}}}privateContinueInDiff();
class LocalType {Target targetField = sourceTargetField;String localVal = outerProtectedField;}LocalType local = new LocalType();
List<String> result = new ArrayList<>();int count = 0;while (count < 3) {String val1 = local.targetField::getInnerField;String val2 = local.targetField::getInnerField;String val3 = local.targetField::getInnerField;result.add(val1 + val2 + val3);count++;}
Object var = local.targetField;result.add(local.localVal);return result;}}}
protected class Target {private String innerField = "target_inner_value";
public String getInnerField() {class TargetLocalInner {String localInnerVal = innerField;}new TargetLocalInner();return innerField;}}
package other;
public class DiffPackageOthers {private Object superField = new Object();
public Object getSuperField() {return superField;}}
package same;
import org.junit.Test;import java.util.List;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testInstanceMethod() {Source source = new Source();Source.SourceMemberInner2 inner = source.new SourceMemberInner2();
List<String> result = inner.instanceMethod();
assertNotNull(result);assertEquals(4, result.size());assertTrue(result.get(0).contains("target_inner_value"));assertTrue(result.get(3).contains("source_protected_value"));}}