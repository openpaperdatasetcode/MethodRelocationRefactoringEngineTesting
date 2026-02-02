package sourcepkg;
import targetpkg.TargetClass;
private class SourceClass<T> {protected String outerProtectedField = "sourceProtected";
private TargetClass methodToMove(TargetClass target) {
class LocalInnerOne {}
class LocalInnerTwo {
void useOuter() {
SourceClass.this.outerProtectedField = "updated";
}
}
new LocalInnerTwo().useOuter();class TypeDecl {};TypeDecl typeInst = new TypeDecl();System.out.println(typeInst);;
super.toString();target.localInnerAction();return target;}}
package targetpkg;
public class TargetClass {
public void localInnerAction() {
class TargetLocalInner {
String innerField = "targetLocal";
}
new TargetLocalInner();
}
}