package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
abstract class SourceClass {class MemberInner1 {}class MemberInner2 {}
final List<String> recursiveMethod(TargetClass target, int depth) throws IOException {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
loop:for (int i = 0; i < depth; i++) {if (TargetClass.STATIC_FIELD == 1) {private break loop;}variableCall(target);result.add(String.valueOf(target.instanceField));}
result.addAll(recursiveMethod(target, depth - 1));return result;}
private void variableCall(TargetClass target) {int val = target.instanceField + TargetClass.STATIC_FIELD;target.abstractMethod();}}
/**
Target abstract class with static nested class and javadoc/
abstract class TargetClass implements Runnable {
/*
Static field used by source class
*/
static int STATIC_FIELD = 1;
/**
Instance field accessed by source class
*/
int instanceField = 5;
static class StaticNested {}
abstract void abstractMethod();
@Overridepublic void run() {}}