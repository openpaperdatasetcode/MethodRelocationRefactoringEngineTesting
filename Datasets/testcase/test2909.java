package samepkg;
import otherpkg.OtherPackageHelper;
class ParentGenericClass<T> {}
protected class SourceClass<T> extends ParentGenericClass<T> {class MemberInner {}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass<T>().getTargetAccessor(new TargetClass<>());}};
public Object getTargetAccessor(TargetClass<T> target) {private class IfStatementHelper {void checkTargetField() {if (target.field == 1) {OtherPackageHelper.accessTargetField(target);}}}new IfStatementHelper().checkTargetField();
try {TargetClass.StaticNested<T> nested = new TargetClass.StaticNested<>();nested.useField(target.field);return target.field;} catch (Exception e) {return null;}}}
private class TargetClass<T> {int field;
static class StaticNested {
void useField(int field) {
System.out.println("Static nested uses field: " + field);
}
}
}
// Different package auxiliary classpackage otherpkg;
import samepkg.TargetClass;
public class OtherPackageHelper {public static <T> void accessTargetField(TargetClass<T> target) {System.out.println("Diff-package access target field: " + target.field);}}