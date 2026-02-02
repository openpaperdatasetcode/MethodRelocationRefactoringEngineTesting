package test;
import java.util.List;import java.util.ArrayList;
class ParentClass {strictfp List<String> parentMethod(TargetClass target) {return new ArrayList<>();}}
protected class SourceClass extends ParentClass {static class StaticNested {}
{new Runnable() {};}
strictfp int overloadingMethod(TargetClass target) throws Exception {try {TargetClass obj = new TargetClass();List<String> list = finalMethod(target);variableCall = target.field;this.overloadingMethod(1);dependsOnInner = target.new Nested().innerField;} catch (Exception e) {throw e;}return variableCall;}
strictfp int overloadingMethod(int num) {return 0;}
private final List<String> finalMethod(TargetClass target) {return target.m1().m2().m3();}
int variableCall;int dependsOnInner;}
public class TargetClass {int field;
static class StaticNested {}
class Nested {int innerField;}
TargetClass m1() { return this; }TargetClass m2() { return this; }List<String> m3() { return new ArrayList<>(); }}
