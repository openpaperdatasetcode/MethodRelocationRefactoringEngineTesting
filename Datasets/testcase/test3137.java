package test;
import other.DiffPackageClass;
final class TargetClass {int targetField; // target_feature: empty}
package other;
import test.TargetClass;
public class DiffPackageClass {static class ClassName {static int field;}
public static void throwWithField(TargetClass target) {ClassName.field = 3;if (ClassName.field == 3) {throw new IllegalArgumentException("Field value is 3"); // ThrowStatement with ClassName.field = 3}}}
package test;
public class SourceClass {private String outerPrivateField = "private_val";static int staticField = 10; // static field for dependency
class MemberInner {}
public void createLocalInner() {class LocalInner {} // local inner classnew LocalInner();}
class SourceInner {record SourceInnerRec() {} // source_inner_rec
abstract class InnerAbstract {protected abstract int abstractMethod();}
class InnerConcrete extends InnerAbstract {@Overrideprivate int abstractMethod() {return 1; // 1 as required (method_feature)}}
// Static code blocks with overriding method callstatic {SourceInner inner = new SourceClass().new SourceInner();InnerConcrete concrete = inner.new InnerConcrete();int overrideResult = concrete.abstractMethod(); // super.methodName() (implicit super call in override)}
protected int methodToMove(TargetClass target, SourceInnerRec rec) {// Variable callint var = target.targetField;
// Access outer privateString privateVal = SourceClass.this.outerPrivateField;
// Depends on static fieldvar += SourceClass.staticField;
// Call diff_package_others ThrowStatementtry {DiffPackageClass.throwWithField(target);} catch (IllegalArgumentException e) {var += 3; // Handle exception, no new exception thrown}
// Call_method in expression: this.methodName(arguments)String callResult = this.innerNormalMethod();
return var + callResult.length();}
// Call_method: normal inner class methodprotected String innerNormalMethod() {return "inner_result";}}}