package same;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import other.DiffPackageOthers;
interface Source {Target sourceTargetField = new Target();
static class SourceStaticNested {public static String staticMethod(String param) {return "static_" + param;}}
class SourceInner {protected List<String> recursiveMethod(int depth) {private void privateWhileInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();int count = 0;while (count < 1) {diffObj.setDiffField(SourceStaticNested.staticMethod("diff_param"));if (diffObj.getDiffField() != null) {break;}count++;}}privateWhileInDiff();
List<String> result = new ArrayList<>();if (depth <= 0) {Object var = sourceTargetField;Target.TargetInner.TargetInnerRec targetRec = sourceTargetField.new TargetInner().new TargetInnerRec();result.add(targetRec.recInnerMethod("base_case"));return result;}
try {Method innerRecMethod = Target.TargetInner.TargetInnerRec.class.getMethod("recInnerMethod", String.class);Object targetInner = Target.class.getDeclaredConstructor().newInstance();Object targetRec = ((Target.TargetInner) targetInner.getClass().getDeclaredConstructor(Target.class).newInstance(sourceTargetField)).new TargetInnerRec();String reflectedResult = (String) innerRecMethod.invoke(targetRec, "reflection_" + depth);result.add(reflectedResult);} catch (Exception e) {e.printStackTrace();}
SourceStaticNested rawNested = new SourceStaticNested();result.add(rawNested.staticMethod("rec_" + depth));result.addAll(recursiveMethod(depth - 1));return result;}}
default Object callSourceMethod() {SourceInner inner = new SourceInner();return inner.recursiveMethod(2).get(0);}}
protected interface Target {
class TargetInner {
class TargetInnerRec {
public String recInnerMethod (String param) {
return "target_rec_" + param;
}
}
}
}
package other;
public class DiffPackageOthers {private String diffField;
public String getDiffField() {return diffField;}
public void setDiffField(String diffField) {this.diffField = diffField;}}
package same;
import org.junit.Test;import java.util.List;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testRecursiveMethod() {Source.SourceInner inner = new Source.SourceInner();List<String> result = inner.recursiveMethod(2);
assertNotNull(result);assertTrue(result.contains("target_rec_reflection_2"));assertTrue(result.contains("static_rec_2"));assertTrue(result.contains("target_rec_base_case"));assertEquals(4, result.size());}
@Testpublic void testCallSourceMethod() {Source source = new Source() {};Object result = source.callSourceMethod();
assertNotNull(result);assertTrue(result.toString().contains("target_rec_reflection_2"));}}