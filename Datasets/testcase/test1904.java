package test;
private class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
private static TargetClass staticMethod() {return new TargetClass();}
protected void method(TargetClass targetParam) {class LocalType {}LocalType lt = new LocalType();
int i = 0;while (i < 3) {if (i == 1) {if (staticMethod() != null) {this.method(targetParam);} else {this.method(targetParam);}}i++;}
Runnable r1 = () -> System.out.println(targetParam.field);Runnable r2 = () -> { targetParam.field = 3; };
List rawList = new ArrayList();rawList.add(targetParam.field);
class Inner {private void innerMethod() {if (targetParam.field == 3) {return;}}}}}
class TargetClass {int field;
void someMethod() {class TargetLocalInner {}}}