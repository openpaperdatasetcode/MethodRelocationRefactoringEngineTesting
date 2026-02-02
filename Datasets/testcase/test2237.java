package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
enum SourceEnum implements Runnable {INSTANCE;
class MemberInner {@MyAnnotationpublic static Object methodToMove(TargetEnum target) {try {for (int i = 0; i < 2; i++) {int val = new InnerHelper().helperMethod(2);}
switch (target) {case VALUE1:target.instanceMethod();break;default:break;}} catch (IOException e) {// No new exception}return new Object();}}
class InnerHelper {default int helperMethod(int num) {return new TargetEnum.StaticNested().nestedMethod();}}
{new Runnable() {public void run() {}};}
public void run() {}}
/**
Javadoc for TargetEnum*/public enum TargetEnum {VALUE1, VALUE2;
static class StaticNested {int nestedMethod() {return 0;}}
void instanceMethod() {}}