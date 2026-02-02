package test;
public class TargetClass {int value = 10;
void createAnonymous() {Runnable r = new Runnable() {public void run() {System.out.println(TargetClass.this.value);}};}}
protected class SourceClass<T> {private int outerPrivate = 5;TargetClass targetField = new TargetClass();
public class SourceInner {public abstract class SourceInnerRec {@MyAnnotationfinal abstract TargetClass abstractMethod();
protected SourceInnerRec() {super();}
protected int overrideMethod() {return 0;}}}
public static class StaticNested {void useInnerRec() {SourceInnerRec innerRec = new SourceClass<>().new SourceInner().new SourceInnerRec() {@Overridefinal TargetClass abstractMethod() {if (targetField == null) {throw new NullPointerException();}
Runnable r = () -> System.out.println(this.value);r.run();
int val = outerPrivate;val = SourceClass.this.outerPrivate;
try {val = OthersClass::overrideMethod;} catch (Exception e) {}
return targetField;}};}}
@interface MyAnnotation {}}
class OthersClass {protected int overrideMethod() {return 1;}}