package test;
private class SourceClass {public static class StaticNested1 {}public static class StaticNested2 {}
class SourceInner {class SourceInnerRec {private void moveMethod(ProtectedTarget target) {class LocalType {}LocalType local = new LocalType();
target.staticNested.process("data1");target.instanceMethod();}
private void moveMethod(ProtectedTarget target, int num) {class LocalType {}LocalType local = new LocalType();
target.staticNested.process("data2:" + num);target.instanceMethod(num);}}}}
protected class ProtectedTarget {public static class StaticNested {public void process(String input) {}}
public void instanceMethod() {}public void instanceMethod(int num) {}}
