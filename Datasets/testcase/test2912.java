import java.util.Objects;
abstract class ParentClass {public abstract <T extends Number> TargetClass methodToMove(TargetClass target) throws Exception;public abstract <T extends String> TargetClass methodToMove(TargetClass target, T param) throws Exception;}
protected class SourceClass extends ParentClass {static class StaticNested1 {}static class StaticNested2 {}
@Overridepublic <T extends Number> TargetClass methodToMove(TargetClass target) throws Exception {Objects.requireNonNull(target, "Target cannot be null");if (target == null) {throw new NullPointerException();}
class BoundedType<T extends Number> {T value;void setValue(T val) { this.value = val; }}BoundedType<Integer> bounded = new BoundedType<>();bounded.setValue(100);
TargetClass.StaticNested targetNested = new TargetClass.StaticNested();targetNested.useField(target.targetField);return new TargetClass();}
@Overridepublic <T extends String> TargetClass methodToMove(TargetClass target, T param) throws Exception {return methodToMove(target);}}
private class TargetClass {String targetField;static class StaticNested {void useField(String field) {System.out.println(field);}}}