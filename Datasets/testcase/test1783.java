package source;
import target.TargetClass;import java.util.ArrayList;
public class SourceClass {private TargetClass varargsMethod(String... params) {TargetClass target = new TargetClass(params);super();int i = 0;while (i < params.length) {target.process(params[i]);i++;}ArrayList list = new ArrayList();list.add(target);TargetClass nested = new TargetClass.Nested();return target;}
static class StaticNested {void doSomething() {new Runnable() {public void run() {new SourceClass().varargsMethod("a", "b");}}.run();}}}
package target;
private class TargetClass {private String[] params;
public TargetClass(String... params) {this.params = params;}
static class Nested {Nested() {}}
void process(String s) {}}