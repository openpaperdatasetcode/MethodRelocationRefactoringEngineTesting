package test;
public abstract class SourceClass {protected TargetClass targetField;
public class MemberInner {public TargetClass recursiveMethod(int count) {if (count <= 0) {return new TargetClass.StaticNested();}
class LocalInner<T extends Number> {T value;LocalInner(T val) { this.value = val; }}LocalInner<Integer> local = new LocalInner<>(count);int var = local.value;
targetField.process(this);return recursiveMethod(var - 1);}}}
public abstract class TargetClass {public static class StaticNested extends TargetClass {public TargetClass recursiveMethod(int count) {if (count <= 0) {return this;}return recursiveMethod(count - 1);}}
public void process(SourceClass source) {}}