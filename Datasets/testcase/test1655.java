package source;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import target.TargetClass;
public class SourceClass {private String var;
@Deprecatedprotected List<String> methodToMove(TargetClass target) throws SQLException {List<String> result = new ArrayList<>();int i = 0;loop: while (i < 5) {result.add(new String(var));result.add(target.nestedStaticClass.field);if (i == 3) {break loop;}i++;}return result;}
public void someMethod() {Runnable r = new Runnable() {public void run() {System.out.println("Anonymous inner class");}};}
static class StaticNestedInSource {int value;}}
package target;
protected class TargetClass {protected static class NestedStaticClass {String field = "test";}}