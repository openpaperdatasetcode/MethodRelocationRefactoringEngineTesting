package test;
// Interface for source enum's implements featureinterface TestInterface {void testMethod();}
// Abstract target enum with anonymous inner class (target_feature)abstract enum TargetEnum {VALUE1, VALUE2;
// Static field for depends_on_static_field featurepublic static final String STATIC_FIELD = "targetStaticValue";
// Abstract method for method featurepublic abstract TargetEnum abstractMethod();
public void execute() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(STATIC_FIELD);}};runnable.run();}}
// Source: public enum (implements + member inner + local inner class)public enum SourceEnum implements TestInterface {PERM1, PERM2;
// Member inner class (source_class feature)protected class SourceInner {// Default abstract method (matches method feature)public abstract TargetEnum innerAbstractMethod(TargetEnum target);}
// Default varargs method (base type return: int)int varargsMethod(TargetEnum... targets) { // Contains target parameter (meets per_condition)int count = 0;SourceInner inner = new SourceInner() {@Overridepublic TargetEnum innerAbstractMethod(TargetEnum target) {return target;}};
for (TargetEnum target : targets) {// Expression (pos for abstract method feature)TargetEnum abstractResult = inner.innerAbstractMethod(target); // this.methodName(arguments)
// Expression statementtarget.execute();
// Super keywordssuper.toString();
// Variable callvariableCall(target);
// 2 protected MethodInvocationprotected String invoc1 = target.name();protected int invoc2 = target.ordinal();
// Depends on static field (target's static field)count += TargetEnum.STATIC_FIELD.length();
// Local inner class (source_class feature)class LocalInner {void process() {variableCall(target);}}new LocalInner().process();}
return count; // No new exception}
private void variableCall(TargetEnum target) {TargetEnum local = target;local.abstractMethod();}
@Overridepublic void testMethod() {}}
