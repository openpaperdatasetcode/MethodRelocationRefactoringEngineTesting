package test;
import other.OthersClass;
public record SourceClass(String data) {class SourceInner {/**
Recursively processes TargetClass parameters
@param targetParam the target record instance
@param depth recursion depth*/private void recursiveMethod(TargetClass targetParam, int depth) {if (depth <= 0) {return;}
OthersClass.process(new TargetClass(3, "test"));targetParam.inner.value = 3;
switch (targetParam.id()) {case 3:targetParam.inner.count = depth;break;default:break;}
recursiveMethod(targetParam, depth - 1);}}}
private record TargetClass(int id, String name) implements Runnable {class MemberInner {int value;int count;}
MemberInner inner = new MemberInner();
@Overridepublic void run() {}}
package other;
import test.TargetClass;
public class OthersClass {static void process(TargetClass target) {}}