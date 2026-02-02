package test;
public class TargetClass {int targetField;class TargetInnerRec {}}
public class SourceClass {class MemberInner {}
public SourceClass() {Runnable r = new Runnable() {public void run() {}};}
public record SourceInnerRec() {private <T extends TargetClass> TargetClass methodToMove(T param) {private class PrivateInner {PrivateInner() {int val = param.targetField;param.targetField = 2;}}new PrivateInner();
try {protected TargetClass helperMethod() {this.methodToMove(param);return new TargetClass();}TargetClass result = helperMethod();} catch (Exception e) {}
TargetClass target = new TargetClass();char c1 = 'a', c2 = 'b', c3 = 'c';super.toString();target.targetField = c1;
TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();return target;}}
default String callMethod() {{SourceInnerRec rec = new SourceInnerRec();TargetClass target = new TargetClass();String str = target.toString().concat("").trim();rec.methodToMove(target);}return "";}}