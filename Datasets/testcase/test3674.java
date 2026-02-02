package test;
protected enum SourceEnum {INSTANCE;
public static class StaticNestedSource {public static TargetEnum.StaticNestedTarget createTargetNested() {return new TargetEnum.StaticNestedTarget();}}
public class InnerSource {public Object overloadedMethod(TargetEnum target) {TargetEnum.StaticNestedTarget nested1 = new TargetEnum.StaticNestedTarget();TargetEnum.StaticNestedTarget nested2 = StaticNestedSource.createTargetNested();
nested1.field = SourceEnum.this.name().length();nested2.field = target.name().length();
; // Empty statement
new Runnable() {@Overridepublic void run() {nested1.field += 2;}}.run();
return nested1.field + nested2.field;}
public Object overloadedMethod(TargetEnum.StaticNestedTarget nested) {TargetEnum.StaticNestedTarget newNested = new TargetEnum.StaticNestedTarget();newNested.field = nested.field + SourceEnum.this.ordinal();
; // Empty statement
return newNested.field;}}}
enum TargetEnum {VALUE1, VALUE2;
public static class StaticNestedTarget {public int field;
public StaticNestedTarget() {this.field = 0;}}}