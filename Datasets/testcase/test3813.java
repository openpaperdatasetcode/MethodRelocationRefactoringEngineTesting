package samepkg;
import java.util.ArrayList;import java.util.List;
private class SourceClass<T extends Number> {static class StaticNested {}
class MemberInner {/**
Method javadoc*/private int varargsMethod(int baseParam, TargetClass<String>... targets) throws Exception {labeled: {class LocalType {}LocalType lt = new LocalType();
if (targets.length == 0) {break labeled;}
TargetClass<String> target = targets[0];int val = target.targetMethod();System.out.println(val);}return baseParam;}}
void methodWithLocal() {class LocalInner {}new LocalInner();}
default void callMethod() {List<SourceClass<?>> list = new ArrayList<>();list.stream().map(obj -> obj.new MemberInner().varargsMethod(0)).count();Object obj = new SourceClass<>();obj.new MemberInner().varargsMethod(1).toString().hashCode();}}
class TargetClass<V> {static class TargetStaticNested {}
int targetMethod() {return 0;}}