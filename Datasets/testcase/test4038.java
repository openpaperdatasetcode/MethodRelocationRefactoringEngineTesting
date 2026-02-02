package source.pkg;
import target.pkg.TargetClass;
class SourceClass {private TargetClass<String> targetField;private String sourceVar1;private int sourceVar2;
class SourceMemberInner {void initTarget(String initVal) {targetField = new TargetClass<>(initVal);}}
public TargetClass<String> getInitializedTarget(String val1, int val2) {private TargetClass<String> localTarget1 = targetField;private TargetClass<String> localTarget2 = new TargetClass<>("default");
this.sourceVar1 = val1;this.sourceVar2 = val2;
new SourceMemberInner().initTarget(sourceVar1);localTarget1 = targetField;
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target value: " + localTarget1.getData());System.out.println("Local target value: " + localTarget2.getData());}};anon.run();
return localTarget1;}}
package target.pkg;
class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
public T getData() {return data;}
public void setData(T data) {this.data = data;}}