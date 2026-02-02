package test;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
record SourceRecord(int data) {static class SourceStaticNested {}
class SourceInner {public int overloadedMethod(TargetRecord target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
int result = 0;switch (target.id()) {case 1:result = target.value();break;case 2:result = target.value() * 2;break;default:result = -1;}return result;}
public int overloadedMethod(TargetRecord target, Collection<String> bounds) {if (bounds == null || bounds.isEmpty()) {throw new IllegalArgumentException("Bounds cannot be empty");}return overloadedMethod(target) * bounds.size();}}}
public record TargetRecord(int id, int value) {static class TargetStaticNested {}}
strictfp class OthersClass {public List<String> callChainedMethods(SourceRecord source, TargetRecord target) {List<String> result = new ArrayList<>();SourceRecord.SourceInner inner = source.new SourceInner();
if (target.id() > 0) {int val = inner.overloadedMethod(target);result.add(String.valueOf(val));} else {List<String> bounds = new ArrayList<>();bounds.add("bound1");int val = inner.overloadedMethod(target, bounds);result.add(String.valueOf(val));}
return result.stream().map(String::toUpperCase).toList();}}