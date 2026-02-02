package test;
@FunctionalInterfaceinterface FunctionalInterface {Object execute();}
abstract class SourceClass {protected TargetClass targetField = new TargetClass();
public class MemberInner {}
private Runnableable anonymousSource = new Runnable() {@Overridepublic void run() {}};
default void instanceMethod() {protected abstract Object abstractMethod();
FunctionalInterface func = () -> {int count = 3;return super.toString();};
do {if (targetField.inner.rec.field > 0) {targetField.inner.rec.field--;Object result = func.execute();}} while (targetField.inner.rec.field > 0);}}
public class TargetClass {public InnerClass inner = new InnerClass();
public class InnerClass {public RecursiveInner rec = new RecursiveInner();
public class RecursiveInner {int field = 3;}}
private Runnable anonymousTarget = new Runnable() {@Overridepublic void run() {}};}