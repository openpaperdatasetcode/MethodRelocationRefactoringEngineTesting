import java.util.List;
private class SourceClass<T> {class MemberInner {Object methodToMove (TargetClass target) {return processTarget (List.of (target));}
Object methodToMove (List<TargetClass> targets) {return processTarget(targets);}
private Object processTarget(List<TargetClass> targets) {if (targets == null || targets.isEmpty()) {throw new NullPointerException("Targets cannot be null or empty");}
for (TargetClass target : targets) { // EnhancedForStatementsynchronized (target) {if (target.field == 1) {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.useField(target.field);}}}return targets.get(0);}}
Runnable anonymous = new Runnable () {@Overridepublic void run () {new MemberInner ().methodToMove (new TargetClass ());}};}
public class TargetClass {int field;static class StaticNested {void useField(int field) {}}}