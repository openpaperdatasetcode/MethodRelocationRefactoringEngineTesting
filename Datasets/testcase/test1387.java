package test;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;
private class SourceClass {private TargetClass target = new TargetClass();
static class StaticNested {}
class MemberInner {}
/**
Method Javadoc
@param args varargs parameters
@return list of strings*/protected <T> List<String> methodToMove(T... args) {// source contains target's field (per_condition)Object fieldVal = target.targetField;
// super constructor invocationStaticNested nested = new StaticNested() {};
// super keywordssuper.toString();
// variable callTargetClass.StaticNested targetNested = new TargetClass.StaticNested();targetNested.doSomething();
// numbers:1, modifier:protected, exp:PatternPattern pattern = Pattern.compile(".", 1);
return new ArrayList<>();}}
class TargetClass {Object targetField;
// static nested class (target_feature)static class StaticNested {protected void doSomething() {}}}