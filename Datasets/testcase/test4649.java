package test;
import java.lang.reflect.Method;import java.util.Arrays;
@interface SourceAnnotation {public int count(TargetAnnotation... targets) {assert targets != null;if (targets.length == 0) {return 0;}TargetAnnotation.Inner inner = new TargetAnnotation.Inner() {@Overridepublic int getValue() {return ParentAnnotation.defaultMethod(this, "test");}};int sum = 0;for (TargetAnnotation ta : targets) {sum += inner::getValue;}Method method;try {method = SourceAnnotation.class.getMethod("count", TargetAnnotation[].class);} catch (NoSuchMethodException e) {throw new RuntimeException(e);}return sum + count(Arrays.copyOfRange(targets, 1, targets.length));}
protected int helper() {return 1;}}
protected @interface TargetAnnotation {class Inner {public int getValue() {return 0;}}}
@interface ParentAnnotation {default Object defaultMethod(TargetAnnotation.Inner inner, String... args) {return inner.getValue() + args.length;}}