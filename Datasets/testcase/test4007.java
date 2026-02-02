package test;
protected class SourceClass<T> permits SourceClass.SubSource<T> {private int sourceInstanceField = 5;private TargetClass<T>.TargetInner targetInnerField;
static class SourceNested1 {}static class SourceNested2 {}
public SourceClass() {this(null);}
public SourceClass(TargetClass<T> target) {super();if (target != null) {this.targetInnerField = target.new TargetInner(2);}}
class SourceInner {protected int instanceMethod(TargetClass<T> targetParam) {if (targetParam == null) {return 0;}
try {super.toString();targetParam.targetInstanceField = (T) String.valueOf(2);targetInnerField.innerField = targetParam.targetInstanceField;
return sourceInstanceField + Integer.parseInt(targetInnerField.innerField.toString());} catch (Exception e) {return -1;}}}
static class SubSource<T> extends SourceClass<T> {}}
private class TargetClass<T> {T targetInstanceField;
class TargetInner {T innerField;
public TargetInner(int initVal) {this.innerField = (T) String.valueOf(initVal);}}}