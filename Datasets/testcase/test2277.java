package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
interface MyInterface {}class ParentClass {}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {String value() default SourceClass.MemberInner.InnerRec.staticMethod().toString();}
public class SourceClass extends ParentClass implements MyInterface {class MemberInner {class InnerRec {@MyAnnpublic Object varargsMethod(TargetClass... targets) {int i = 0;do {variableCall = targets[0].field;i++;} while (i < 2);
int result = switch (i) {case 2 -> 2;default -> 0;};return result;}
public static List<String> staticMethod() {return new ArrayList<>();}
String variableCall;}}
{new Runnable() {};}}
protected class TargetClass extends ParentClass implements MyInterface {String field;class TargetInner {}}
