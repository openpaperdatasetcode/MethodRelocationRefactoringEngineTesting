package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import other.DiffPackageOthers;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
strictfp enum Source {INSTANCE1, INSTANCE2;
Target targetField = Target.INSTANCE;int sourceVar;
class SourceMemberInner1 {}class SourceMemberInner2 {}
@Override@MethodAnnopublic String toString() {private void privateVarDeclInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();int field1 = diffObj.obj.field;int field2 = diffObj.obj.field;}privateVarDeclInDiff();
class LocalType {Target.TargetStaticNested nested;}new LocalType();
this.sourceVar = targetField.targetVar;
Object var = targetField;try {targetField.instanceMethod();} catch (Exception e) {}
return String.valueOf(sourceVar);}}
/**
Target enum: contains static nested class and instance method*/private enum Target {INSTANCE;
public int targetVar = 10;static class TargetStaticNested {}
public void instanceMethod() {}}
package other;
public class DiffPackageOthers {public Obj obj = new Obj();}
class Obj {public int field = 5;}