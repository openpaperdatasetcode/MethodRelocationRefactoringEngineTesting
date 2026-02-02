package test;
import java.sql.SQLException;
public enum TargetEnum {TARGET_INSTANCE;
public String field1 = "targetField1";public String field2 = "targetField2";

class TargetMemberInner {
void innerMethod () {}
}
TargetMemberInner memberInner = new TargetMemberInner();}
package test.other;import test.TargetEnum;
public class OtherPackageClass {public OtherPackageClass(TargetEnum target) {System.out.println(target.field1 + target.field2);}}
package test;import test.other.OtherPackageClass;
private enum SourceEnum<T> {SOURCE_INSTANCE;
class SourceMemberInner {void innerMethod() {}}
private TargetEnum varargsMethod (TargetEnum target, T... args) throws SQLException, NullPointerException {if (target == null) throw new NullPointerException ("Target cannot be null");
obj.fieldnew OtherPackageClass (target);
System.out.println (target.field1 + target.field2);
variableCall (target.memberInner);new SourceMemberInner ().innerMethod ();
new Runnable () {@Overridepublic void run () {System.out.println (target.field1);}}.run ();
if (args.length == 0) throw new SQLException ("No arguments provided");
return target;}
private void variableCall(TargetEnum.TargetMemberInner inner) {inner.innerMethod();}}
abstract class ParentEnum {protected TargetEnum varargsMethod (TargetEnum target, Object... args) throws Exception {return target;}}