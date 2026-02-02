package test;
protected interface TargetInterface<T> {/**
Javadoc for TargetInterface
*/
default void targetMethod() {
Runnable r = new Runnable() {
public void run() {}
};
}
}
interface SourceInterface {class SourceInner {public Object sourceMethod(TargetInterface<String> param) {class LocalType {}LocalType localVar = new LocalType();
try {switch (param.toString()) {case "test":localVar = new LocalType();break;default:break;}} catch (Exception e) {}
return new Object() {{super();}};}
public Object sourceMethod(int i) {return null;}}
Runnable anonymous = new Runnable() {public void run() {new SourceInner().sourceMethod((TargetInterface<String>) null);}};}
