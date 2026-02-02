import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class Container {private enum SourceEnum {INSTANCE;
public static class StaticNested {public static String processBaseType(int value) {return String.valueOf(value);}}
strictfp List<String> varargsMethod(TargetEnum... targets) {List<String> result = new ArrayList<>();if (targets == null || targets.length == 0) {return result;}
class LocalInner {void handleTarget(TargetEnum target, int baseParam) {variableCall(target.staticNested);result.add(target.name());result.add(SourceEnum.StaticNested.processBaseType(baseParam));result.add(target.staticNested.getStaticField());}}LocalInner local = new LocalInner();
try {Method targetMethod = TargetEnum.StaticNested.class.getMethod("updateStaticField", String.class);targetMethod.invoke(null, "reflected_update");} catch (Exception e) {}
for (TargetEnum target : targets) {local.handleTarget(target, 10);result.add(overloadHandle(target));result.add(overloadHandle(target, "prefix_"));}
return result;}
private void variableCall(TargetEnum.StaticNested nested) {nested.setStaticField("var_call_update");}
private String overloadHandle(TargetEnum target) {return target.name() + "_overload1";}
private String overloadHandle(TargetEnum target, String prefix) {return prefix + target.name() + "_overload2";}}
public void useSourceEnum() {List<String> res = SourceEnum.INSTANCE.varargsMethod(TargetEnum.VALUE1, TargetEnum.VALUE2);}}
public enum TargetEnum {VALUE1, VALUE2;
public StaticNested staticNested = new StaticNested();
public static class StaticNested {private String staticField = "default_static";
public void setStaticField(String field) {this.staticField = field;}
public String getStaticField() {return staticField;}
public static void updateStaticField(String field) {new StaticNested().setStaticField(field);}}}