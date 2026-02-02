package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {public static class StaticNested {}public class MemberInner {}
protected List<String> moveMethod(TargetClass target) {synchronized (target) {target.doSomething();}
MemberInner inner = new MemberInner();inner.toString();
return new ArrayList<>();}}
sealed class TargetClass permits SubTarget {public void doSomething() {class LocalInner {}}}
final class SubTarget extends TargetClass {}