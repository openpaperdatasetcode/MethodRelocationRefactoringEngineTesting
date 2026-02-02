package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
interface Source {private int outerPrivateField = 50;static class SourceStaticNested {}
/**
Varargs method: processes Target instances and handles null checks
@param targets Variable-length array of Target parameters
@throws NullPointerException If any Target parameter is null*/@MethodAnnoprotected void varargsMethod(Target... targets) throws NullPointerException {private void privateVarDecl() {Obj obj = new Obj();int field1 = obj.field;int field2 = obj.field;int field3 = obj.field;}privateVarDecl();
for (Target target : targets) {if (target == null) {throw new NullPointerException("Target parameter cannot be null");}
Object var = target;int accessOuter = outerPrivateField;}
class SourceLocalInner {void useTarget(Target target) {Object localVar = target;}}new SourceLocalInner().useTarget(targets[0]);}}
class Obj {public int field = 10;}
public interface Target extends TargetSuperInterface {default void createLocalInner() {class TargetLocalInner {void useTarget() {}}new TargetLocalInner().useTarget();}}
interface TargetSuperInterface {}