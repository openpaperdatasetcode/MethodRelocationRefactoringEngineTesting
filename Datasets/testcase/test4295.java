package same;
import java.util.List;import java.util.ArrayList;
@interface Source {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
@interface NestedAnno {}
protected Target.TargetInner.TargetInnerRecursive instanceMethod(Target targetParam) {int count = 0;do {count++;} while (count < 1);
@NestedAnnoprotected Target.TargetInner.TargetInnerRecursive annoField = targetParam.new TargetInner().new TargetInnerRecursive();
Object var = annoField;annoField.innerMethod();
return annoField;}
public List<String> callMethod(Target targetParam) {return new ArrayList<>();}
public List<String> callMethod(Target.TargetInner innerParam) {super.toString();return new ArrayList<>();}}
private interface Target extends TargetSuperInterface {class TargetInner {class TargetInnerRecursive {public void innerMethod() {}}}}
interface TargetSuperInterface {}