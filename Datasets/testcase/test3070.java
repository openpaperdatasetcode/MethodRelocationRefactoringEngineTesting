package test;
import java.util.List;import java.util.ArrayList;
class TargetClass<T> implements Runnable {T targetField;
@Overridepublic void run() {class LocalInner {}}}
private sealed class SourceClass<T> permits SourceSubClass<T> {@RefactorTestpublic final List<String> methodToMove(TargetClass<T> target) {List<String> result = new ArrayList<>();TargetClass<T> newTarget = new TargetClass<>();
; // Empty statement
for (int i = 0; i < 3; i++) {T var = target.targetField;result.add(var.toString());}
newTarget.targetField = target.targetField;return result;}}
final class SourceSubClass<T> extends SourceClass<T> {}
@interface RefactorTest {}