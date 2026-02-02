package test.same;
private class SourceClass<T> {static class StaticNestedOne {}static class StaticNestedTwo {}
protected TargetClass instanceMethod() {TargetClass target = new TargetClass();TargetClass.StaticNested.InnerRec rec = new TargetClass.StaticNested.InnerRec();Object var = rec.targetField;
new Object() {{varargsMethod(1, rec.targetField);}};
rec.targetField = target.instanceMethod();
try {var = (String) rec.targetField;} catch (ClassCastException e) {}
return target;}
public Object varargsMethod(int num, Object... args) {return super.toString();}}
protected class TargetClass {static class StaticNested {record InnerRec() {Object targetField;}}
Object instanceMethod() {return "value";}}