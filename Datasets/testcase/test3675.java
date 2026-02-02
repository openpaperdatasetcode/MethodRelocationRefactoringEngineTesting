package test;
import java.util.function.Supplier;
private enum SourceEnum {INSTANCE;
public static class StaticNestedSource {public static TargetEnum.StaticNestedTarget getTargetNested() {return new TargetEnum.StaticNestedTarget();}}
public class InnerSource {public void instanceMethod(TargetEnum target) {class LocalInner {TargetEnum.StaticNestedTarget createNested() {Supplier<TargetEnum.StaticNestedTarget> supplier = target::new StaticNestedTarget;return supplier.get();}}
LocalInner local = new LocalInner();TargetEnum.StaticNestedTarget nested = local.createNested();
assert nested != null : "StaticNestedTarget instance is null";assert nested.getCount() >= 0 : "Count cannot be negative";
nested.setCount(1);System.out.println(nested.getCount());
TargetEnum.StaticNestedTarget anotherNested = StaticNestedSource.getTargetNested();anotherNested.setCount(nested.getCount() * 2);}}
{new InnerSource().instanceMethod(TargetEnum.VALUE1);}}
protected enum TargetEnum {VALUE1, VALUE2;
public static class StaticNestedTarget {private int count;
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}}}