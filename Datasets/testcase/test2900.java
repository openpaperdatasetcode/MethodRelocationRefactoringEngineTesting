import java.lang.reflect.Method;
sealed class ParentPermitsClass permits SourceClass {}
final class SourceClass extends ParentPermitsClass {class MemberInner {class InnerRec {protected int methodToMove(TargetClass target) {private class VarDeclHelper {void declare() {TargetSubclass sub = new TargetSubclass();int superField = sub.superField;assert superField == 1;}}new VarDeclHelper().declare();
class TypeDecl {};TypeDecl typeInst = new TypeDecl();;
protected String nameExp = "NameExpression";TargetClass result = (target != null) ? target.getAccessor() : new TargetClass().getAccessor();
try {Method method = TargetClass.StaticNested.class.getMethod("accessorMethod");} catch (NoSuchMethodException e) {}
return target.getStaticField();}}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new MemberInner().new InnerRec().methodToMove(new TargetClass());}};}
public class TargetClass {static class StaticNested {public void accessorMethod() {}}
int staticField;public TargetClass getAccessor() {return new TargetClass();}
public int getStaticField() {return staticField;}}
class TargetSubclass extends TargetClass {int superField = 1;}