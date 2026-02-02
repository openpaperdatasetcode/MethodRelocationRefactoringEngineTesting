package same;
public interface SomeInterface {}
package same;
public class SourceClass implements SomeInterface {public class InnerClass {/**
Returns sum of target field and value
@param target the target class instance
@return sum as int
*/
private int methodToMove(TargetClass target) {
int value = 5;
if (target.inner.field > 0) {
value += target.inner.field;
}
return value;
}
}
}
package same;
protected class TargetClass extends ParentClass {public MemberInner inner = new MemberInner();
public class MemberInner {public int field;}}
package same;
class ParentClass {}
