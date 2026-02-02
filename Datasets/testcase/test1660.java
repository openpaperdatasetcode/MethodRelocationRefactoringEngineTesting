package same;
import java.io.Serializable;
public class SourceClass implements Serializable {public int outerField;
public static class StaticNested {public class NestedInner {private int innerField;
public final Object methodToMove(TargetClass target) {SourceClass.this.outerField = 10;innerField = 5;
if (target.targetField < 0) {throw new IllegalArgumentException(SourceClass.this.outerField + " is invalid");}
String typeDecl = "test";int count = 0;
switch (target.targetField) {case 1:count++;break;case 2:count += 2;break;default:count = 0;}
Runnable r = new Runnable() {public void run() {count++;}};
return count;}
public final Object methodToMove(String str) {return str;}}}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {outerField++;}};}}
package same;
public class TargetClass {public int targetField;
public void methodWithAnonymous() {Runnable r = new Runnable() {public void run() {targetField++;}};}}
