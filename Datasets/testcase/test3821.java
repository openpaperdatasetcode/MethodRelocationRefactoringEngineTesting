package samepkg;
interface AnnotationInterface {}
@interface SourceAnnotation implements AnnotationInterface {static class StaticNested1 {private static void staticMethod() {}}static class StaticNested2 {}
public Object varargsMethod(TargetAnnotation... targets) {class GenericWithBounds<T extends Number> {}GenericWithBounds<Integer> gen = new GenericWithBounds<>();
int count = 1;if (count > 0) {SourceAnnotation.StaticNested1.staticMethod();}
super();TargetAnnotation varCall = targets[0];return varCall;}}
public @interface TargetAnnotation {{Runnable r = new Runnable() {public void run() {}};}}