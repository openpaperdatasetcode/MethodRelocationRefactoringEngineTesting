import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class Container {private <T extends CharSequence> SourceClass<T> source = new SourceClass<>();
private class SourceClass<T extends CharSequence> {private T instanceField;
public static class StaticNested {private static <T extends CharSequence> void callInLambda(SourceClass<T> source, TargetClass target) {Consumer<TargetClass> lambda = t -> {MemberInner<T> inner = source.new MemberInner<>();inner.processTarget(t, 2);};lambda.accept(target);}}
public class MemberInner<T extends CharSequence> {public final List<String> processTarget(TargetClass target, int depth) throws IllegalArgumentException {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}if (target == null) {throw new NullPointerException("TargetClass cannot be null");}
for (int i = 0; i < depth; i++) {if (i == 1) {break;}
target.localData = "processed_" + i;variableCall(target);result.add(target.getLocalData());}
result.addAll(processTarget(target, depth - 1));return result;}
private void variableCall(TargetClass target) {target.updateLocalCount();}}
public void init(T data) {this.instanceField = data;TargetClass target = new TargetClass();StaticNested.callInLambda(this, target);}}}
protected class TargetClass {String localData;private int localCount;
public String getLocalData() {class LocalInner {String fetchData() {return localData;}}return new LocalInner().fetchData();}
public void updateLocalCount() {this.localCount++;}
public int getLocalCount() {return localCount;}}