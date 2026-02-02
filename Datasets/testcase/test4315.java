package source;
import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import target.Target;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
@interface Source {Target sourceTargetField = new Target() {};
class SourceMemberInner {public void innerMethod() {}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().innerMethod();}};
@MethodAnnodefault int varargsMethod(String... params) throws SQLException {outer:for (String param : params) {if (param == null) {break outer;}
SourceMemberInner inner = new SourceMemberInner();inner.innerMethod();
protected boolean conditionalExp = (param.length() > 5) ? true : false;int conditionVal = conditionalExp ? 1 : 0;
Object var = sourceTargetField;int methodVal = sourceTargetField.targetInstanceMethod(conditionVal);
param + "_processed";}
return params.length;}
@Overrideboolean equals(Object obj);}
package target;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)abstract @interface Target {static class TargetStaticNested {public void nestedMethod() {}}
default int targetInstanceMethod(int val) {return val * 2;}}