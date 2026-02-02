package test;
public class SourceClass {TargetClass target = new TargetClass();
class InnerSource {@Deprecatedpublic TargetClass.InnerTarget() {;target.field = 0;switch (1) {case 1:continue;}}
Object method(int i) {return new Object();}
Object method(String s) {super.method(1);InnerSource inner = new InnerSource();return new Object() {};}}}
/**
Javadoc for TargetClass*/class TargetClass {int field;
class InnerTarget {void method(int x) {}}}