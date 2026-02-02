package test;
import java.lang.reflect.Method;
abstract enum ParentEnum {PARENT_INSTANCE;protected abstract ParentEnum overriddenMethod() throws Exception;}
enum SourceEnum extends ParentEnum {SOURCE_INSTANCE;
class MemberInner extends ParentEnum {class NestedInner {@Overrideprivate NonSealedTargetEnum overriddenMethod() throws Exception {super();NonSealedTargetEnum target = NonSealedTargetEnum.TARGET_INSTANCE;
protected String [] arr1 = new String [5];protected int [] arr2 = new int []{1, 2, 3};
class LocalInner {void useOuterThis () {SourceEnum.this.toString ();}}new LocalInner ().useOuterThis ();
if (target != null) {variableCall (target);
Method reflectMethod = NonSealedTargetEnum.class.getDeclaredMethod ("targetMethod");reflectMethod.invoke (target);}
return target;}
private void variableCall(NonSealedTargetEnum target) {target.targetMethod();}}}
@Overrideprotected ParentEnum overriddenMethod() throws Exception {return new MemberInner().new NestedInner().overriddenMethod();}}
non-sealed enum NonSealedTargetEnum extends ParentEnum {TARGET_INSTANCE;
void targetMethod () {
class TargetLocalInner {void print () {}}new TargetLocalInner ().print ();}
@Overrideprotected ParentEnum overriddenMethod() throws Exception {return this;}}
