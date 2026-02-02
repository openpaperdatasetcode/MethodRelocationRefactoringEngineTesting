class SourceInner1 {class SourceInnerRecursive {default void recursiveMethod(Target<T>.TargetInner.TargetInnerRecursive targetRecParam) throws NullPointerException {public class OverrideHelper extends SourceInnerRecursive {@Overridepublic Object overridingMethod(Target<T>.TargetInner.TargetInnerRecursive rec) {return (rec != null) ? rec.instanceMethod() : null;}}
OverrideHelper helper = new OverrideHelper();helper.overridingMethod(targetRecParam);helper.overridingMethod(targetRecParam);helper.overridingMethod(targetRecParam);
if (targetRecParam == null) {throw new NullPointerException("Target recursive inner is null");}
Object var = targetRecParam;;if (var != null) {recursiveMethod(targetRecParam);}}}}
class SourceInner2 {}}
non-sealed interface SourceSub1<T> extends Source<T> {}non-sealed interface SourceSub2<T> extends Source<T> {}
public interface Target {
static class TargetStaticNested {}
class TargetInner {class TargetInnerRecursive {public Object instanceMethod() {return null;}
protected abstract void callInSwitch();
protected void callInSwitch(int param) {switch (param) {case 1:super.callInSwitch();break;default:break;}}}}}