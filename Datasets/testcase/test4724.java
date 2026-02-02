package source;
import target.TargetClass;import java.io.IOException;import java.util.function.Supplier;
public class SourceClass {private TargetClass sourceTargetField;
public SourceClass() throws IOException {this.sourceTargetField = TargetClass.createInstance();}
protected TargetClass process(TargetClass.TargetInner inner, String... args) throws IOException {class LocalType {void updateInner(TargetClass.TargetInner targetInner, String arg) {targetInner.setArg(arg);}}LocalType local = new LocalType();
for (String arg : args) {local.updateInner(inner, arg);String innerData = inner.getProcessedData();}
sourceTargetField.setInner(inner);return sourceTargetField;}
void useLocalInner() throws IOException {class SourceLocalInner {void execute() throws IOException {TargetClass.TargetInner inner = sourceTargetField.new TargetInner();process(inner, "local-arg1", "local-arg2");}}new SourceLocalInner().execute();}
void useAnonymous() throws IOException {Runnable anonTask = new Runnable() {@Overridepublic void run() {try {TargetClass.TargetInner inner = sourceTargetField.new TargetInner();process(inner, "anon-arg");} catch (IOException e) {throw new RuntimeException(e);}}};anonTask.run();}
void callOthersInFunctional() {Supplier<String> func = () -> {try {return OtherFinalClass.getInstance().processTarget(sourceTargetField.new TargetInner());} catch (IOException e) {return "error";}};func.get();}}
package target;
import java.io.IOException;
private class TargetClass {private TargetInner inner;
public static TargetClass createInstance() throws IOException {return new TargetClass();}
public TargetInner new TargetInner() {return new TargetInner();}
public void setInner(TargetInner inner) {this.inner = inner;}
public class TargetInner {private String arg;
public void setArg(String arg) {this.arg = arg;}
public String getProcessedData() {class InnerLocal {String formatData() {return "processed:" + arg;}}return new InnerLocal().formatData();}}}
package source;
import target.TargetClass;
final class OtherFinalClass {private static final OtherFinalClass INSTANCE = new OtherFinalClass();
public static OtherFinalClass getInstance() {return INSTANCE;}
public String processTarget(TargetClass.TargetInner inner) throws IOException {inner.setArg("others-arg");return this.getTargetDetail(inner);}
private String getTargetDetail(TargetClass.TargetInner inner) {return inner.getProcessedData();}}