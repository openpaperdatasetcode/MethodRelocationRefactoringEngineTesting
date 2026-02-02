package test.same;
public class SourceClass extends ParentClass {static class StaticNested {}
class MemberInner {public Object instanceMethod(TargetClass target) {TargetClass.StaticNested inner = new TargetClass.StaticNested();Object var = inner.targetField;
try {TargetClass result = super.overrideMethod(1);var = result;} catch (Exception e) {throw new RuntimeException();}
return var;}}}
abstract class TargetClass {static class StaticNested {Object targetField;}}
class ParentClass {private TargetClass overrideMethod(int val) {return null;}}