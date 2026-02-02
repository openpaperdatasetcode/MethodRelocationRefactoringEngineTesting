package test;
class SourceClass {public class InnerSource {public class NestedInner {protected void void normalMethod(TargetClass.InnerTarget param) {TargetClass.InnerTarget target = new TargetClass.InnerTarget(2);switch (param.field) {case 1:param.field = target.field;break;case 2:target = new TargetClass.InnerTarget(param);break;}overloadMethod(param);overloadMethod(target.field);}
protected void overloadMethod(TargetClass.InnerTarget t) {t.field++;}
protected void overloadMethod(int value) {new TargetClass.InnerTarget(value);}}}}
private class TargetClass extends ParentClass {public class InnerTarget {public int field;
public InnerTarget(int f) {this.field = f;}
public InnerTarget(TargetClass.InnerTarget other) {this.field = other.field;}}}
class ParentClass {}