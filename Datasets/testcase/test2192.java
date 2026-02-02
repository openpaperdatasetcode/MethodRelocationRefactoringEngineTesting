package source;
import target.TargetClass;import samepackage.OthersClass;import java.util.ArrayList;import java.util.List;
protected class SourceClass {protected int outerProtected;static class StaticNested {}
class SourceInner {private List<String> moveMethod(TargetClass target) {List<String> result = new ArrayList<>();OthersClass others = new OthersClass();
for (int i = 0; i < target.this.field; i++) {if (i == 1) break;result.add(String.valueOf(i));}
do {target.localInnerDependent.method();result.add(String.valueOf(outerProtected));} while (result.size() < 3);
return result;}}}
package target;
public class TargetClass {int field = 1;InnerClass localInnerDependent;
public TargetClass() {class LocalInner {void init() {localInnerDependent = new InnerClass();}}new LocalInner().init();}
class InnerClass {void method() {}}}
package samepackage;
public class OthersClass {}