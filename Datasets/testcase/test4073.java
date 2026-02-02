package test;
import java.io.IOException;
class ParentClass {protected int superField = 3;}
protected class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
public TargetClass recursiveMethod(int depth) throws IOException {super();
public int localVar = super.superField;
while (depth > 0) {TargetClass var = SourceClass.this.targetField;depth--;}
return depth == 0 ? targetField : recursiveMethod(depth);}
strictfp class InnerCaller {public Object callMethod() throws IOException {return (Math.random() > 0.5) ? SourceClass.this.recursiveMethod(3) : new TargetClass().anonymousMethod();}}}
protected class TargetClass {public Object anonymousMethod() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};runnable.run();return this;}}