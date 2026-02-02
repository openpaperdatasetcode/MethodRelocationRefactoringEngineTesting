package test;
import java.lang.reflect.Method;
enum TargetClass {INSTANCE;
class TargetInner {int innerField = 15;}
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {new TargetInner().innerField++;}};r.run();}}
public sealed enum SourceClass permits SourceSubEnum {VALUE1, VALUE2;
@MyAnnotationprivate static TargetClass staticMethod(TargetClass.TargetInner param) {labeled: {if (param == null) {break labeled;}int val = param.innerField;val += SourceClass.VALUE1.ordinal();}
try {Method refMethod = SourceClass.class.getDeclaredMethod("staticMethod", TargetClass.TargetInner.class);refMethod.setAccessible(true);return (TargetClass) refMethod.invoke(null, param);} catch (Exception e) {return TargetClass.INSTANCE;}}
public static class SourceStaticNested1 {void invokeStaticMethod() {TargetClass.TargetInner inner = TargetClass.INSTANCE.new TargetInner();TargetClass result = SourceClass.staticMethod(inner);}}
public static class SourceStaticNested2 {void useTarget() {TargetClass.INSTANCE.createAnonymous();}}
@interface MyAnnotation {}}
final enum SourceSubEnum implements SourceClass {SUB_VALUE;}