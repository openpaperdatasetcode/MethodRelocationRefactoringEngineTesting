package test;
protected class SourceClass<T extends Number> {static class StaticNested {}class MemberInner {}
class InnerRec {protected Object recursiveMethod(TargetClass.InnerRec param, int depth) throws Exception {class ParentOfLocal {protected int parentField;ParentOfLocal(int val) { this.parentField = val; }}
class LocalClass extends ParentOfLocal {LocalClass() {super(3);int superVal = super.parentField;}}
LocalClass local = new LocalClass();Object var = param.targetField;
if (depth <= 0) {return var;}return recursiveMethod(param, depth - 1);}}}
class TargetClass {static class Nested {}class InnerRec {Object targetField;}}