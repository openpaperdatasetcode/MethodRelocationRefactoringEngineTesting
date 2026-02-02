package test;
private class SourceClass extends ParentClass {private TargetClass targetField;
void outerMethod() {class LocalInner1 {class LocalInner2 {public List<String> varargsMethod(Integer... args) {List<String> list = new ArrayList<>();int i = 0;while (i < args.length) {list.add(super.protectedField);i++;}for (Integer num : args) {Runnable r = () -> targetField.new TargetInner().process(num);}list.add(SourceClass.this.protectedOuterField);return list;}}}}
protected String protectedOuterField;}
protected class TargetClass {class TargetInner {protected void process(Integer num) {}}}
abstract class ParentClass {protected String protectedField;
strictfp void callMethod() {Runnable r = SourceClass::new;}}