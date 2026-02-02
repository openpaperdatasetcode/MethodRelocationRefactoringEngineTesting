package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MyInnerAnnotation {}
public class SourceClass {public class SourceInner {@MyInnerAnnotationprivate void instanceMethod(TargetClass target) {class LocalType {void processTarget(TargetClass.TargetInner inner) {List rawList = new ArrayList();rawList.add(inner.innerField);System.out.println(rawList.get(0));}}
new Runnable() {@Overridepublic void run() {LocalType local = new LocalType();local.processTarget(target.inner);}};
target.inner.innerMethod();target.createLocalInner();}}
private SourceInner innerInstance = new SourceInner();}
sealed class TargetClass permits TargetSubClass {TargetInner inner = new TargetInner();
public class TargetInner {int innerField = 5;
void innerMethod() {}}
public void createLocalInner() {class TargetLocalInner {void localProcess() {System.out.println(TargetClass.this.inner.innerField);}}new TargetLocalInner().localProcess();}}
final class TargetSubClass extends TargetClass {}