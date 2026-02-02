package test;
class ParentSource {protected int parentField = 5;}
public class SourceClass extends ParentSource {public static class StaticNested {public static int formatValue(int value) {return value * 2;}}
public class MemberInner {public class DeepInner {private int overloadedMethod(TargetClass target) {target.compute();int value = target.field;expressionStatement: System.out.println("Processed value: " + value);return StaticNested.formatValue(value);}
private int overloadedMethod(TargetClass target, int multiplier) {TargetClass rawTarget = new TargetClass();int baseValue = overloadedMethod(rawTarget);return baseValue * multiplier;}}}
public int triggerRefactorMethod(TargetClass target) {return new MemberInner().new DeepInner().overloadedMethod(target);}
public int triggerRefactorMethod(TargetClass target, int multiplier) {return new MemberInner().new DeepInner().overloadedMethod(target, multiplier);}}
class TargetClass {int field;
public TargetClass() {this.field = 1;}
public TargetClass(int field) {this.field = field;}
public void compute() {class LocalInner {void updateField() {field += 3;}}new LocalInner().updateField();}}