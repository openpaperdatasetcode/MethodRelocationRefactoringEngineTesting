package test;
import java.util.ArrayList;import java.util.List;
sealed abstract class SourceClass permits SourceSubClass {protected int outerProtectedField = 15;
public static class StaticNested {public static final String STATIC_FIELD = "source_static";}
public class SourceInner {public class InnerRecursive {protected int process(TargetClass... targets) {List<TargetClass> list = new ArrayList<>();for (TargetClass target : targets) {list.add(target);}
int result = 0;for (TargetClass target : list) {TargetClass.MemberInner targetInner = target.new MemberInner();
if (target.targetField == 1) {break;}
try {Object methodResult = targetInner.callFinal(target);result += ((Number) methodResult).intValue();} catch (Exception e) {result = 0;}
result += outerProtectedField;result += TargetClass.STATIC_FIELD.hashCode();}
Runnable anon = new Runnable() {@Overridepublic void run() {list.forEach(t -> t.targetField++);}};anon.run();
return result;}}}}
class SourceSubClass extends SourceClass {}
abstract class TargetClass {public int targetField;public static final int STATIC_FIELD = 20;
public class MemberInner {public final Object callFinal(TargetClass target) {super.toString();return target.targetField * 3;}}}
