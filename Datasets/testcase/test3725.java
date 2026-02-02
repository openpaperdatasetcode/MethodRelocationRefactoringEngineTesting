import java.util.Objects;
interface SourceInterface extends ParentInterface {@RefactorMarkerdefault Object recursiveMethod(TargetInterface target, int depth) {if (depth <= 0) {return target;}
class TypeDeclaration {void process(TargetInterface t) {variableCall(t.innerClass);t.innerClass.instanceMethod();}}new TypeDeclaration().process(target);
return recursiveMethod(target, depth - 1);}
private default void variableCall(TargetInterface.InnerClass inner) {inner.setData("source_processed");}
class MemberInner {public Object useRecursion(TargetInterface target) {return SourceInterface.this.recursiveMethod(target, 3);}}
static class StaticNested {public static Object staticHelper(TargetInterface target) {return new SourceInterface.MemberInner().useRecursion(target);}}}
interface ParentInterface {default void parentMethod() {}}
interface TargetInterface extends ParentTargetInterface {class InnerClass {private String data;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public void instanceMethod() {}}
InnerClass innerClass = new InnerClass();}
interface ParentTargetInterface {default void targetParentMethod() {}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorMarker {}