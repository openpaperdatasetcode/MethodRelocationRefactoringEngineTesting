package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.Collection;
class ParentEnum {}
sealed public enum SourceEnum extends ParentEnum permits ExtendedSourceEnum {SOURCE_INSTANCE;
protected TargetEnum overloadingMethod (TargetEnum target) {return process (target);}
protected TargetEnum overloadingMethod (TargetEnum target, String arg) {return process (target);}
private TargetEnum process (TargetEnum target) {new Runnable () {@Overridepublic void run () {variableCall (target.staticNested);}}.run ();
class LocalInner {void processLabel () { obj.fieldprivate labeled: {System.out.println (target.objField);break labeled;}}}new LocalInner ().processLabel ();
Collection rawCollection = new ArrayList ();rawCollection.add (target.objField);
try {SQLExceptionif (target == null) throw new SQLException ("Target cannot be null");variableCall (target.staticNested);} catch (SQLException e) {e.printStackTrace ();}
return target;}
private void variableCall(TargetEnum.StaticNested nested) {nested.nestedMethod();}}
non-sealed enum ExtendedSourceEnum extends ParentEnum implements SourceEnum {}
public enum TargetEnum {TARGET_INSTANCE;
public String objField = "targetField";
static class StaticNested {void nestedMethod() {}}
StaticNested staticNested = new StaticNested();}