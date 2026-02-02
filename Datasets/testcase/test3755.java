package test;
private class SourceClass {private String outerPrivateField = "sourcePrivateData";private static String sourceStaticField = "sourceStaticData";
public static class SourceStaticNested {}
public record SourceInnerRec() {protected TargetClass recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target;}
try {TargetClass.TargetStaticNested nestedObj = new TargetClass.TargetStaticNested() {@Overrideprotected int overrideMethod(int a, String b) {System.out.println(outerPrivateField);return a + b.length();}};
int result = nestedObj.overrideMethod(depth, sourceStaticField);variableCall(target.targetField, result);} catch (Exception e) {}
int count = 0;while (count < 2) {if (count == 1) {count++;continue;}expressionStatement(target);count++;}
return recursiveMethod(target, depth - 1);}
protected TargetClass recursiveMethod(TargetClass target) {return recursiveMethod(target, 1);}
private void variableCall(String targetField, int num) {System.out.println(targetField + num);}
private void expressionStatement(TargetClass target) {target.targetField += sourceStaticField;}}
{new Runnable() {@Overridepublic void run() {SourceStaticNested staticNested = new SourceStaticNested();}};}}
private class TargetClass {String targetField = "targetData";
public static class TargetStaticNested {protected int overrideMethod(int param1, String param2) {return param1 * param2.length();}}}