package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
sealed private class SourceClass<T> permits SourceSubClass {static class StaticNested {}
@RefactorAnnotation // has_annotationTargetClass<String> methodToMove(TargetClass<T>.TargetInnerRec... targets) {class LocalInner {}LocalInner local = new LocalInner();
// Variable call (contains target parameter, source has target's field)if (targets.length > 0) {TargetClass<T>.TargetInnerRec target = targets[0];int val = target.field;}
return new TargetClass<>();}}
final class SourceSubClass<T> extends SourceClass<T> {}
sealed class TargetClass<T> permits TargetSubClass<T> {class TargetInner {record TargetInnerRec(int field) {} // Target field used in source}}
final class TargetSubClass<T> extends TargetClass<T> {}