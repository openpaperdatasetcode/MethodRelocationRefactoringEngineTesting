package test;
abstract class SourceClass permits SourceSubClass1, SourceSubClass2 {private String sourceInstanceField = "sourceInstanceVal";
class SourceMemberInner {}
private int varargsMethod(TargetClass... targets) {private int localVar = 1;
for (int i = 0; i < targets.length; i++) {if (targets[i] == null) {continue;}
String varCall = targets[i].targetField;localVar += varCall.length();localVar += sourceInstanceField.length();}
return localVar;}
void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(sourceInstanceField);}};}}
class SourceSubClass1 extends SourceClass {}class SourceSubClass2 extends SourceClass {}
private class TargetClass {String targetField = "targetInstanceVal";
static class TargetStaticNested {}}
