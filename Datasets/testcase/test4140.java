package test;
interface SomeInterface {}
protected class SourceClass implements SomeInterface {@Overridepublic TargetClass process(TargetClass... targets) {private TargetClass first = targets.length > 0 ? targets[0] : new TargetClass();private int val = first.field1 + first.field2;
do {val--;Object result = TargetClass.calculate(val > 0 ? val : 0);} while (val > 0);
super.toString();return SourceClass.this.helper(first);}
public abstract Object helper(TargetClass target);}
public class TargetClass {int field1;int field2;
public static abstract Object calculate(int num);}
