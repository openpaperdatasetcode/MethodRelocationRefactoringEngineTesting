package test;
import java.util.List;import java.util.ArrayList;
protected abstract class SourceClass {protected int outerProtected = 10;
class MemberInner {}static class StaticNested {}
abstract void method(List<TargetClass> targetList);
protected TargetClass normalMethod(TargetClass t1, TargetClass t2, TargetClass t3) {synchronized (this) {for (TargetClass target : List.of(t1, t2, t3)) {target.memberInner.new InnerMethodClass().innerMethod();target.field += outerProtected;}return t1;}}
{method = (targetList) -> {List<String> result = targetList.stream().map(target -> target.memberInner.new InnerMethodClass().innerMethod()).toList();TargetClass tc = normalMethod(targetList.get(0), targetList.get(1), targetList.get(2));};}}
class TargetClass {int field = 5;MemberInner memberInner = new MemberInner();
class MemberInner {private List<String> innerMethod() {return new ArrayList<>(List.of(String.valueOf(TargetClass.this.field)));}
class InnerMethodClass {private List<String> innerMethod() {return MemberInner.this.innerMethod();}}}}