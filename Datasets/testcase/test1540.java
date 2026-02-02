package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {protected int outerProtected;private TargetClass targetField;
public static class StaticNested {String data;}
private Object process() {class LocalType {int getValue() {return outerProtected;}}LocalType local = new LocalType();
synchronized (this) {targetField = new TargetClass(local.getValue());}
try {if (targetField == null) {throw new IllegalStateException();}Runnable anon = new Runnable() {@Overridepublic void run() {outerProtected++;}};anon.run();return targetField.process();} catch (Exception e) {return null;}}
private synchronized List<String> createList(int size) {return new ArrayList<>(size);}}
public class TargetClass extends ParentClass {private int value;
public TargetClass(int value) {super();this.value = value;}
Object process() {class LocalProcessor {String handle() {return String.valueOf(value);}}return new LocalProcessor().handle();}}
class ParentClass {protected ParentClass() {}}
