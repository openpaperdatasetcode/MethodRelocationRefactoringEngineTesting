import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface RefactoringTest {}
public class SourceClass {class MemberInner {@RefactoringTestTargetClass process() {super();TargetClass target = new TargetClass();
class LocalInner {TargetClass createTarget() {return new TargetClass.StaticNested(target.dataField).getTarget();}}
TargetClass result = new LocalInner().createTarget();result.dataField = "updated";return result;}}}
abstract class TargetClass extends ParentClass {String dataField;
static class StaticNested {private String nestedField;
protected StaticNested(String field) {this.nestedField = field;}
TargetClass getTarget() {return new TargetClass() {};}}}
class ParentClass {}