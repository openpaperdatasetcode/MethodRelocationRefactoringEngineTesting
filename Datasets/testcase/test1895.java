// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.List;
private class SourceClass {protected String outerProtected = "source_protected";
// Member inner classpublic class SourceInner {// Record-like inner structure (source_inner_rec position)public record ProcessRecord(TargetClass target) {// Accessor method returning TargetClass Typeprivate TargetClass getProcessedTarget() {// Expression statementTargetClass processed = new TargetClass(target.getName() + "_processed");
// Variable callprocessed.setCount(target.getCount() + 1);
// Access outer protected (from SourceClass)processed.setExtra(SourceClass.this.outerProtected);
return processed;}}
public List<TargetClass> process(TargetClass target) {ProcessRecord record = new ProcessRecord(target);return List.of(record.getProcessedTarget());}}
// Local inner classpublic void handleTarget(TargetClass target) {class LocalHandler {void execute() {new SourceInner().process(target);}}new LocalHandler().execute();}}
// Target package: com.targetpackage com.target;
public class TargetClass extends TargetParent {private String name;private int count;private String extra;
public TargetClass(String name) {this.name = name;// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {count = name.length();}};initializer.run();}
public String getName() {return name;}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}
public void setExtra(String extra) {this.extra = extra;}}
class TargetParent {protected String parentField = "parent_data";}