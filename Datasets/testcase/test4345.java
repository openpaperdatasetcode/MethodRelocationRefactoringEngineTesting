package test;
import java.util.List;import java.util.ArrayList;
class SourceClass {static class StaticNested {protected int x;}
class MemberInner {protected int overrideMethod() throws NullPointerException {if (SourceClass.this == null) {throw new NullPointerException();}int a = 5;a++;TargetClass target = TargetClass.create(2, new OtherClass(), StaticNested.class, super.toString());for (int i = 0; i < 10; i++) {if (i == 3) {continue;}target.memberInnerField = i;}return a;}}}
/**
Javadoc for TargetClass*/public class TargetClass {int memberInnerField;
class TargetInner {void doSomething() {}}
public static TargetClass create(int num, OtherClass oc, Class<?> cls, String str) {return new TargetClass();}}
final class ParentClass {List<String> callMethod() {SourceClass sc = new SourceClass();SourceClass.MemberInner mi = sc.new MemberInner();return new ArrayList<String>() {{add(String.valueOf(mi.overrideMethod()));}};}}
class OtherClass {}