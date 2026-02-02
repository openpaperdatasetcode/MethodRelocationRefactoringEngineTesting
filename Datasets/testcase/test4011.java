package test;
import java.util.List;import java.lang.reflect.Method;import java.io.IOException;
protected interface TargetInterface {int targetField = 0;
default void createLocalInner() {class TargetLocalInner {TargetInterface getTarget() {return new TargetInterface() {};}}}}
interface SourceInterface {TargetInterface targetInstance = new TargetInterface() {};raw_type rawObj = new raw_type();
default void createAnonymous1() {Runnable r1 = new Runnable() {public void run() {}};}
default void createAnonymous2() {Runnable r2 = new Runnable() {public void run() {}};}
private TargetInterface instanceMethod(List<String> paramList) throws IOException {if (paramList == null) {throw new IOException("List parameter is null");}
TargetInterface.TargetLocalInner localInner = new TargetInterface() {{createLocalInner();}}.new TargetLocalInner();
for (String item : paramList) {rawObj.setValue(item);}
try {Method method = TargetInterface.TargetLocalInner.class.getMethod("getTarget");return (TargetInterface) method.invoke(localInner);} catch (Exception e) {throw new IOException("Reflection call failed", e);}}}
class raw_type {private String value;public void setValue (String value) {this.value = value;}}
interface TargetInterface {class TargetLocalInner {TargetInterface getTarget () {return new TargetInterface () {};}}}