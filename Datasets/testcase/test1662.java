package same;
import java.util.function.Supplier;
public enum SourceEnum {VALUE1, VALUE2;
public static class StaticNested {public class NestedInner {@Deprecatedpublic Object methodToMove(TargetEnum... targets) {int i = 0;while (i < targets.length) {TargetEnum target = targets[i];Object val = target.superField > 0 ? target : null;i++;}
Supplier<?> sup = new Supplier<>() {public Object get() {return targets[0].field;}};
return sup.get();}}}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {System.out.println("Anonymous in SourceEnum");}};}}
package same;
/**
Javadoc for TargetEnum*/public enum TargetEnum implements Runnable {A, B;
protected int superField;public String field;
@Overridepublic void run() {}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {System.out.println("Anonymous in TargetEnum");}};}}