package samepkg;
import java.lang.reflect.Method;import java.util.List;import java.util.function.Consumer;
/**
Source enum for Move Method refactoring test (varargs method)*/public enum SourceEnum permits SourceEnumExtension {INSTANCE_A, INSTANCE_B;
public static class StaticNestedSource {}public class MemberInnerSource {}
/**
Processes target enum with varargs and returns Object
@param targetParam Target enum parameter (per condition)
@param args Varargs parameters
@return Processed Object result*/protected Object processVarargs(TargetEnum<?> targetParam, String... args) {// Raw type usageTargetEnum rawTarget = targetParam;
// Variable call: target members, inner/static nested classesrawTarget.field = "processed";StaticNestedSource nested = new StaticNestedSource();MemberInnerSource inner = new MemberInnerSource();
// Loop with ContinueStatement and BreakStatementloop:for (String arg : args) {if (arg.isEmpty()) {; // Empty statementcontinue loop; // ContinueStatement with this.field (target's field)}if (arg.equals("stop")) {break loop; // Break statement}rawTarget.anonymousAction.accept(arg);}
// Used by reflectiontry {Method method = TargetEnum.InnerClass.class.getDeclaredMethod("process", String.class);method.setAccessible(true);method.invoke(rawTarget.new InnerClass(), "reflectionArg");} catch (Exception e) {}
// Call parent class's private method (chain call: obj.m1().m2().m3())List<String> chainResult = rawTarget.getParentInstance().m1().m2().m3();
return chainResult;}}
// Permitted extension of source enumenum SourceEnumExtension implements SourceEnum {}
package samepkg;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
/**
Target enum with type parameter and anonymous inner class*/public enum TargetEnum<T> extends ParentEnum {INSTANCE_X, INSTANCE_Y;
public T field;public Consumer<String> anonymousAction = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println("Anonymous processing: " + s);}};
public class InnerClass {private void process(String arg) {field = (T) arg;}}
public ParentEnum getParentInstance() {return this;}}
// Parent class for target enumclass ParentEnum {private ParentEnum m1() {return this;}
private ParentEnum m2() {return this;}
private List<String> m3() {return new ArrayList<>();}}