package test;
protected enum SourceEnum {VALUE1, VALUE2;
private int field1;private String field2;private boolean field3;
private int instanceMethod(TargetEnum targetParam) {class LocalInner1 {}class LocalInner2 {}
LocalInner1 inner1 = new LocalInner1();LocalInner2 inner2 = new LocalInner2();
this.field1 = 10;this.field2 = "enumField";this.field3 = true;
targetParam.staticNested.process();int result = this.field1 + this.field2.length();SourceEnum.this.field3 = false;
return result;}}
public enum TargetEnum {ITEM_A, ITEM_B;
static class StaticNested {void process() {}}}