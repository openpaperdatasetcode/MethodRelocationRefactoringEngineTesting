package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {private int outerPrivate = 5;
static class StaticNested {public TargetClass method() {return new TargetClass();}}
private Object anonymous = new Object() {void foo() {}};
@MyAnnotationprivate List<String> moveMethod(List<Integer> paramList, String... args) {class LocalType {}LocalType lt = new LocalType();
TargetClass target = new TargetClass();try {for (int i = 0; i < 2; i++) {TargetClass result = new StaticNested().method();target.outerPrivateAccess = outerPrivate;target.instanceMethod();expressionStatement();}} catch (Exception e) {}
return new ArrayList<>();}
private void expressionStatement() {}
public class Inner extends StaticNested {@Overridepublic TargetClass method() {throw new RuntimeException();}}}
class TargetClass {int outerPrivateAccess;
void instanceMethod() {class LocalInner {}}}