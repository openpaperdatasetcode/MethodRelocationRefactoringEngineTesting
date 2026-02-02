package test;
abstract class SourceGenericClass<T> permits ConcreteSourceClass {protected T sourceField;
record SourceInnerRec<S>(S data) {int varargsMethod(TargetGenericClass<T>.TargetStaticNested... targetParams) {label: {while (targetParams != null && targetParams.length > 0) {TargetGenericClass<T>.TargetStaticNested param = targetParams[0];param.doOperation();int value = param.getCount();
super();System.out.println(super.toString());
if (value > 5) break label;targetParams = new TargetGenericClass<T>.TargetStaticNested[0];}}return sourceField != null ? 1 : 0;}}}
class ConcreteSourceClass<T> extends SourceGenericClass<T> {}
abstract class TargetGenericClass {
protected U targetField;
static class TargetStaticNested {private int count = 3;
void doOperation() {}int getCount() { return count; }}}
