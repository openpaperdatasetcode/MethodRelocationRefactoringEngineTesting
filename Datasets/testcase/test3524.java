package source;
import target.TargetEnum;import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.Arrays;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnn {String value() default "Test annotation for Move Method";}
public enum SourceEnum {INSTANCE;
public static class StaticNested {public static void validate(TargetEnum target) throws IOException {if (target == null) {throw new IOException("Target cannot be null");}}}
@EnumMethodAnn@EnumMethodAnn(value = "Duplicate annotation test")public void normalMethod(TargetEnum... targets) throws IOException {StaticNested.validate(targets[0]);
class LocalInner {void process(TargetEnum t) {System.out.println("LocalInner processing: " + t.getName());}}LocalInner local = new LocalInner();
// Raw type usageList rawList = Arrays.asList(targets);for (int i = 0; i < rawList.size(); i++) {TargetEnum target = (TargetEnum) rawList.get(i);local.process(target);variableCall: System.out.println(target.getName());}
// Enhanced for statementfor (TargetEnum target : targets) {accessInstanceMethod: target.getStaticNested().format(target.getName());local.process(target);}
if (targets.length == 0) {throw new IOException("No targets provided");}}}
package target;
public enum TargetEnum {VALUE1("one"), VALUE2("two");
private final String name;
TargetEnum(String name) {this.name = name;}
public String getName() {return name;}
public StaticNested getStaticNested() {return new StaticNested();}
public static class StaticNested {public void format(String input) {System.out.println("Formatted: " + input.toUpperCase());}}}