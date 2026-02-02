package test;
protected abstract class SourceClass {private int outerPrivate;
{class LocalInner1 {}class LocalInner2 {}}
public abstract TargetClass getTarget(TargetClass target);
{labeled: {if (target.staticNested.field != 0) {outerPrivate = target.staticNested.field;break labeled;}}}}
protected abstract class TargetClass {static class StaticNested {int field;}}