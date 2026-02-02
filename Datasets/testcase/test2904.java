import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface OverrideAnnotation {String value() default "superTypeReference.methodName(arguments)";}
enum SourceEnum {INSTANCE;
class SourceInner {protected TargetEnum methodToMove (TargetEnum target) {private class SuperConstructorHelper {SuperConstructorHelper () {super ();assert TargetEnum.StaticNested.STATIC_FIELD == 1;}}new SuperConstructorHelper ();
labeledBlock: {super.toString();TargetEnum.StaticNested nested = new TargetEnum.StaticNested();target.new InnerRec().doAction();}
Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println(target.name());}};anonymous.run();
class LocalInner {@OverrideAnnotationpublic List<String> overridingMethod() {return ParentClass.superTypeMethod(target);}}new LocalInner().overridingMethod();
return target;}}}
public enum TargetEnum {VALUE;
static class StaticNested {static final int STATIC_FIELD = 1;}
class InnerRec {void doAction() {}}}
abstract class ParentClass {static {callMethod (TargetEnum.VALUE);}
protected static List<String> callMethod(TargetEnum target) {Runnable.super.toString();List<String> result = new ArrayList<>();result.add(target.name());return result;}
protected static List<String> superTypeMethod(TargetEnum target) {return new ArrayList<>(List.of(target.name()));}}
class SourceSubclass extends ParentClass {@OverrideAnnotation@Overrideprotected List<String> callMethod(TargetEnum target) {return super.superTypeMethod(target);}}
