package test;
import java.util.regex.Pattern;
public class SourceClass {public class MemberInner {}public static class StaticNested {}
public record SourceInnerRec() {private static final Pattern privatePattern = Pattern.compile("\d+");
public final Object recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetField;}
String var = target.memberInner.innerField;boolean matches = privatePattern.matcher(var).matches();
return recursiveMethod(target, depth - 1);}}}
protected class TargetClass {public String targetField = "targetData";
public class MemberInner {public String innerField = "innerData123";}
public MemberInner memberInner = new MemberInner();}