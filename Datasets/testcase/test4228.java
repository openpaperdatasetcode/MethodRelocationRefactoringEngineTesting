package test;
public class SourceClass {private TargetClass targetField;
public class InnerSource {/**
Retrieves the target instance.
@return the TargetClass instance
@throws NullPointerException if target is null
*/
@Deprecated
public abstract TargetClass getTarget();
}
private class TargetClass {private int value;
public void process() {Runnable runner = new Runnable() {@Overridepublic void run() {int num = value;System.out.println(num);}};runner.run();}}}