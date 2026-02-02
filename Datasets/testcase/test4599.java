package test;
import java.util.List;
protected class Source<T> {Target<String> targetField;
static class Nested1 {static class Nested2 {public Nested2() {int var = 1;switch (var) {case 1:Nested1 nested1 = new Nested1();int val = nested1.field;break;default:break;}}}}
static class Nested1 {int field;}
strictfp List<String> callMethod() {Source<Integer> source = new Source<>();Nested1.Nested2 inner = source.new Nested1().new Nested2();return null;}
strictfp List<String> callMethod(int param) {return null;}}
class Target {
static class TargetNested {}
}