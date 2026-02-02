package test;
import diffpackage.OthersClass;import java.util.Objects;
@MyAnnotationprotected enum SourceEnum {INSTANCE;
protected int outerProtected;static class StaticNested {}
protected int process(TargetEnum... targets) {Objects.requireNonNull(targets, "Targets must not be null");OthersClass others = new OthersClass();int result = 0;
for (TargetEnum target : targets) {synchronized (this) {TargetEnum.StaticNested nested = new TargetEnum.StaticNested();nested.value = 3;result += nested.value;
others.handle(() -> {switch (TargetEnum.staticField) {case 3:outerProtected = target.ordinal();break;default:break;}});}
class LocalInner {}}
return result;}
// Override violation (if TargetEnum has same method signature)public void process(String param) {}}
protected enum TargetEnum {VALUE1, VALUE2;
static int staticField;
static class StaticNested {int value;}}
package diffpackage;
public class OthersClass {void handle(Runnable action) {action.run();}}
@interface MyAnnotation {}