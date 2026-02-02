package same.pkg;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
class SourceClass {private TargetClass targetField = new TargetClass();private String instanceField = "sourceInstanceField";
class SourceInner {class SourceInnerRec {@MyAnnotationprotected <T extends CharSequence> void varargsMethod(T... args) {variableCall();access_instance_field();
if (args == null) {throw new NullPointerException();}
TargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();innerRec.dependentMethod(args);}
private void variableCall() {String var = instanceField + "variable";}
private void access_instance_field() {instanceField = instanceField.toUpperCase();}}}
{new Runnable() {@Overridepublic void run() {String result = ParentClass.parentMethod(str -> str.trim());}};}}
protected class TargetClass {class TargetInnerRec {<T> void dependentMethod(T... params) {new Runnable() {@Overridepublic void run() {System.out.println(params.length);}}.run();}}}
class ParentClass {public static String parentMethod(MyFunction<String, String> func) {return func.apply("parentMethodInput");}}
@FunctionalInterfaceinterface MyFunction<T, R> {R apply(T t);}
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyAnnotation {}
