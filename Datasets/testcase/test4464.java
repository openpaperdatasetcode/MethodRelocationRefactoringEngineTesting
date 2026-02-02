package test.source;
public @interface SourceClass {protected int field = 10;
static class StaticNested {class MemberInner {protected int method(int a) {if (a > 0) {return TargetClass.method(5, field);} else {return super.hashCode();}}
protected TargetClass method(String... args) {switch (args.length) {case 0:return new TargetClass() {};default:int val = field;return TargetClass.method(val);}}}}}
package test.target;
public @interface TargetClass {default protected int method(int a) {return a;}
default protected TargetClass method(int... nums) {return new TargetClass() {};}}