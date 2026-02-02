package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
private class SourceClass {class MemberInner {}
@MethodAnno // has_annotationstrictfp List<String> methodToMove(TargetClass<String>.TargetInnerRec... targets) {// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {}};
List<String> result = new ArrayList<>();try {// Variable call (source contains target's field)for (TargetClass<String>.TargetInnerRec target : targets) {if (target == null) throw new NullPointerException();result.add(target.field);}} catch (NullPointerException e) {result.add("null target handled");}
// Call method: others_class overriding in whileOthersClass others = new OthersClass();int i = 0;while (i < 1) {result.addAll(OthersClass.MemberInner::overriddenMethod);i++;}
return result;}}
package target;
private class TargetClass<T> {// Target feature: anonymous inner classRunnable targetAnon = new Runnable() {@Overridepublic void run() {}};
class TargetInner {record TargetInnerRec(T field) {} // Target field used in source}}
package other;
import java.util.List;import java.util.ArrayList;
public class OthersClass {static class MemberInner extends ParentClass {@Overridepublic List<String> overriddenMethod() {return new ArrayList<>(List.of("overridden result"));}}}
abstract class ParentClass {public abstract List<String> overriddenMethod();}