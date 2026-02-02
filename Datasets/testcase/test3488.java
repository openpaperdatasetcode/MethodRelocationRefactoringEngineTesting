package test;
import java.io.IOException;
public record SourceClass(String data) {static {new Runnable() {@Overridepublic void run() {class InnerClass {@Overridepublic void run() {SourceClass instance = new SourceClass("test");instanceReference.methodName(instance);}
private void methodName(SourceClass instance) {}}new InnerClass().run();}};}
{new Thread() {@Overridepublic void run() {System.out.println("Second anonymous class");}}.start();}
protected static TargetClass moveMethod(TargetClass param) throws IOException {do {int value = ((Number) param.data()).intValue();switch (value) {case 1:variableCall(param);break;default:; // Empty statement}} while (param.data() != null);
return param;}
private static void variableCall(TargetClass target) {System.out.println(target.data());}
strictfp int callMethod(SourceClass instance) {int result = 0;do {result = InnerCaller.call(instance);} while (result < 10);return result;}
private static class InnerCaller {static int call(SourceClass instance) {return instance.data().length();}}}
protected record TargetClass(Object data) {protected static TargetClass moveMethod(TargetClass param) throws IOException {return param;}}
