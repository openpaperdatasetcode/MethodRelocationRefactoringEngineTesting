package test;
public class SourceClass extends ParentClass {public static class StaticNested1 {}public static class StaticNested2 {}
@Refactorfinal void moveMethod(TargetClass.TargetInner param) {labeled: {private int x = 0;switch (x) {case 1:param.value = 10;break labeled;default:break;}}
super();assert param.value > 0;if (param.value < 0) {throw new IllegalArgumentException();}param.doAction();}}
class ParentClass {}
private class TargetClass {public static class TargetInner {int value;void doAction() {}}}
@interface Refactor {}