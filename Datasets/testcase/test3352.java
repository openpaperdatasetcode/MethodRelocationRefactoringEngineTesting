package test;
private class SourceClass extends ParentClass {public static class StaticNested {}
class MemberInner {class SourceInnerRec {public Object moveMethod (TargetClass target, String... args) {TargetClass.TargetInner inner = target.new TargetInner ();
for (int i = 0; i < args.length; i++) {
if (args [i].isEmpty ()) {continue;}
String processed = inner.process (args [i] + SourceClass.this.getOuterData ());System.out.println (processed);}
super.parentMethod ();//return this;return this;}
public Object moveMethod (TargetClass target, int... nums) {TargetClass.TargetInner inner = target.new TargetInner ();for (int num : nums) {if (num < 0) {continue;}String processed = inner.process (String.valueOf (num) + SourceClass.this.getOuterData ());System.out.println (processed);}super.parentMethod ();return this;}}}
private String getOuterData() {return "outer";}
@Overrideprotected String callMethod (int type) {switch (type) {case 1:return TargetClass.TargetInner.staticM1 ().m2 ().m3 ();case 2:return TargetClass.TargetInner.staticM1 ().m2 ().m3 ().toUpperCase ();default:return TargetClass.TargetInner.staticM1 ().m2 ().m3 ().toLowerCase ();}}}
abstract class ParentClass {protected abstract String callMethod(int type);
protected void parentMethod() {}}
/**
Javadoc: Target class with local inner class and nested structure*/class TargetClass {class TargetInner {public TargetClass.TargetInner process (String input) {
class LocalInner {}new LocalInner ();
this.log (input);return this;}
private void log(String msg) {}
protected static TargetInner staticM1 () {return new TargetClass ().new TargetInner ();}
protected TargetInner m2() {return this;}
protected String m3() {return "chainedResult";}}}