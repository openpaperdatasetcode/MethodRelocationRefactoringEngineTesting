package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
interface SourceInterface {class SourceInnerClass extends SuperClass {@Deprecatedprotected List<String> recursiveMethod(TargetInterface.TargetNested param, int depth) {if (depth <= 0) {return new ArrayList<>();}
Label: {super.superField = 1;if (param.value == 0) {break Label;}}
List<String> result;result = new ArrayList<>();param.value = depth;;
try {SourceInterface.callPrivateMethod();param.process();} catch (IOException e) {}
return recursiveMethod(param, depth - 1);}}
default void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
private static void callPrivateMethod() {}}
strictfp interface TargetInterface {static class TargetNested {int value;void process() throws IOException {}}}
class SuperClass {int superField;}