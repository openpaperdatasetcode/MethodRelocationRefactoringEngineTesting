package test;
/**
Javadoc for TargetClass
@param <T> type parameter*/class TargetClass<T> {{new Object() {}; // Anonymous inner class}
class TargetInner {class TargetInnerRec {T value;}}}
protected class SourceClass {private TargetClass<String> targetField = new TargetClass<>();
final void varargsMethod(TargetClass<String>.TargetInner.TargetInnerRec... params) {if (params.length == 0) {return; // Return statement}
// Super constructor invocationnew SourceClass() {{super();}};
try {// Variable callTargetClass<String>.TargetInner.TargetInnerRec first = params[0];String var = first.value;
if (var == null) {throw new IllegalArgumentException(); // Throw statement}} catch (IndexOutOfBoundsException e) {e.printStackTrace();}}}
