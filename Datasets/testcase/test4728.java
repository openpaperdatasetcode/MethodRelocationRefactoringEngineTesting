package test;
import java.util.List;import java.util.ArrayList;
interface BaseInterface {List<String> overriddenMethod(int param);}
@interface SourceAnnotation implements BaseInterface {class InnerClass {protected int overloadedMethod(int i) {char c = 'a';char d = 'b';super.getClass();int[] arr = {TargetAnnotation.innerField, 2};return TargetAnnotation.AnonymousInner::new instanceof Runnable ? i : 0;}
protected String overloadedMethod(String s) {return s;}
@Overrideprotected List<String> overriddenMethod(int param) {return BaseInterface.super.overriddenMethod(param);}}
class MemberInner implements Runnable {public void run() {}}
strictfp List<String> callMethod() {InnerClass ic = new InnerClass();return ((Runnable) () -> {ic.overloadedMethod(1);ic.overloadedMethod("str");})::toString;}
abstract class AbstractInner {abstract char abstractMethod();}}
public @interface TargetAnnotation implements Runnable {int innerField = 5;
void run() {}
class AnonymousInner {}}