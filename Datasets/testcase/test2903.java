package samepkg;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
private class SourceClass {private String outerPrivateField = "privateValue";public static class StaticNestedSource {}public class MemberInnerSource {}
public class SourceInnerClass {
private TargetClass process (TargetClass targetParam) {
return process (targetParam, new String []{});
}
private TargetClass process (TargetClass targetParam, String... args) {TargetClass rawTarget = targetParam;
Class<?> innerRecType = TargetClass.InnerRec.class;
abstract class NameReference {abstract String getInnerName ();}NameReference nameRef = new NameReference () {@OverrideString getInnerName () {return innerRecType.getSimpleName ();}};
StaticNestedSource nested = new StaticNestedSource ();MemberInnerSource inner = new MemberInnerSource ();
String privateVal = SourceClass.this.outerPrivateField;
this.setInnerField (targetParam, privateVal);List<String> fieldList = this.getInnerFields(targetParam);
targetParam.innerStaticNested.process (fieldList);TargetClass.InnerRec rec = new TargetClass.InnerRec (1);rec.setValue (privateVal);
if (rawTarget == null) {throw new NullPointerException ("Target instance cannot be null");}
try {Method accessorMethod = SourceInnerClass.class.getDeclaredMethod ("getInnerFields", TargetClass.class);accessorMethod.setAccessible (true);fieldList = (List<String>) accessorMethod.invoke(this, targetParam);} catch (Exception e) {}
return targetParam;}
public void setInnerField (TargetClass target, String value) {target.targetField = value; }
public List<String> getInnerFields(TargetClass target) {List<String> fields = new ArrayList<>();fields.add(target.targetField);fields.add(this.getOuterPrivateValue());return fields;}
private String getOuterPrivateValue () {return SourceClass.this.outerPrivateField;}}}
package samepkg;
import java.util.List;
class TargetClass implements SomeInterface {public String targetField;
public static class InnerStaticNested {public void process (List<String> data) {}}
 record（target_inner_rec）public record InnerRec (int id) {private String value;
public void setValue(String val) {this.value = val;}
public String getValue() {return value;}}
public InnerStaticNested innerStaticNested = new InnerStaticNested();
@Overridepublic void interfaceMethod() {}}
interface SomeInterface {void interfaceMethod ();}