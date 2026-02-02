package same.pkg;
interface SourceInterface extends TargetInterface<String> {TargetInterface<String> targetField = new TargetInterfaceImpl<>(); // Contains target field
static class SourceStaticNested1 {}static class SourceStaticNested2 {}
@Overrideprivate TargetInterface<String> overridingMethod() {variableCall();
// Instance code block with accessor method{Object parentValue = ParentClass.getAccessorValue(1);}
// 2 SuperMethodInvocation expressionsprotected Object superCall1 = super.getTargetValue();protected Object superCall2 = super.getTargetValue();
// For statementfor (int i = 0; i < 2; i++) {if (targetField == null) {throw new NullPointerException("Target field is null");}// Call sub_class method in if/elseint subResult = (i == 0)? TargetSubClass.TargetInner.method(1): TargetSubClass.TargetInner.method(2);}
return targetField;}
private void variableCall() {String localVar = targetField.getGenericValue();}}
interface TargetInterface<T> extends MyBaseInterface {T getGenericValue();
default Object getTargetValue() {return new Object();}
default TargetInterface<T> overridingMethod() {// Local inner class in target interfaceclass TargetLocalInner {void localMethod() {}}new TargetLocalInner().localMethod();return this;}}
interface MyBaseInterface {}
class ParentClass {private static Object getAccessorValue(int param) {return param;}}
class TargetSubClass implements TargetInterface<Integer> {private static class TargetInner {private static int method(int param) {return param * 2;}}
@Overridepublic Integer getGenericValue() {return 0;}
@Overridepublic TargetInterface<Integer> overridingMethod() {return this;}}
class TargetInterfaceImpl<T> implements TargetInterface<T> {@Overridepublic T getGenericValue() {return null;}}