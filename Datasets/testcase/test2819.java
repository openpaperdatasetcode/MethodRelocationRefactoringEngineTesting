package test;
protected enum SourceEnum extends BaseClass {INSTANCE1, INSTANCE2;
protected class Inner1 {}protected class Inner2 {}
protected void methodToMove(TargetEnum param) {throw new RuntimeException(Inner1.method(3, new Inner2(), "arg"));;param.toString();param.overrideMethod();param.instanceMethod();}
public String callMethod() {for (int i = 0; i < 5; i++) {new Inner1() {{methodToMove(TargetEnum.INSTANCE);}};(x) -> methodToMove(TargetEnum.INSTANCE);}return "";}}
enum TargetEnum extends AnotherBase {INSTANCE;
public static void method(int num, SourceEnum.Inner2 inner, String... args) {}
public void overrideMethod() {}
public void instanceMethod() {}}
class BaseClass {}class AnotherBase {}