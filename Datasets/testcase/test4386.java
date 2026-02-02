package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(Retention.RUNTIME)@interface MethodAnno {String value() default "";Class<?> handler() default SourceClass.Inner.class;}
protected sealed class SourceClass permits SourceSubClass {protected String outerProtectedField = "protectedValue";
class Inner {void innerMethod(TargetClass target) {label: {if (TargetClass.staticField > 3) {break label;}}}}
Object anonymousInner = new Runnable() {@Overridepublic void run() {}};
@MethodAnno(handler = SourceClass.Inner.class, value = "refactoredMethod")public final List<String> moveMethod(TargetClass targetParam) {if (targetParam == null) {return new ArrayList<>();}
targetParam.instanceField = "updated";String outerVal = SourceClass.this.outerProtectedField;
new TargetClass();super.toString();
List<String> result = genericHelper(targetParam, outerVal);result.addAll(moveMethod(targetParam));return result;}
private strictfp <T> List<String> genericHelper(T target, String val) {List<String> list = new ArrayList<>();list.add(val);list.add(target.toString());return list;}}
class SourceSubClass extends SourceClass {}
public class TargetClass {public static int staticField = 2;String instanceField;
public TargetClass() {super();}}