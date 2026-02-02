package source;
import target.TargetInterface;import java.util.List;
interface SourceInterface {
public int varargsMethod (String... args) {typeDeclaration: {class LocalTypeDecl {String getPrivateData () {return outerPrivateField;}}LocalTypeDecl localType = new LocalTypeDecl ();
int result = 0;for (String arg : args) {switch (arg) {case "target":try {TargetInterface.MemberInner targetInner = new TargetInterface.MemberInner();result = TargetInterface.STATIC_FIELD;String var = targetInner.getTargetData();result += var.length();} catch (Exception e) {result = -1;}break;case "source":result = localType.getPrivateData().length();break;default:result = arg.length();break;}}return result;}}}
static class StaticNested {
public static TargetInterface.MemberInner getTargetInner () {
return new TargetInterface.MemberInner ();
}
}
}
package target;
interface TargetInterface {
int STATIC_FIELD = 1;
class MemberInner {
public String getTargetData () {
return "target_inner_data";
}
}
}