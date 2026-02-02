import java.lang.reflect.Method;
public class SourceClass {static class StaticNested {}class MemberInner {}
private TargetClass methodToMove(TargetClass target) {private class VarDeclHelper {void declare() {int fieldValue = TargetClass.StaticNested.STATIC_FIELD;assert fieldValue == 1;}}new VarDeclHelper().declare();
TargetClass.StaticNested nested = new TargetClass.StaticNested();MemberInner inner = new MemberInner();target = new TargetClass();super.toString();
try {Method method = TargetClass.class.getMethod("innerRecMethod");} catch (NoSuchMethodException e) {}
return target;}}
protected class TargetClass extends ParentClass {static class StaticNested {static final int STATIC_FIELD = 1;}
class InnerRec {void innerRecMethod() {}}
public TargetClass() {super();}}
class ParentClass {}
// Diff-package auxiliary class (simulated different package structure)package otherpkg;import samepkg.TargetClass;
public class OtherPackageClass {public void accessTargetField() {int value = TargetClass.StaticNested.STATIC_FIELD;}}