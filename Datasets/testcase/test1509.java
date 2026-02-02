package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
public class Target {public String publicField = "target_field";
public class MemberInner {private List<String> items = new ArrayList<>();
public void addItem(String item) {items.add(item);}
public List<String> getItems() {return items;}}}
private abstract class Source {protected String outerProtected = "outer_protected";
public abstract class Inner {public abstract void process(Target target);}
public class ConcreteInner extends Inner {@Overridepublic void process(Target target) {// Local inner classclass LocalHandler {void handleItem(String item) {System.out.println(item);}}
// Anonymous inner classRunnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println(target.publicField); // Access target field}};anonTask.run();
// 2 ClassInstanceCreation expressionsTarget.MemberInner inner1 = target.new MemberInner();Target.MemberInner inner2 = target.new MemberInner();
// Access outer protected fieldinner1.addItem(outerProtected);
// Expression statementinner2.addItem(target.publicField);
// Enhanced for statementfor (String item : inner1.getItems()) {new LocalHandler().handleItem(item);}
// Access instance methodinner1.addItem("processed");
// Used by reflectiontry {Method method = Target.MemberInner.class.getMethod("getItems");List<String> items = (List<String>) method.invoke(inner2);} catch (Exception e) {// No new exception}
// Variable callList<String> allItems = new ArrayList<>(inner1.getItems());allItems.addAll(inner2.getItems());}}}
