package test;
import java.util.function.Runnable;
interface Processable {void execute();}
protected class Source implements Processable {private Target targetField;
public Source() {targetField = new Target();Runnable anon = new Runnable() {@Overridepublic void run() {getTarget().inner.setValue("anonymous");}};anon.run();}
public Target getTarget() {class LocalHelper {Target process(Target t) {t.counter++;return t;}}Target t = new LocalHelper().process(targetField);super.toString();t.inner.setValue("updated");return t;}
@Overridepublic void execute() {}}
class Target {int counter;InnerClass inner = new InnerClass();
class InnerClass {private String value;
void setValue(String val) {this.value = val;}
String getValue() {return value;}}}
