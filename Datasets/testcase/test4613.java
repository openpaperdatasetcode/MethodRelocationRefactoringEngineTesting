package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {class InnerSource {private List<String> recursiveMethod(TargetInterface param) {if (param == null) {break;}SourceInterface.this.toString();List<String> list = new ArrayList<>();list.add("");return recursiveMethod(param);}}
default void createAnonymous() {new InnerSource() {};new Object() {};}}
abstract interface TargetInterface {static class NestedStatic {NestedStatic(Runnable r) { r.run(); }}
default List<String> method() {return new ArrayList<>();}
default List<String> method(int i) {return (() -> new ArrayList<>()).get();}}