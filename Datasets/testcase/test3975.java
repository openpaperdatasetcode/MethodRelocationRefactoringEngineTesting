package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
interface SourceInterface {static class SourceNested {private TargetInterface.TargetNested targetField;
class SourceInner {final List<String> normalMethod() {List<String> result = new ArrayList<>();raw_type rawObj = new raw_type();TargetInterface.TargetNested nested = new TargetInterface.TargetNested();nested.setValue(1);
for (int i = 0; i < 5; i++) {if (i == TargetInterface.CONST_FIELD) {continue;}nested.callMethod1().callMethod2().callMethod3();result.add(String.valueOf(i));}
try {targetField.setValue(2);targetField.callMethod1();} catch (IOException e) {}
return result;}
final List<String> normalMethod(int param) {return new ArrayList<>();}}}
default void createLocalInner() {class LocalInner {}}}
public interface TargetInterface {int CONST_FIELD = 2;
static class TargetNested {private int value;
public TargetNested setValue(int val) {this.value = val;return this;}
public TargetNested callMethod1() {return this;}
public TargetNested callMethod2() {return this;}
public void callMethod3() {}
public void setValue(int val) throws IOException {this.value = val;}}}
class raw_type {}