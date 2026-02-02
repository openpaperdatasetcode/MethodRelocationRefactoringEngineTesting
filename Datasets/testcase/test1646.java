package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
sealed abstract class SourceClass permits TargetClass {private static class StaticNested {public List<String> overriddenMethod(int num) {return new ArrayList<>();}}
public final int methodToMove(String... args) {// if statementif (args.length > 0) {// expression statementargs[0] = "modified";}
// variable call and access_instance_methodStaticNested sn = new StaticNested();List<String> list = sn.overriddenMethod(3);
// local inner classclass LocalInner {private char charLit = 'a'; // CharacterLiteral (private modifier)}LocalInner li = new LocalInner();char c = li.charLit;
// used_by_reflectiontry {Method method = TargetClass.class.getMethod("anonymousMethod");} catch (Exception e) {}
// target parameter usage (per_condition)TargetClass target = new TargetClass();int result = target.processArgs(args);
// property assignment with overriding featurestarget.listProp = SuperType.superMethod(3, new StaticNested(), list);
return 3; // base type return}}
abstract class SuperType {public static List<String> superMethod(int num, StaticNested sn, List<String> list) {return new ArrayList<>();}}
public abstract class TargetClass extends SourceClass {List<String> listProp;
public int processArgs(String... params) {return params.length;}
// anonymous inner class (target_feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {anonymousMethod();}
public List<String> overriddenMethod(int num) { // overriding (public modifier)return new ArrayList<>();}};
public void anonymousMethod() {}}