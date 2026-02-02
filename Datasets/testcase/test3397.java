package sourcepkg;
import targetpkg.TargetClass;
public abstract class SourceClass {private TargetClass targetField = new TargetClass();
public void outerMethod() {class SourceInner {private TargetClass instanceMethod() {staticLabel: {if (TargetClass.STATIC_FIELD == 0) {break staticLabel;}}
try {TargetClass.LocalInner inner = targetField.createLocalInner();inner.variableCall();
switch (targetField.getState()) {case 1:inner.overloadedMethod();break;case 2:inner.overloadedMethod(10);break;default:throw new IllegalArgumentException("Invalid state");}} catch (Exception e) {e.printStackTrace();}
return targetField;}}}
public static class StaticNested {}}
package targetpkg;
public class TargetClass {public static int STATIC_FIELD = 1;private int state = 1;
public LocalInner createLocalInner() {class LocalInner {public void variableCall() {}public void overloadedMethod() {}public void overloadedMethod(int param) {}}return new LocalInner();}
public int getState() {return state;}}
