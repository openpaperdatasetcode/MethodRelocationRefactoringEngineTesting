package test;
protected class SourceClass {protected int sourceField;static class StaticNested {private TargetClass targetParam;private int nestedField;
private Object varargsMethod(String... args) {LocalInner local = new LocalInner();this.nestedField = 42;targetParam.targetField = 100;return local.process(args);}
class LocalInner {Object process(String... items) {return items.length;}}}}
abstract class TargetClass {int targetField;class MemberInner {}}