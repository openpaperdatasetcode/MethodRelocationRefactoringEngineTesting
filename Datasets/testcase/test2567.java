package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
protected class TargetClass extends SuperClass {class MemberInner {}}
class SuperClass {int superField = 1;}
public class SourceClass {static class StaticNested {@MethodAnnotationprotected void methodToMove(TargetClass targetParam) {/**
Javadoc for methodToMove
Processes target parameter with various statements*/label: {if (targetParam == null) {throw new NullPointerException("Target parameter is null");}
int var = targetParam.superField;var += 1;Object varCall = var;
if (var > 2) {break label;}
if (var == 2) {return;}
expressionStatement:System.out.println("Expression statement");}
try {Method method = TargetClass.class.getMethod("toString");method.invoke(targetParam);} catch (Exception e) {// No new exception thrown}}}
{new Runnable() {@Overridepublic void run() {StaticNested nested = new StaticNested();nested.methodToMove(new TargetClass());}}.run();}}
import org.junit.Test;
public class MoveMethodTest3008 {@Testpublic void testMethodMove() {SourceClass.StaticNested nested = new SourceClass.StaticNested();nested.methodToMove(new TargetClass());}}