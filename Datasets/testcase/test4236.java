package same.pkg;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {protected TargetClass createTarget(TargetClass targetParam) {TargetClass targetInstance = new TargetClass();
try {String fieldVal = targetParam.staticNestedField;TargetClass.StaticNested nestedObj = new TargetClass.StaticNested();List<String> result = new TargetClass().fetchData();
if (targetParam == null) {throw new NullPointerException("TargetClass parameter cannot be null");}} catch (NullPointerException e) {e.printStackTrace();}
class LocalInner {TargetClass getLocalTarget() {return targetInstance;}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new LocalInner().getLocalTarget();}};anonymous.run();
return targetInstance;}}
protected class TargetClass {String staticNestedField;
static class StaticNested {void nestedMethod() {}}
public List<String> fetchData() {return new ArrayList<>();}}
