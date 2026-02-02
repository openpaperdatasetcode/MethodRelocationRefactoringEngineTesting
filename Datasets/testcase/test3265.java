package sourcepkg;
import targetpkg.TargetClass;import java.util.List;import java.util.ArrayList;
private class SourceClass {private static String outerPrivateStaticField = "staticData";private String outerPrivateField = "instanceData";
static class FirstStaticNested {}static class SecondStaticNested {}
private List<String> normalMethod(TargetClass.TargetInnerRec targetParam) {FirstStaticNested nested1 = new FirstStaticNested();SecondStaticNested nested2 = new SecondStaticNested();
List<String> result = new ArrayList<>();privateForStatement(targetParam, result);
; // Empty statementtargetParam.doAction();result.add(outerPrivateField); // Access outer private fieldresult.add(SourceClass.outerPrivateStaticField); // Depends on static fieldresult.add(this.outerPrivateField); // Uses outer this
return result;}
private List<String> normalMethod(TargetClass.TargetInnerRec targetParam, int num) {return new ArrayList<>(); // Overload exists}
private void privateForStatement(TargetClass.TargetInnerRec target, List<String> list) {for (int i = 0; i < 1; i++) {list.add(target.field); // Access obj.fieldif (i == 0) return; // Return statement}}}
package targetpkg;
import sourcepkg.SourceClass;import java.util.List;
private class TargetClass {class TargetInnerRec {String field = "innerField";void doAction() {}}
private TargetInnerRec innerRec = new TargetInnerRec();}
package otherpkg;
import sourcepkg.SourceClass;import targetpkg.TargetClass;import java.util.List;
public class OtherClass {protected static int callMethod() {TargetClass target = new TargetClass();TargetClass.TargetInnerRec inner = target.new TargetInnerRec();SourceClass source = new SourceClass();
List<String>[] arr = new List[] {SourceClass.FirstStaticNested.class.getName(),source.normalMethod(inner)};
return arr.length;}}