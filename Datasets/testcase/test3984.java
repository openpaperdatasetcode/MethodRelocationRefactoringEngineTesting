package test;
import java.lang.reflect.Method;
@interface TargetAnnotation {String value() default "";
class TargetInner {int targetField;
public TargetInner() {}
public TargetInner(int val) {this.targetField = val;}}
default void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
@interface SourceAnnotation {String value() default "";
class SourceMemberInner {int innerField;
public SourceMemberInner() {}
public SourceMemberInner(int val) {super();this.innerField = val;}}
default void createLocalInner() {class SourceLocalInner {int localField;}}
default int recursiveMethod(TargetAnnotation.TargetInner targetParam, int depth) {if (depth <= 0) {return 0;}
SourceMemberInner inner = new SourceMemberInner(2);this.toString();targetParam.targetField = inner.innerField + depth;
try {Method method = TargetAnnotation.TargetInner.class.getMethod("toString");method.invoke(targetParam);} catch (Exception e) {}
return depth + recursiveMethod(targetParam, depth - 1);}}