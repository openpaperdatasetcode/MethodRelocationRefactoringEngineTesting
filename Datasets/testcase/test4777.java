package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
enum BaseEnum {VALUE;protected int method(TargetEnum target) { return 0; }}
enum SourceEnum extends BaseEnum {SOURCE;
public class SourceInner {@MyAnnotation@Overrideprotected int method(TargetEnum target) {class LocalInner {void checkField() {switch (target.field) {case 1: break;default: break;}}}new LocalInner().checkField();
TargetEnum.StaticNested nested = new TargetEnum.StaticNested();
new Runnable() {public void run() {SourceInner.this.method(target);}}.run();
@MyAnnotationint val = target.field;
return val;}}}
public enum TargetEnum {TARGET;
public int field;public static class StaticNested {}}