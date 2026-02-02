package test;
class ParentClass {Object callInDoWhile(TargetClass target, String... args) {do {Runnable runnable = target::process;runnable.run();} while (args.length > 0);return null;}}
class SourceClass extends ParentClass {private int outerPrivate;public static class StaticNested {}
public class SourceInner {public class InnerRec {protected Object normalMethod(TargetClass target) {class LocalInner {}
private void tryBlock() {try {int val = TargetClass.staticField;} catch (Exception e) {}}tryBlock();
switch (target.field) {case 1: break;default: break;}
outerPrivate = 5;
StaticNested nested = new StaticNested();nested.toString();
return target;}}}}
private class TargetClass {int field;static int staticField;
void process() {}}
