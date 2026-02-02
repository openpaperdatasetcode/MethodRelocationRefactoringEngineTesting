package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {public class FirstInner {public String value = "first_inner";}
public class SecondInner {public String process(FirstInner inner) {return inner.value;}}
public List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();SecondInner secondInner = new SecondInner();
for (TargetClass target : targets) {// Check for null to avoid NullPointerExceptionif (target == null) {throw new NullPointerException("Target cannot be null");}
// Variable call - access target's fieldresult.add(target.data);
// Super keywordresult.add(super.toString());
// Lambda expressionRunnable lambda = () -> System.out.println(secondInner.process(new FirstInner()));lambda.run();
// Switch caseswitch (target.getType()) {case 1:target.handle("case1"); // Overloaded method 1break;case 2:target.handle(2); // Overloaded method 2break;default:break;}
// Requires try-catchtry {// Depends on target's inner classTargetClass.AnonymousHolder holder = target.new AnonymousHolder();result.add(holder.getContent());} catch (Exception e) {// no new exception}}
return result;}}
abstract class TargetClass {public String data;private int type;
public TargetClass(String data, int type) {this.data = data;this.type = type;}
// Overloaded methodspublic void handle(String str) {data = str;}
public void handle(int num) {data = String.valueOf(num);}
public int getType() {return type;}
// Inner class used by sourcepublic class AnonymousHolder {private String content;
public AnonymousHolder() {// Anonymous inner class in targetRunnable task = new Runnable() {@Overridepublic void run() {content = "holder_content";}};task.run();}
public String getContent() {return content;}}
public abstract void abstractMethod();}
