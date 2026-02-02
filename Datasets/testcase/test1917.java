package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
protected record SourceClass(String name) {static class StaticNested {static List<String> staticField = new ArrayList<>();}
List<String> method(int... args) throws SQLException {TargetClass target = new TargetClass(new ArrayList<>());
synchronized (target) {target.values().addAll(StaticNested.staticField);}
Runnable r = new Runnable() {public void run() {for (int arg : args) {target.values().add(String.valueOf(arg));}}};r.run();
return target.values();}}
private record TargetClass(List<String> values) {{Runnable r = new Runnable() {public void run() {values.add("init");}};r.run();}}