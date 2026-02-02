package test;
import java.lang.reflect.Constructor;
private enum SourceEnum {INSTANCE;
public class MemberInner {public void process(TargetEnum target) {// Static nested class in sourceStaticNested helper = new StaticNested();
// Private ConstructorInvocation with 3 obj.field accessesprivate TargetEnum.StaticNested nested1 = new TargetEnum.StaticNested(target.data);private TargetEnum.StaticNested nested2 = new TargetEnum.StaticNested(target.data + "_copy1");private TargetEnum.StaticNested nested3 = new TargetEnum.StaticNested(target.data + "_copy2");
// Variable call - access target's fieldhelper.log(target.data);
// Used by reflectiontry {Constructor<?> constructor = TargetEnum.StaticNested.class.getConstructor(String.class);TargetEnum.StaticNested nestedReflect = (TargetEnum.StaticNested) constructor.newInstance(target.data + "_reflect");} catch (Exception e) {// no new exception}}}
public static class StaticNested {public void log(String data) {System.out.println(data);}}}
abstract enum TargetEnum {VALUE1, VALUE2;
public String data;
public static class StaticNested {public String nestedData;
public StaticNested(String data) {this.nestedData = data;}}
public abstract void compute();}