package test;
strictfpabstract class SourceClass<T> {private TargetClass targetField = new TargetClass();
{new Runnable() {};new Cloneable() {};}
protected Object getTargetField() {TargetClass.MemberInner inner = new TargetClass.MemberInner();Object var = targetField.field;inner.setValue(var);return targetField.field;}}
private abstract class TargetClass {Object field;
class MemberInner {void setValue(Object val) {TargetClass.this.field = val;}}}