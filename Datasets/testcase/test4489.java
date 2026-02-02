package test;
class ParentSourceClass<T> {protected int outerProtected = 10;public static final String STATIC_FIELD = "sourceStatic";
public abstract int overrideMethod(T target);}
final class SourceClass<T extends TargetClass<?>> extends ParentSourceClass<T> {class MemberInner {int helperMethod(T target) {return target.field + SourceClass.this.outerProtected;}}
@Overridepublic int overrideMethod(T target) {try {class LocalInner {int recursiveHelper(T target, int depth) {if (depth <= 0) {return 0;}variableCall(target);return helperMethod(target) + recursiveHelper(target, depth - 1);}}
LocalInner local = new LocalInner();RawType raw = new RawType();int[] arr = {local.recursiveHelper(target, 3), raw.process(target)};return arr[0] + arr[1];} catch (Exception e) {return -1;}}
private void variableCall(T target) {int val = target.field;TargetClass.MemberInner inner = target.new MemberInner();inner.useStaticField(ParentSourceClass.STATIC_FIELD);}
class RawType {int process(TargetClass<?> target) {return target.field;}}}
class ParentTargetClass<K> {protected int parentField = 5;}
class TargetClass<K> extends ParentTargetClass<K> {int field = 1;
class MemberInner {void useStaticField(String staticVal) {System.out.println(staticVal);}}}