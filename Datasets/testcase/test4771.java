package test;
import java.util.ArrayList;import java.util.Collection;
abstract class SourceClass<T> {int overloadedMethod(TargetClass<T> target) {class LocalInner1 {}class LocalInner2 {}
TargetClass<T> newTarget = new TargetClass<>();int val = target.field;
loop: for (int i = 0; i < 5; i++) {if (i == val) break loop;}
SourceClass<T> source = new SourceClass<>() {};source.toString();
return val;}
String overloadedMethod(String str) {return str;}}
class TargetClass<T> {T field;
strictfp int callInCollection(SourceClass<T> source) {Collection<Integer> coll = new ArrayList<>();TargetClass<T> outer = new TargetClass<>();coll.add(outer.new Inner().compute());return coll.size();}
class Inner {int compute() {return 0;}}}