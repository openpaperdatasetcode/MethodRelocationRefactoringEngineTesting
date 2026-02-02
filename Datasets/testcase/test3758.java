package test;
class SourceClass {private String outerPrivateField = "sourcePrivateData";
public class MemberInner {}
public record SourceInnerRec() {public int recursiveMethod(TargetClass.TargetInner targetInner, int depth) {if (depth <= 0) {return targetInner.targetIntField;}
class InnerClass {public String field1 = targetInner.targetStrField;public int field2 = targetInner.targetIntField;}InnerClass innerObj = new InnerClass();
int var = innerObj.field2;var += outerPrivateField.length();
int count = 0;while (count < 1) {TargetClass target = new TargetClass().getTargetAccessor();var += target.targetInner.targetIntField;count++;}
return recursiveMethod(targetInner, depth - 1) + var;}}
public void sourceMethod() {class LocalInner {}LocalInner local = new LocalInner();}}
private class TargetClass {public TargetInner targetInner = new TargetInner();
public class TargetInner {public String targetStrField = "targetStr";public int targetIntField = 10;}
public TargetClass getTargetAccessor() {return this;}
public void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}
class OthersClass {public <T> void genericMethod(T param) {System.out.println(param);}
public void callInArrayInit(TargetClass.TargetInner targetInner) {Runnable[] runnables = {() -> OthersClass.InnerClass.callStaticMethod(targetInner)};}
public static class InnerClass {public static <T> void callStaticMethod(T target) {System.out.println(target);}}}