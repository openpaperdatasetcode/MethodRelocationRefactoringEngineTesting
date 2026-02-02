package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class SourceClass {private String outerPrivateField = "private_value";private static int staticField = 100;
static class StaticNested {}
class InnerClass {class InnerRec {@MethodAnnotation // has_annotationprivate List<String> process(TargetClass target) {List<String> result = new ArrayList<>();
// uses_outer_thisresult.add(SourceClass.this.outerPrivateField);// access_outer_privateresult.add(outerPrivateField);// depends_on_static_fieldresult.add(String.valueOf(SourceClass.staticField));
variableCall(target);target.innerClass.helper();
// local inner classclass LocalInner {void addToResult() {result.add(target.publicField);result.add(String.valueOf(SourceClass.staticField));}}new LocalInner().addToResult();new StaticNested();
return result;}
private void variableCall(TargetClass target) {target.publicMethod();}}}}
package target;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public String publicField = "target_public";
class MemberInner {public void helper() {}}
public MemberInner innerClass = new MemberInner();
public void publicMethod() {}
public List<String> process() {return new ArrayList<>() {{ add(publicField); }};}}