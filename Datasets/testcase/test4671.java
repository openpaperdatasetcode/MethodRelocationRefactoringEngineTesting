package test;
import java.util.function.Consumer;
non-sealed record SourceRecord(int value) {public static class StaticNested {}
private final Consumer<Integer> anonymous = new Consumer<>() {@Overridepublic void accept(Integer i) {}};
public int varargsMethod(TargetRecord... targets) {StaticNested nested = new StaticNested();TargetRecord rawTarget;int result = 0;
for (TargetRecord target : targets) {rawTarget = target;synchronized (this) {switch (target.field()) {case 1:result += value;break;case 2:result -= value;break;default:result = 0;}}result += rawTarget.field();}
return result;}}
public record TargetRecord(int field) {}
