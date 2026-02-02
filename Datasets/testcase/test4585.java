package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
protected class SourceClass {public class SourceInner {private TargetClass<String> targetField;
public final TargetClass<String> recursiveMethod(int depth) {if (depth <= 0) {try {throw new IOException("Depth reached zero");} catch (IOException e) {return targetField;}}
class LocalType {transient void processTarget(TargetClass<String> target) {List<String> items = new ArrayList<>();for (String item : items) {if (item.equals(target.dataField)) {int value = new TargetClass<>("call").overloadedMethod(1);}}}}
new Runnable() {@Overridepublic void run() {LocalType local = new LocalType();local.processTarget(targetField);}};
int count = 0;while (count < 3) {count += new TargetClass<>("loop").overloadedMethod("count");}
return recursiveMethod(depth - 1);}}
private SourceInner innerInstance = new SourceInner();}
strictfp class TargetClass<T> {public T dataField;
public TargetClass(T data) {this.dataField = data;}
protected int overloadedMethod(int num) {return num * 2;}
protected int overloadedMethod(String str) {return str.length();}
public void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(dataField);}};}}