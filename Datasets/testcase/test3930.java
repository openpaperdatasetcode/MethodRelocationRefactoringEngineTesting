import java.lang.reflect.Method;import java.util.Objects;
public class SourceClass {private String outerInstanceField = "sourceOuterField";
public TargetClass instanceMethod(TargetClass targetParam) {variableCall(targetParam);
try {Method refMethod = TargetClass.class.getMethod("setInnerValue", String.class);refMethod.invoke(targetParam, SourceClass.this.outerInstanceField);} catch (Exception e) {// No new exception thrown}
LocalInnerClass localInner = new LocalInnerClass();localInner.useOuterThis();
return targetParam;}
private void variableCall(TargetClass target) {target.setOuterRef(this);}
private class LocalInnerClass {void useOuterThis() {System.out.println(SourceClass.this.outerInstanceField);}}
public static class StaticNestedClass {public TargetClass processTarget(TargetClass target) {return new SourceClass().instanceMethod(target);}}}
/**
TargetClass contains business logic and an anonymous inner class.
It holds a reference to SourceClass and an inner value for data processing.*/public class TargetClass {private SourceClass outerRef;private String innerValue;
public void setOuterRef(SourceClass outerRef) {this.outerRef = outerRef;}
public void setInnerValue(String innerValue) {this.innerValue = innerValue;}
public String getProcessedValue() {Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println("Processing: " + innerValue);}};anonymousInner.run();return innerValue;}}