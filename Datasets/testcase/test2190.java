package test;
import java.util.List;
protected record SourceClass<T>(List<T> items) implements Runnable {static class StaticNested {}
@MyAnnotationprotected TargetClass<String> moveMethod(TargetClass<Integer>... targets) {class LocalInner {}
for (TargetClass<Integer> target : targets) {for (int val : target.values) {if (val < 0) {break;}target.inner.recursiveInner.process();}}
return new TargetClass<>("result");}
@Overridepublic void run() {}}
public record TargetClass(U data) {
int[] values = {1, 2, 3};
class Inner {RecursiveInner recursiveInner = new RecursiveInner();
class RecursiveInner {void process() {}}}}
@interface MyAnnotation {}