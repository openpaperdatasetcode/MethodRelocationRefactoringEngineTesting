package test;
abstract class SourceClass {static class StaticNested {}
class SourceInner {Object methodToMove(TargetClass.TargetInner inner) {new StaticNested();new Object() {};
int val = inner.targetField;OtherClass.process(this);
for (int i = 0; i < val; i++) {if (i % 2 == 0) continue;super.toString();}
Runnable r = (val > 5) ? () -> super.hashCode() : () -> {};r.run();
return inner;}}}
sealed abstract class TargetClass permits SubClass {static class TargetInner {int targetField;}}
final class SubClass extends TargetClass {}
class OtherClass {static void process(Object obj) {}}