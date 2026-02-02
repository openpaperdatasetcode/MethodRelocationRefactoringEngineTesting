package test.refactoring.movemethod;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
public abstract class TargetClass {static class TargetStaticNested {@Overridepublic String toString() {return "TargetStaticNested";}
public static void staticMethod() {}}}
protected abstract class SourceClass {private TargetClass target;private String var;
@CustomAnnotationpublic final List<String> overloadedMethod(TargetClass targetParam) throws SQLException {this.target = targetParam;String localVar = "test";this.var = localVar;
List<String> result = new ArrayList<>();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();Object varCall = nested.toString();
try {if (target == null) {throw new SQLException("Target is null");}} catch (SQLException e) {throw e;}
result.add(nested.toString());result.add(this.var);
// Ternary operator with inner class method callString ternaryResult = (result.size() > 0) ?(() -> { TargetClass.TargetStaticNested.staticMethod(); return "called"; }).get() :"not called";result.add(ternaryResult);
return result;}
public final List<String> overloadedMethod() {return new ArrayList<>();}
{new Runnable() {@Overridepublic void run() {try {overloadedMethod(new TargetClass() {});} catch (SQLException e) {e.printStackTrace();}}}.run();
new Runnable() {@Overridepublic void run() {overloadedMethod();}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3039 {@Testpublic void testOverloadedMethod() throws SQLException {SourceClass source = new SourceClass() {};TargetClass target = new TargetClass() {};List<String> result = source.overloadedMethod(target);assertEquals(3, result.size());}}